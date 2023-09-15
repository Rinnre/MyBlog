package com.wj.blog.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 友链dto
 *
 * @author wj
 * @date 2023/02/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDto {

    @ApiModelProperty("友链主键")
    private String id;

    @ApiModelProperty("博主名称")
    @NotBlank
    private String nickName;

    @ApiModelProperty("博主头像")
    private String avatar;

    @ApiModelProperty("博客链接状态")
    private Integer status;

    @ApiModelProperty("友链地址")
    private String url;

    @ApiModelProperty("博主简介")
    private String description;

    @ApiModelProperty("申请用户id")
    @NotBlank
    private String userId;

    @ApiModelProperty("申请用户名称")
    private String userName;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
