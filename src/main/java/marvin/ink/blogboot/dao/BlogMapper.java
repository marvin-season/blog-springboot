package marvin.ink.blogboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import marvin.ink.blogboot.model.entity.Blog;
import marvin.ink.blogboot.model.entity.Tag;
import marvin.ink.blogboot.res.blog.BlogTagRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/27 20:33
 */
public interface BlogMapper extends BaseMapper<Blog> {
    List<Tag> findTagsByBlogId(@Param("blogId") Integer blogId);

    List<Integer> findTagIdsByBlogId(@Param("blogId") Integer blogId);

    void deleteTagIdsByBlogId(Integer blogId);

    BlogTagRes findBlogByBlogId(@Param("id") int id);

    List<Integer> findCollectBlogIds(Integer authorId);
}
