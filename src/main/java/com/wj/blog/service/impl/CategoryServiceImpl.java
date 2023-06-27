package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.enums.CategoryTypeEnum;
import com.wj.blog.mapper.CategoryMapper;
import com.wj.blog.model.entity.Category;
import com.wj.blog.model.vo.CategoryVo;
import com.wj.blog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类、标签表 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> searchCategoryList(String name, Integer type, Integer page, Integer size) {
        Integer startNumber = null;
        if (page != null && size != null) {
            startNumber = (page - 1) * size;
        }
        return baseMapper.selectCategoryList(name, type, startNumber, size);
    }

    @Override
    public void createTag(CategoryVo tagVo) {
        Category category = new Category();
        BeanUtils.copyProperties(tagVo, category);
        category.setType(CategoryTypeEnum.TAG.getValue());
        baseMapper.insert(category);
    }
}
