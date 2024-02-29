package com.wj.blog.controller;


import com.wj.blog.common.result.Result;
import com.wj.blog.model.dto.ArticleDto;
import com.wj.blog.model.param.ArticleQueryParam;
import com.wj.blog.model.vo.ArticleDetailVo;
import com.wj.blog.model.vo.ArticleIntroductionVo;
import com.wj.blog.model.vo.CategoryVo;
import com.wj.blog.model.vo.UserVo;
import com.wj.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog/article")
@Slf4j
public class ArticleController {

    @Resource(name = "articleService")
    private ArticleService articleService;

    /**
     * 搜索文章列表
     *
     * @param articleQueryParam 文章查询参数
     * @return {@link Result}<{@link List}<{@link ArticleIntroductionVo}>>
     */
    @GetMapping
    public Result<List<ArticleIntroductionVo>> searchArticleList(ArticleQueryParam articleQueryParam) {
        List<ArticleDto> articleDtoList = articleService.searchArticleList(articleQueryParam);
        List<ArticleIntroductionVo> articleIntroductionVos = new ArrayList<>();
        articleDtoList.forEach(articleDto -> {
            ArticleIntroductionVo articleIntroductionVo = new ArticleIntroductionVo();
            BeanUtils.copyProperties(articleDto, articleIntroductionVo);
            invertArticleDtoToVo(articleDto, articleIntroductionVo);
            articleIntroductionVos.add(articleIntroductionVo);
        });
        return Result.success(articleIntroductionVos);
    }

    /**
     * 搜索文章细节
     *
     * @param id 文章id
     * @return {@link Result}<{@link ArticleDetailVo}>
     */
    @GetMapping("/{id}")
    public Result<ArticleDetailVo> searchArticleDetail(@PathVariable String id) {
        ArticleDto articleDto = articleService.searchArticleDetail(id);
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
        BeanUtils.copyProperties(articleDto, articleDetailVo);
        invertArticleDtoToVo(articleDto, articleDetailVo);
        return Result.success(articleDetailVo);

    }

    /**
     * 创建文章
     *
     * @param articleDto 文章dto
     * @return {@link Result}<{@link String}>
     */
    @PostMapping
    public Result<String> createArticle(@RequestBody @Valid ArticleDto articleDto) {
        articleService.createArticle(articleDto);
        return Result.success(null);
    }

    /**
     * 删除文章
     * 校验文章-用户在token验证中做
     * 后续需考虑批量删除、url长度有限
     *
     * @param id 文章id
     * @return {@link Result}<{@link String}>
     */
    @DeleteMapping("/{id}")
    public Result<String> removeArticle(@PathVariable String id) {
        articleService.removeArticle(id);
        return Result.success(null);
    }

    /**
     * 反转文章dto签证官
     * 部分articleDto属性转vo
     *
     * @param articleDto      文章dto
     * @param articleDetailVo 文章详细签证官
     */
    public void invertArticleDtoToVo(ArticleDto articleDto, ArticleIntroductionVo articleDetailVo) {
        // category->categoryVo
        CategoryVo categoryVo = new CategoryVo();
        invertVo(articleDto.getCategory(), categoryVo);
        articleDetailVo.setCategory(categoryVo);

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
