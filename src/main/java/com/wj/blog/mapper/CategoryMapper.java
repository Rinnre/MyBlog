package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.pojo.entity.Category;
import org.apache.ibatis.annotations.Param;

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
     * 查询文章类别
     *
     * @param articleId 文章id
     * @return {@link Category} 文章类别
     */
    Category selectArticleCategory(@Param("articleId") String articleId);
}
