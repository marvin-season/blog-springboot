package marvin.ink.blogboot.service;

import marvin.ink.blogboot.model.common.PageData;
import marvin.ink.blogboot.vo.req.blog.BlogPageSearchReq;
import marvin.ink.blogboot.vo.req.blog.BlogSaveReq;
import marvin.ink.blogboot.vo.res.blog.BlogRes;

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
}
