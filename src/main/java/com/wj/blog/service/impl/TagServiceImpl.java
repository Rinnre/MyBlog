package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.pojo.Tag;
import com.wj.blog.mapper.TagMapper;
import com.wj.blog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> getTagsByArticleId(String articleId) {
        return this.baseMapper.getTagsByArticleId(articleId);
    }
}
