package com.wj.blog.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论Vo对象
 *
 * @author wj
 * @date 2023/02/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsVo {

    @ApiModelProperty("评论id")
    private String id;

    @ApiModelProperty("评论父类id")
    private String pid;

    private List<CommentsVo> childrenComments;

    @ApiModelProperty("回复用户id")
    private String replayUserId;

    @ApiModelProperty("评论内容")
    private String context;

    @ApiModelProperty("评论发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("评论发布人")
    private String userId;
}
