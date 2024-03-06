package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.ArticleMapper;
import com.wj.blog.model.dto.ArticleDto;
import com.wj.blog.model.entity.Article;
import com.wj.blog.model.param.ArticleQueryParam;
import com.wj.blog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Override
    public List<ArticleDto> searchArticleList(ArticleQueryParam articleQueryParam) {

        if (null != articleQueryParam) {
            Integer startPageNumber = null;
            if (null != articleQueryParam.getPage() && null != articleQueryParam.getSize()) {
                startPageNumber = (articleQueryParam.getPage() - 1) * articleQueryParam.getSize();
            }
            articleQueryParam.setPage(startPageNumber);
        }
        return baseMapper.selectArticleList(articleQueryParam);
    }

    @Override
    public ArticleDto searchArticleDetail(String id) {
        return baseMapper.searchArticleDetail(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createArticle(ArticleDto articleDto) {
        // 文章信息保存
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        baseMapper.insert(article);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeArticle(String id) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getId, id);
        Article article = baseMapper.selectOne(articleLambdaQueryWrapper);
        if (null != article) {
            baseMapper.deleteById(id);
        }
    }
}
