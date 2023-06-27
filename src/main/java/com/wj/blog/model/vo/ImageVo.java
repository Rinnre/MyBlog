package com.wj.blog.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片vo
 *
 * @author wj
 * @date 2023/02/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageVo {

    @ApiModelProperty("图片id")
    private String id;

    @ApiModelProperty("图片地址")
    private String url;

}
