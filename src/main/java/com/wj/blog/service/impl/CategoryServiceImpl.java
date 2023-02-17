package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.mapper.CategoryMapper;
import com.wj.blog.pojo.entity.Category;
import com.wj.blog.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类、标签表 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
