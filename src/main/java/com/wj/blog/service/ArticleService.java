package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.common.pojo.Article;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
public interface ArticleService extends IService<Article> {

    /**
     * 发布文章
     *
     * @param article 文章实体
     * @return 添加结果
     */
    boolean addArticle(Article article);


    /**
     * 删除文章
     *
     * @param articleId 文章id
     * @return boolean 删除结果
     */
    boolean removeArticle(String articleId);
}
