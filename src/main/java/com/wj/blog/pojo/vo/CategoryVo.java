package com.wj.blog.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类别Vo
 *
 * @author wj
 * @date 2023/02/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {

    @ApiModelProperty("分类ID")
    private String id;

    @ApiModelProperty("分类名称")
    private String name;


}
