package com.wj.blog.controller;


import com.wj.blog.entity.Article;
import com.wj.blog.service.ArticleService;
import com.wj.blog.util.ResultEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author w
 * @since 2022-08-01
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/get/all/articles")
    @ApiOperation("查找所有文章")
    public ResultEntity<List<Article>> getAllArticles(){
        List<Article> articles = articleService.getBaseMapper().selectList(null);
        return ResultEntity.success(articles);
    }
}

