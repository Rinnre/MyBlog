package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.pojo.Article;
import com.wj.blog.pojo.Tag;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 文章绑定标签
     *
     * @param articleId 文章id
     * @param tagList   标签集合
     * @return 影响的行数
     */
    int insertTags(String articleId, List<Tag> tagList);
}
