package com.wj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.blog.common.aop.annation.NumberCount;
import com.wj.blog.mapper.CommentMapper;
import com.wj.blog.model.dto.CommentDto;
import com.wj.blog.model.entity.Comment;
import com.wj.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @NumberCount(mode = "addComment")
    @Override
    public void addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        baseMapper.insert(comment);
    }

    @NumberCount(mode = "subComment")
    @Override
    public void removeComment(String id, String userId) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getPid, id)
                .or().eq(Comment::getId, id).eq(Comment::getUserId, userId);
        List<Comment> comments = baseMapper.selectList(commentLambdaQueryWrapper);
        List<String> ids = new ArrayList<>();
        comments.forEach(comment ->
                ids.add(comment.getId())
        );
        baseMapper.deleteBatchIds(ids);
    }


}
