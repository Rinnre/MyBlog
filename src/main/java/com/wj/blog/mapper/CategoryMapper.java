package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.model.entity.Category;
import com.wj.blog.model.param.CategoryQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 分类、标签表 Mapper 接口
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface CategoryMapper extends BaseMapper<Category> {


    /**
     * 查询分类列表
     *
     * @param categoryQueryParam 类别列表过滤参数
     * @return {@link List}<{@link Category}>
     */
    List<Category> selectCategoryList(@Param("categoryParam") CategoryQueryParam categoryQueryParam);

}
