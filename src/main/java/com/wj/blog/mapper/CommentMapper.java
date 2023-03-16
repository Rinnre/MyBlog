package com.wj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wj.blog.pojo.dto.CommentDto;
import com.wj.blog.pojo.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 查询所属类型评论
     *
     * @param sourceId 源id（动态、文章、留言）
     * @return {@link List}<{@link CommentDto}>
     */
    List<CommentDto> selectCommentListById(@Param("sourceId") String sourceId);
}
