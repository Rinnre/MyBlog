package com.wj.blog.controller;


import com.wj.blog.common.result.Result;
import com.wj.blog.model.entity.Category;
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
@RequestMapping("/blog")
public class CategoryController {

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    /**
     * 查询类别列表
     *
     * @param name 名字
     * @param type 类型
     * @param page 页面
     * @param size 大小
     * @return {@link Result}<{@link List}<{@link CategoryVo}>>
     */
    @GetMapping("/category")
    public Result<List<CategoryVo>> searchCategoryList(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) Integer type,
                                                       @RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size) {
        List<Category> categories = categoryService.searchCategoryList(name, type, page, size);
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
     * 创建tag
     *
     * @param tagVo 标签签证官
     * @return {@link Result}<{@link String}>
     */
    @PostMapping("/tag")
    public Result<String> createTag(@RequestBody @Valid CategoryVo tagVo) {
        categoryService.createTag(tagVo);
        return Result.success(null);
    }

}
