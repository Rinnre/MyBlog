package com.wj.blog.pojo.dto;

import com.wj.blog.pojo.vo.CommentVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论dto
 *
 * @author wj
 * @date 2023/02/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {


    @ApiModelProperty("评论id")
    private String id;

    @ApiModelProperty("评论父类id")
    private String pid;

    private List<CommentVo> childrenComments;

    @ApiModelProperty("回复用户id")
    private String replayUserId;

    @ApiModelProperty("回复用户名称")
    private String replayUserName;

    @ApiModelProperty("评论内容")
    private String context;

    @ApiModelProperty("评论发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("评论发布人")
    private String userId;
}
