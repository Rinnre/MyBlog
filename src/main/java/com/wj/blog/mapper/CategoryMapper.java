package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.model.entity.Category;
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
     * @param name        名字
     * @param type        类型
     * @param startNumber 开始页数
     * @param size        页面大小
     * @return {@link List}<{@link Category}> 分类列表
     */
    List<Category> selectCategoryList(@Param("name") String name,
                                      @Param("type") Integer type,
                                      @Param("startNumber") Integer startNumber,
                                      @Param("size") Integer size);

}
