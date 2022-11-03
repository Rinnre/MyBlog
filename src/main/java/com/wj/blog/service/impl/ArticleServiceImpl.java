package com.wj.blog.service.impl;

import com.wj.blog.entity.Article;
import com.wj.blog.mapper.ArticleMapper;
import com.wj.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
