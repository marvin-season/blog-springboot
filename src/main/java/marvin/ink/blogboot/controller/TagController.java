package marvin.ink.blogboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import marvin.ink.blogboot.model.entity.Tag;
import marvin.ink.blogboot.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 16:47
 */
@RestController
@RequestMapping("tag")
@Api(tags = "标签管理接口")
public class TagController {
    @Resource
    private TagService tagService;

    @ApiOperation("查询所有分类")
    @GetMapping("tags")
    public List<Tag> findAll() {
        return tagService.findAll();
    }

    @ApiOperation("查询某个分类通过分类id")
    @GetMapping("{tagId}")
    public Tag findByCategoryId(@PathVariable("tagId") Integer tagId) {
        return tagService.findByTagId(tagId);
    }
}
