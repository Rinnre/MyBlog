package com.wj.blog.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 动态vo
 *
 * @author wj
 * @date 2023/02/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicDetailVo extends DynamicVo {

    @ApiModelProperty("发布人信息")
    private UserVo userVo;

    @ApiModelProperty("动态评论")
    private List<CommentDetailVo> commentVoList;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("动态标签")
    private List<CategoryVo> categoryVoList;
}
