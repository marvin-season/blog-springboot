package marvin.ink.blogboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import marvin.ink.blogboot.model.entity.Category;
import marvin.ink.blogboot.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 16:32
 */
@RestController
@RequestMapping("category")
@Api(value = "value", tags = "分类管理接口")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @ApiOperation("查询所有分类")
    @GetMapping("categories")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @ApiOperation("查询某个分类通过分类id")
    @GetMapping("{categoryId}")
    public Category findByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        return categoryService.findByCategoryId(categoryId);
    }
}
