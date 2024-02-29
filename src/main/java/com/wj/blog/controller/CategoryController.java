package com.wj.blog.controller;


import com.wj.blog.common.result.Result;
import com.wj.blog.model.entity.Category;
import com.wj.blog.model.param.CategoryQueryParam;
import com.wj.blog.model.vo.CategoryVo;
import com.wj.blog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类、标签
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog/category")
public class CategoryController {

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    /**
     * 查询类别列表
     *
     * @param categoryQueryParam 查询过滤参数
     * @return {@link Result}<{@link List}<{@link CategoryVo}>>
     */
    @GetMapping
    public Result<List<CategoryVo>> searchCategoryList(@RequestBody CategoryQueryParam categoryQueryParam) {
        List<Category> categories = categoryService.searchCategoryList(categoryQueryParam);
        List<CategoryVo> categoryVoList = new ArrayList<>();
        if (null != categories) {
            categories.forEach(category -> {
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVoList.add(categoryVo);
            });
        }
        return Result.success(categoryVoList);
    }

    /**
     * 创建分类-多级别分类
     *
     * @param categoryVo 类别实体类
     * @return {@link Result}<{@link String}>
     */
    @PostMapping
    public Result<String> createTag(@RequestBody @Valid CategoryVo categoryVo) {
        categoryService.createTag(categoryVo);
        return Result.success(null);
    }

}
