package com.wj.blog.model.dto;

import com.wj.blog.model.vo.CommentVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论dto
 *
 * @author wj
 * {@code @date} 2023/02/17
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

    @ApiModelProperty("回复用户")
    private UserDto replayUser;

    @ApiModelProperty("评论所属文章、动态、留言")
    @NotBlank(message = "来源id不能为空")
    private String sourceId;

    @ApiModelProperty("所属类型")
    @NotNull(message = "评论所属类型不能为空")
    private Integer sourceType;

    @ApiModelProperty("评论内容")
    @NotBlank(message = "评论内容不能为空")
    @Size(max = 255, message = "评论内容不能超过255个字符")
    private String context;

    @ApiModelProperty("评论发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("评论发布人")
    @NotBlank(message = "评论发布人不能为空")
    private String userId;

    @ApiModelProperty("评论发布人")
    private UserDto user;
}
