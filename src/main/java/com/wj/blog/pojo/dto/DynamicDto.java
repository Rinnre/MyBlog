package com.wj.blog.pojo.dto;

import com.wj.blog.pojo.entity.Image;
import com.wj.blog.pojo.entity.Statistics;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 动态dto
 *
 * @author wj
 * @date 2023/02/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicDto {

    @ApiModelProperty("动态id")
    private String id;

    @ApiModelProperty("内容")
    private String context;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("发布人")
    private UserDto user;

    @ApiModelProperty("发布人id")
    private String userId;

    @ApiModelProperty("动态图片")
    private List<Image> images;

    @ApiModelProperty("动态数据")
    private Statistics statistics;

    @ApiModelProperty("动态评论")
    private CommentDto commentDto;
}
