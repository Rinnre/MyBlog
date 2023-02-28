package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.dto.ArticleQueryParam;
import com.wj.blog.pojo.dto.CommentDto;
import com.wj.blog.pojo.vo.*;
import com.wj.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<CommentDetailVo> commentVos = assembleComment(articleDto.getComments());
        articleDetailVo.setComments(commentVos);
        return ResultEntity.success(articleDetailVo);

    }

    private List<CommentDetailVo> assembleComment(List<CommentDto> comments) {
        comments = comments.stream()
                .sorted(Comparator.comparing(CommentDto::getCreateTime))
                .collect(Collectors.toList());
        List<CommentDetailVo> commentRootVos = new ArrayList<>();
        // 遍历找出根评论
        comments.forEach(comment -> {
            if (comment.getPid() == null) {
                convertDtoToVo(commentRootVos, comment);
            }
        });

        // 遍历寻找子节点
        for (CommentDetailVo commentRoot : commentRootVos) {
            List<CommentDetailVo> commentChildrenVos = new ArrayList<>();
            comments.forEach(comment -> {
                if (commentRoot.getId().equals(comment.getPid())) {
                    convertDtoToVo(commentChildrenVos, comment);
                }
            });
            commentRoot.setChildrenComments(commentChildrenVos);
        }

        return commentRootVos;
    }

    private void convertDtoToVo(List<CommentDetailVo> commentChildrenVos, CommentDto comment) {
        CommentDetailVo commentVo = new CommentDetailVo();
        invertVo(comment, commentVo);
        UserVo userVo = new UserVo();
        invertVo(comment.getUser(), userVo);
        commentVo.setUser(userVo);
        userVo = new UserVo();
        invertVo(comment.getReplayUser(), userVo);
        commentVo.setReplayUser(userVo);
        commentChildrenVos.add(commentVo);
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
