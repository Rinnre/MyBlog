package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.dto.ArticleQueryParam;
import com.wj.blog.pojo.entity.Article;
import com.wj.blog.pojo.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询文章列表
     *
     * @param articleQueryParam 文章查询参数
     * @return {@link List}<{@link ArticleDto}> 文章列表
     */
    List<ArticleDto> selectArticleList(@Param("articleQueryParam") ArticleQueryParam articleQueryParam);

    /**
     * 查看文章详情
     *
     * @param id 文章id
     * @return {@link ArticleDto}
     */
    ArticleDto searchArticleDetail(@Param("id") String id);

    /**
     * 插入标签
     *
     * @param tags 标签
     * @param id   id
     */
    void insertTags(@Param("tags") List<Category> tags, @Param("id") String id);
}
