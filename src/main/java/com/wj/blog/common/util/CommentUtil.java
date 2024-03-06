package com.wj.blog.common.util;

import com.wj.blog.model.dto.CommentDto;
import com.wj.blog.model.vo.CommentDetailVo;
import com.wj.blog.model.vo.UserVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论工具类
 *
 * @author wj
 * @date 2023/02/28
 */
public class CommentUtil {
    public static List<CommentDetailVo> assembleComment(List<CommentDto> comments) {
        comments = comments.stream()
                .sorted(Comparator.comparing(CommentDto::getCreateTime))
                .collect(Collectors.toList());
        List<CommentDetailVo> commentRootVos = new ArrayList<>();
        // 遍历找出根评论
        comments.forEach(comment -> {
            if (comment.getPid() == null) {
                convertDtoToVo(commentRootVos, comment);
            }
        });

        // 遍历寻找子节点
        for (CommentDetailVo commentRoot : commentRootVos) {
            List<CommentDetailVo> commentChildrenVos = new ArrayList<>();
            comments.forEach(comment -> {
                if (commentRoot.getId().equals(comment.getPid())) {
                    convertDtoToVo(commentChildrenVos, comment);
                }
            });
            commentRoot.setChildrenComments(commentChildrenVos);
        }

        return commentRootVos;
    }

    private static void convertDtoToVo(List<CommentDetailVo> commentChildrenVos, CommentDto comment) {
        CommentDetailVo commentVo = new CommentDetailVo();
        BeanUtils.copyProperties(comment, commentVo);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(comment.getUser(), userVo);
        commentVo.setUser(userVo);
        userVo = new UserVo();
        if (comment.getReplayUser() != null) {
            BeanUtils.copyProperties(comment.getReplayUser(), userVo);
        }
        commentVo.setReplayUser(userVo);
        commentChildrenVos.add(commentVo);
    }
}
