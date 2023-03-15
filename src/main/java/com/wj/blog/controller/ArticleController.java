package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.common.util.CommentUtil;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.dto.ArticleQueryParam;
import com.wj.blog.pojo.vo.*;
import com.wj.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章 controller
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog")
@Slf4j
public class ArticleController {

    @Resource(name = "articleService")
    private ArticleService articleService;

    @GetMapping("/article")
    public ResultEntity<List<ArticleIntroductionVo>> searchArticleList(@RequestParam(required = false) ArticleQueryParam articleQueryParam) {
        List<ArticleDto> articleDtoList = articleService.searchArticleList(articleQueryParam);
        List<ArticleIntroductionVo> articleIntroductionVos = new ArrayList<>();
        articleDtoList.forEach(articleDto -> {
            ArticleIntroductionVo articleIntroductionVo = new ArticleIntroductionVo();
            BeanUtils.copyProperties(articleDto, articleIntroductionVo);
            // category->categoryVo
            // statics->staticsVo
            // tag->tagVo
            invertArticleDtoToVo(articleDto, articleIntroductionVo);
            articleIntroductionVos.add(articleIntroductionVo);
        });
        return ResultEntity.success(articleIntroductionVos);
    }

    @GetMapping("/article/{id}")
    public ResultEntity<ArticleDetailVo> searchArticleDetail(@PathVariable String id) {
        ArticleDto articleDto = articleService.searchArticleDetail(id);
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
        BeanUtils.copyProperties(articleDto, articleDetailVo);
        invertArticleDtoToVo(articleDto, articleDetailVo);
        //  comments->commentsVo 评论组装
        List<CommentDetailVo> commentVos = CommentUtil.assembleComment(articleDto.getComments());
        articleDetailVo.setComments(commentVos);
        return ResultEntity.success(articleDetailVo);

    }

    @PostMapping("/article")
    public ResultEntity<String> createArticle(@RequestBody @Valid ArticleDto articleDto) {
        articleService.createArticle(articleDto);
        return ResultEntity.success();
    }

    /**
     * 部分articleDto属性转vo
     */
    public void invertArticleDtoToVo(ArticleDto articleDto, ArticleIntroductionVo articleDetailVo) {
        // category->categoryVo
        CategoryVo categoryVo = new CategoryVo();
        invertVo(articleDto.getCategory(), categoryVo);
        articleDetailVo.setCategory(categoryVo);

        // statics->staticsVo
        StatisticsVo statisticsVo = new StatisticsVo();
        if (articleDto.getStatistics() != null) {
            invertVo(articleDto.getStatistics(), statisticsVo);
        }
        articleDetailVo.setStatisticsVo(statisticsVo);
        // tag->tagVo
        List<CategoryVo> tags = new ArrayList<>();
        if (articleDto.getTags() != null) {
            articleDto.getTags().forEach(tag -> {
                CategoryVo tagVo = new CategoryVo();
                invertVo(tag, tagVo);
                tags.add(tagVo);
            });
        }
        articleDetailVo.setTags(tags);
        // user->userVo
        UserVo userVo = new UserVo();
        if (articleDto.getAuthor() != null) {
            BeanUtils.copyProperties(articleDto.getAuthor(), userVo);
        }
        articleDetailVo.setAuthor(userVo);
    }

    /**
     * dto对象转化为vo对象
     */
    private void invertVo(Object source, Object target) {
        if (source != null) {
            BeanUtils.copyProperties(source, target);
        }
    }
}
