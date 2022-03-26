package marvin.ink.blogboot.service;

import marvin.ink.blogboot.model.common.PageData;
import marvin.ink.blogboot.req.blog.BlogOptionsReq;
import marvin.ink.blogboot.req.blog.BlogPageSearchReq;
import marvin.ink.blogboot.req.blog.BlogSaveReq;
import marvin.ink.blogboot.res.blog.BlogInfoRes;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/27 20:32
 */
public interface BlogService {
    void saveOrUpdate(BlogSaveReq blogSaveReq);

    PageData<BlogInfoRes> findBlogByCondition(BlogPageSearchReq blogPageSearchReq);

    List<BlogInfoRes> findUnpublishedBlogByAuthorId(Integer authorId);

    void deleteById(Integer id);

    BlogInfoRes findBlogByBlogId(int id);

    PageData<BlogInfoRes> findBlogByOptions(BlogOptionsReq options);
}
