package com.wj.blog.mapper;

import com.wj.blog.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id查询其所有的tag
     * @param articleId 文章ID
     * @return tags
     */
    List<Tag> getTagsByArticleId(String articleId);
}
