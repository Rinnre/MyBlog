package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.model.entity.Category;
import com.wj.blog.model.param.CategoryQueryParam;
import com.wj.blog.model.vo.CategoryVo;

import java.util.List;

/**
 * <p>
 * 分类、标签表 服务类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询分类列表
     *
     * @param name 名字
     * @param type 类型
     * @param page 页面
     * @param size 大小
     * @return {@link List}<{@link Category}> 分类列表
     */
    List<Category> searchCategoryList(CategoryQueryParam categoryQueryParam);

    /**
     * 创建标签
     *
     * @param tagVo 标签
     */
    void createTag(CategoryVo tagVo);
}
