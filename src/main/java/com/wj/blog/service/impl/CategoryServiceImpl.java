package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.CategoryMapper;
import com.wj.blog.model.entity.Category;
import com.wj.blog.model.param.CategoryQueryParam;
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
    public List<Category> searchCategoryList(CategoryQueryParam categoryQueryParam) {
        if (categoryQueryParam != null) {
            Integer page = categoryQueryParam.getPage();
            Integer size = categoryQueryParam.getSize();
            int startNumber;
            if (page != null && size != null) {
                startNumber = (page - 1) * size;
                categoryQueryParam.setPage(startNumber);
            }
        }

        return baseMapper.selectCategoryList(categoryQueryParam);
    }

    @Override
    public void createTag(CategoryVo tagVo) {
        Category category = new Category();
        BeanUtils.copyProperties(tagVo, category);
        baseMapper.insert(category);
    }
}
