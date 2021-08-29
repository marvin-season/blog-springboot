package marvin.ink.blogboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import marvin.ink.blogboot.dao.TagMapper;
import marvin.ink.blogboot.model.entity.Tag;
import marvin.ink.blogboot.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 马文澍
 * @Date: 2021/8/28 16:40
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public List<Tag> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public Tag findByTagId(Integer tagId) {
        return baseMapper.selectById(tagId);
    }
}
