package marvin.ink.blogboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.dao.BlogMapper;
import marvin.ink.blogboot.dao.BlogTagMapper;
import marvin.ink.blogboot.exception.CustomizeException;
import marvin.ink.blogboot.model.common.PageData;
import marvin.ink.blogboot.model.entity.Blog;
import marvin.ink.blogboot.model.entity.Category;
import marvin.ink.blogboot.model.entity.Tag;
import marvin.ink.blogboot.model.enums.ResultEnum;
import marvin.ink.blogboot.model.pojo.BlogTag;
import marvin.ink.blogboot.req.blog.BlogOptionsReq;
import marvin.ink.blogboot.service.BlogService;
import marvin.ink.blogboot.service.CategoryService;
import marvin.ink.blogboot.service.TagService;
import marvin.ink.blogboot.req.blog.BlogPageSearchReq;
import marvin.ink.blogboot.req.blog.BlogSaveReq;
import marvin.ink.blogboot.res.blog.BlogTagRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 马文澍
 * @Date: 2021/8/27 20:32
 */
@Service
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Resource
    private BlogTagMapper blogTagMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(BlogSaveReq blogSaveReq) {
        Assert.notNull(blogSaveReq, () -> CustomizeException.is(ResultEnum.PARAM_ERROR).hint("参数空指针异常"));

        // 判断分类存在否
        Category category = categoryService.findByCategoryId(blogSaveReq.getCategoryId());
        if (ObjectUtil.isNull(category)) {
            throw CustomizeException.is(ResultEnum.PARAM_ERROR).hint("分类不存在");
        }

        //相关的标签
        List<Integer> tagIds = blogSaveReq.getTagIds();
        //  判断标签存在否
        if (ObjectUtil.isNotEmpty(tagIds)) {
            tagIds.forEach(tagId -> {
                Tag tag = tagService.findByTagId(tagId);
                if (ObjectUtil.isNull(tag)) {
                    throw CustomizeException.is(ResultEnum.PARAM_ERROR).hint("指定的标签不存在");
                }
            });
        }

        Blog blog = BeanUtil.copyProperties(blogSaveReq, Blog.class);
        Blog bean = baseMapper.selectOne(Wrappers.<Blog>lambdaQuery().eq(Blog::getId, blog.getId()));
        // 博客存在
        if (ObjectUtil.isNotNull(bean)) {
            log.info("修改博客");
            blog.setUpdateTime(LocalDateTime.now());
            super.updateById(blog);
            // 删除掉原来的标签， 后面重新添加
            baseMapper.deleteTagIdsByBlogId(blog.getId());
        } else {
            // 博客为新增
            log.info("新增博客");
            super.save(blog);
            // 新增不用删除中间表中的blog与tag的映射关系
        }
        // 新增标签
        tagIds.forEach(tagId -> {
            BlogTag blogTag = new BlogTag().setBlogId(blog.getId()).setTagId(tagId);
            blogTagMapper.insert(blogTag);
        });

    }

    @Override
    public PageData<BlogTagRes> findBlogByCondition(BlogPageSearchReq blogPageSearchReq) {
        log.info("blogPageSearchReq = {}", blogPageSearchReq);
        List<Blog> blogs = null;
        // 所有博客
        Page<Blog> blogPage = baseMapper.selectPage(new Page<>(blogPageSearchReq.getPageNo(), blogPageSearchReq.getPageSize()),
                Wrappers.<Blog>lambdaQuery()
                        .eq(ObjectUtils.isNotNull(blogPageSearchReq.getAuthorId()), Blog::getAuthorId, blogPageSearchReq.getAuthorId())
                        .like(ObjectUtils.isNotNull(blogPageSearchReq.getTitle()), Blog::getTitle, blogPageSearchReq.getTitle())
                        .or()
                        .eq(ObjectUtils.isNotNull(blogPageSearchReq.getCategoryId()), Blog::getCategoryId, blogPageSearchReq.getCategoryId())
                        .or()
                        .and(wrapper -> {
                            wrapper.eq(Blog::getPublished, true);
                        })
        );

        List<BlogTagRes> blogRes = BeanUtil.copyToList(blogPage.getRecords(), BlogTagRes.class);
        // 查出所对应的标签
        this.withTags(blogRes);

        // 过滤掉不包含BlogSearchReq::getTagIds的博客
        List<Integer> searchIds = blogPageSearchReq.getTagIds();
        if (ObjectUtil.isNotEmpty(searchIds)) {
            blogRes = blogRes.stream().filter(blog -> {
                List<Integer> tagIds = blog.getTags().stream().map(Tag::getId).collect(Collectors.toList());
                // 只要包含一个标签就不过滤
                return tagIds.stream().anyMatch(searchIds::contains);
            }).collect(Collectors.toList());
        }

        PageData<BlogTagRes> blogResPage = PageData.of(blogPage, BlogTagRes.class);
        blogResPage.setRecords(blogRes);
        return blogResPage;

    }

    @Override
    public List<BlogTagRes> findUnpublishedBlogByAuthorId(Integer authorId) {
        List<Blog> blogs = baseMapper.selectList(Wrappers.<Blog>lambdaQuery()
                .eq(Blog::getPublished, false)
                .eq(Blog::getAuthorId, authorId)
        );
        List<BlogTagRes> blogRes = BeanUtil.copyToList(blogs, BlogTagRes.class);
        withTags(blogRes);
        return blogRes;
    }

    public void withTags(List<BlogTagRes> blogs) {
        blogs.forEach(blog -> {
            List<Tag> tags = baseMapper.findTagsByBlogId(blog.getId());
            blog.setTags(tags);
        });
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        // 逻辑删除
        super.removeById(id);
    }

    @Override
    public BlogTagRes findBlogByBlogId(int id) {
        BlogTagRes blogRes = baseMapper.findBlogByBlogId(id);
        if (ObjectUtil.isNull(blogRes)) {
            throw CustomizeException.is(ResultEnum.DATA_NO_FOUND).hint("博客不存在!或被移动");
        }
        return blogRes;
    }

    @Override
    public PageData<BlogTagRes> findBlogByOptions(BlogOptionsReq options) {
        PageData<BlogTagRes> pageData = new PageData<BlogTagRes>(0, null, options.getPageSize());
        Page<Blog> page = null;
        if (options.isRecommend()) {
            // 推荐博客， 查询点赞数在20以上的
            page = baseMapper.selectPage(new Page<>(options.getPageNo(), options.getPageSize()),
                    Wrappers.<Blog>lambdaQuery().ge(Blog::getLikeCount, 20));
        } else if (options.isCollect()) {
            // 收藏博客

            //1、得到用户收藏的博客的系列id
            List<Integer> blogIds = baseMapper.findCollectBlogIds(options.getAuthorId());
            System.out.println(blogIds);
            // 2、使用得到的id， 查找博客信息

            if (ObjectUtil.isNotEmpty(blogIds)) {
                page = baseMapper.selectPage(new Page<>(options.getPageNo(), options.getPageSize()),
                        Wrappers.<Blog>lambdaQuery()
                                .in(Blog::getId, blogIds)
                );
            }
        } else {
            // 其他条件
            page = baseMapper.selectPage(new Page<>(options.getPageNo(), options.getPageSize()),
                    Wrappers.<Blog>lambdaQuery()
                            .eq(ObjectUtils.isNotNull(options.getAuthorId()), Blog::getAuthorId, options.getAuthorId())
                            .eq(options.isDraft(), Blog::getPublished, false)
            );

        }

        if (ObjectUtil.isNotNull(page)) {
            List<BlogTagRes> blogRes = BeanUtil.copyToList(page.getRecords(), BlogTagRes.class);
            //  同步博客标签
            this.withTags(blogRes);

            pageData = PageData.of(page, BlogTagRes.class);
            pageData.setRecords(blogRes);
        }

        return pageData;
    }
}
