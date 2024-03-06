package com.wj.blog.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 *
 * @author wj
 * @date 2023/02/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("个签")
    private String description;

}
