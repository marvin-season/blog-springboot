package marvin.ink.blogboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import marvin.ink.blogboot.dao.CategoryMapper;
import marvin.ink.blogboot.model.entity.Category;
import marvin.ink.blogboot.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 16:27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<Category> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public Category findByCategoryId(Integer categoryId) {
        return baseMapper.selectById(categoryId);
    }
}
