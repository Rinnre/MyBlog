package com.wj.blog.controller;


import com.wj.blog.common.pojo.Article;
import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/addArticle")
    @ApiOperation("新增文章")
    public ResultEntity<String> addArticle(@RequestBody Article article) {
        boolean insertResult = articleService.addArticle(article);
        if (insertResult) {
            return ResultEntity.success();
        } else {
            return ResultEntity.fail();
        }
    }

    @DeleteMapping("removeArticle/{articleId}")
    @ApiOperation("删除文章")
    public ResultEntity<String> removeArticle(@PathVariable String articleId) {
        boolean removeResult = articleService.removeArticle(articleId);
        return ResultEntity.success();
    }


}

