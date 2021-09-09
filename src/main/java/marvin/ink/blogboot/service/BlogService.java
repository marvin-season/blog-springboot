package marvin.ink.blogboot.service;

import marvin.ink.blogboot.model.common.PageData;
import marvin.ink.blogboot.req.blog.BlogPageSearchReq;
import marvin.ink.blogboot.req.blog.BlogSaveReq;
import marvin.ink.blogboot.res.blog.BlogRes;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/27 20:32
 */
public interface BlogService {
    void saveOrUpdate(BlogSaveReq blogSaveReq);

    PageData<BlogRes> findPublishedBlogByCondition(BlogPageSearchReq blogPageSearchReq);

    List<BlogRes> findUnpublishedBlogByAuthorId(Integer authorId);

    void deleteById(Integer id);

    BlogRes findBlogByBlogId(int id);
}
