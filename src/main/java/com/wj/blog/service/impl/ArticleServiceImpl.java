package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.ArticleMapper;
import com.wj.blog.pojo.dto.ArticleDto;
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
    public List<ArticleDto> searchArticleList(String title, String author, Integer page, Integer size) {
        Integer startPageNumber = null;
        if (null != page && null != size) {
            startPageNumber = (page - 1) * size;
        }
        // todo 重构查询sql
        return baseMapper.selectArticleList(title, author, startPageNumber, size);
    }

    @Override
    public ArticleDto searchArticleDetail(String id) {
        return baseMapper.searchArticleDetail(id);
    }
}
