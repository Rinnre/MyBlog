package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.pojo.entity.Category;
import com.wj.blog.pojo.vo.CategoryVo;
import com.wj.blog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分类、标签表 前端控制器
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog")
public class CategoryController {

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResultEntity<List<CategoryVo>> searchCategoryList(@RequestParam(required = false) String name,
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
        return ResultEntity.success(categoryVoList);
    }

}
