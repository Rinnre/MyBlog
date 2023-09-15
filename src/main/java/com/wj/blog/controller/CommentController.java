package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.model.dto.CommentDto;
import com.wj.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 评论
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog/comment")
public class CommentController {

    @Resource(name = "commentServiceImpl")
    private CommentService commentService;

    /**
     * 添加评论
     *
     * @param commentDto 评论dto
     * @return {@link ResultEntity}<{@link String}>
     */
    @PostMapping("/add")
    public ResultEntity<String> addComment(@RequestBody @Valid CommentDto commentDto) {
        commentService.addComment(commentDto);
        return ResultEntity.success();
    }

    /**
     * 删除评论
     *
     * @param id     id
     * @param userId 用户id
     * @return {@link ResultEntity}<{@link String}>
     */
    @DeleteMapping("/{id}/{userId}")
    public ResultEntity<String> removeComment(@PathVariable String id,
                                              @PathVariable String userId) {
        commentService.removeComment(id, userId);
        return ResultEntity.success();
    }
}
