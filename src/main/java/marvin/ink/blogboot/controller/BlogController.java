package marvin.ink.blogboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import marvin.ink.blogboot.model.common.PageData;
import marvin.ink.blogboot.req.blog.BlogOptionsReq;
import marvin.ink.blogboot.req.blog.BlogPageSearchReq;
import marvin.ink.blogboot.req.blog.BlogSaveReq;
import marvin.ink.blogboot.res.blog.BlogTagRes;
import marvin.ink.blogboot.service.BlogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/26 20:44
 */
@RestController
@Api(value = "value", tags = "个人博客管理接口")
@RequestMapping("blog")
@Slf4j
public class BlogController {
    @Resource
    private BlogService blogService;

    @ApiOperation("添加或修改博客")
    @PostMapping("saveOrUpdate")
    public void saveOrUpdate(@Validated @RequestBody BlogSaveReq blogReq) {
        blogService.saveOrUpdate(blogReq);
    }

    @ApiOperation("根据id查询博客")
    @GetMapping("/{id}")
    public BlogTagRes findBlogByBlogId(@PathVariable int id) {
        return blogService.findBlogByBlogId(id);
    }

    @ApiOperation("根据博客id删除个人的博客")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        blogService.deleteById(id);
    }


    @ApiOperation("根据条件查询博客列表")
    @GetMapping("findBlogByOptions")
    PageData<BlogTagRes> findBlogByOptions(@Validated BlogOptionsReq options) {
        log.info("options:{}", options);
        return blogService.findBlogByOptions(options);
    }

}
