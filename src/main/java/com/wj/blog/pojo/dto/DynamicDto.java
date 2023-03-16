package com.wj.blog.pojo.dto;

import com.wj.blog.pojo.entity.Category;
import com.wj.blog.pojo.entity.Image;
import com.wj.blog.pojo.entity.Statistics;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class DynamicDto implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private List<CommentDto> commentDtoList;

    @ApiModelProperty("动态标签")
    private List<Category> tags;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
