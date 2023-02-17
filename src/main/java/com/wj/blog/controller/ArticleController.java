package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.vo.ArticleDetailVo;
import com.wj.blog.pojo.vo.ArticleIntroductionVo;
import com.wj.blog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog")
public class ArticleController {

    @Resource(name = "articleService")
    private ArticleService articleService;

    @GetMapping("/article")
    public ResultEntity<List<ArticleIntroductionVo>> searchArticleList(@RequestParam(required = false) String title,
                                                                       @RequestParam(required = false) String author,
                                                                       @RequestParam(required = false) Integer page,
                                                                       @RequestParam(required = false) Integer size) {
        List<ArticleDto> articleDtoList = articleService.searchArticleList(title, author, page, size);
        List<ArticleIntroductionVo> articleIntroductionVos = new ArrayList<>();
        articleDtoList.forEach(articleDto -> {
            ArticleIntroductionVo articleIntroductionVo = new ArticleIntroductionVo();
            BeanUtils.copyProperties(articleDto, articleIntroductionVo);
            articleIntroductionVos.add(articleIntroductionVo);
        });
        return ResultEntity.success(articleIntroductionVos);
    }

    @GetMapping("/article/{id}")
    public ResultEntity<ArticleDetailVo> searchArticleDetail(@PathVariable String id) {
        ArticleDto articleDto = articleService.searchArticleDetail(id);
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
        BeanUtils.copyProperties(articleDto, articleDetailVo);
        // todo comments->commentsVo category->categoryVo statics->staticsVo
        return ResultEntity.success(articleDetailVo);
    }
}
