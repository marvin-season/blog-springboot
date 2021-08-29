package marvin.ink.blogboot.service;

import marvin.ink.blogboot.model.entity.Tag;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 16:40
 */
public interface TagService {
    // 所有分类
    List<Tag> findAll();

    // 查询一个分类
    Tag findByTagId(Integer categoryId);
}
