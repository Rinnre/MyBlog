package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.pojo.dto.ArticleDto;
import com.wj.blog.pojo.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询文章列表
     *
     * @param title           标题
     * @param author          作者
     * @param startPageNumber 开始页码
     * @param size            大小
     * @return {@link List}<{@link ArticleDto}>
     */
    List<ArticleDto> selectArticleList(@Param("title") String title,
                                       @Param("author") String author,
                                       @Param("startPageNumber") Integer startPageNumber,
                                       @Param("size") Integer size);

}
