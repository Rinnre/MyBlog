package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.pojo.dto.ArticleDto;
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
     * @param title  标题
     * @param author 作者
     * @param page   页面
     * @param size   大小
     * @return {@link List}<{@link ArticleDto}> 文章列表
     */
    List<ArticleDto> searchArticleList(String title, String author, Integer page, Integer size);

    /**
     * 查询文章详情
     *
     * @param id 文章id
     * @return {@link ArticleDto} 文章详情
     */
    ArticleDto searchArticleDetail(String id);
}
