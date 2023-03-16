package com.wj.blog.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 记录签vo
 *
 * @author wj
 * @date 2023/03/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordVo {

    @ApiModelProperty("用户")
    private UserVo user;

    @ApiModelProperty("文章")
    private ArticleIntroductionVo article;

    @ApiModelProperty("动态")
    private DynamicVo dynamic;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
