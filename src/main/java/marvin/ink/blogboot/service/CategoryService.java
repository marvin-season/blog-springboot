package marvin.ink.blogboot.service;

import marvin.ink.blogboot.model.entity.Category;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 16:27
 */
public interface CategoryService {
    // 所有分类
    List<Category> findAll();
    // 查询一个分类
    Category findByCategoryId(Integer categoryId);
}
