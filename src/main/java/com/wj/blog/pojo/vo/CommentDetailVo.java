package com.wj.blog.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 评论详情
 *
 * @author wj
 * @date 2023/02/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailVo extends CommentVo {

    @ApiModelProperty("回复用户")
    private UserVo replayUser;

    @ApiModelProperty("评论发布人")
    private UserVo user;

    @ApiModelProperty("子评论")
    private List<CommentDetailVo> childrenComments;
}
