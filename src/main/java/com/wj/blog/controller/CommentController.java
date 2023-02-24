package com.wj.blog.controller;


import com.wj.blog.common.result.ResultEntity;
import com.wj.blog.pojo.dto.CommentDto;
import com.wj.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/blog")
public class CommentController {

    @Resource(name = "commentService")
    private CommentService commentService;

    @PostMapping("comment")
    public ResultEntity<String> addComment(@RequestBody CommentDto commentDto) {
        commentService.addComment(commentDto);
        return ResultEntity.success();
    }

    @DeleteMapping("comment/{id}/{userId}")
    public ResultEntity<String> removeComment(@PathVariable String id,
                                              @PathVariable String userId) {
        commentService.removeComment(id, userId);
        return ResultEntity.success();
    }
}
