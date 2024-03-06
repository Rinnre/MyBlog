package com.wj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.blog.model.dto.CommentDto;
import com.wj.blog.model.entity.Comment;


/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
public interface CommentService extends IService<Comment> {

    /**
     * 添加评论
     *
     * @param commentDto 评论dto
     */
    void addComment(CommentDto commentDto);

    /**
     * 删除评论
     *
     * @param id     id
     * @param userId 用户id
     */
    void removeComment(String id, String userId);

}
