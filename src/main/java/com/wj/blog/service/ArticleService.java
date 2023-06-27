package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.param.ArticleQueryParam;
import com.wj.blog.pojo.entity.Article;

import java.util.List;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface ArticleService extends IService<Article> {

    /**
     * 查询文章列表
     *
     * @param articleQueryParam 文章查询参数
     * @return {@link List}<{@link ArticleDto}> 文章列表
     */
    List<ArticleDto> searchArticleList(ArticleQueryParam articleQueryParam);

    /**
     * 查询文章详情
     *
     * @param id 文章id
     * @return {@link ArticleDto} 文章详情
     */
    ArticleDto searchArticleDetail(String id);

    /**
     * 创建文章
     *
     * @param articleDto 文章dto
     */
    void createArticle(ArticleDto articleDto);

    /**
     * 删除文章
     *
     * @param uid uid 用户id
     * @param id  id 文章id
     */
    void removeArticle(String uid, String id);
}
