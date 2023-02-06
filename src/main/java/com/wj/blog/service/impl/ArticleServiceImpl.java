package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.pojo.Article;
import com.wj.blog.mapper.ArticleMapper;
import com.wj.blog.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public boolean addArticle(Article article) {
        return false;
    }

    @Override
    public boolean removeArticle(String articleId) {
        return false;
    }


}
