package com.wj.blog.model.dto;

import com.wj.blog.model.entity.Record;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 记录dto
 *
 * @author wj
 * @date 2023/03/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto extends Record {

    @ApiModelProperty("用户")
    private UserDto user;

    @ApiModelProperty("文章")
    private ArticleDto article;

    @ApiModelProperty("动态")
    private DynamicDto dynamic;
}
