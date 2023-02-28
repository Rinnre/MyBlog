package com.wj.blog.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 动态
 *
 * @author wj
 * @date 2023/02/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicVo {

    @ApiModelProperty("动态id")
    private String id;

    @ApiModelProperty("内容")
    @NotBlank(message = "动态内容不能为空")
    @Size(min = 0, max = 255, message = "内容不得超过255个字符")
    private String context;

    @ApiModelProperty("发布人id")
    @NotBlank(message = "发布人id不能为空")
    private String userId;

    @ApiModelProperty("图片墙")
    private List<ImageVo> imageVoList;

    @ApiModelProperty("动态数据")
    private StatisticsVo statisticsVo;

    @ApiModelProperty("动态评论")
    private List<CommentVo> commentVo;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


}
