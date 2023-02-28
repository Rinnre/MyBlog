package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.ArticleMapper;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.dto.ArticleQueryParam;
import com.wj.blog.pojo.entity.Article;
import com.wj.blog.service.ArticleService;
import org.springframework.stereotype.Service;

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
        // TODO 访问量增加
        return baseMapper.searchArticleDetail(id);
    }
}
