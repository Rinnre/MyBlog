package com.wj.blog.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户dto
 *
 * @author wj
 * @date 2023/02/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("简介")
    private String description;
}
