package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.common.pojo.Tag;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
public interface TagService extends IService<Tag> {

    /**
     *  根据文章查询对应的tag
     * @param articleId 文章ID
     * @return tags
     */
    List<Tag> getTagsByArticleId(String articleId);

}
