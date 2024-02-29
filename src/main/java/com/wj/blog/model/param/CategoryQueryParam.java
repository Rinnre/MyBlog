package com.wj.blog.model.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author w
 * {@code @time:} 14:04
 * Description:
 */
public class CategoryQueryParam extends QueryParam {

    @ApiModelProperty("分类名称")
    private String name;
}
