package com.wj.blog.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 友链详情Vo对象
 *
 * @author wj
 * @date 2023/02/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDetailVo extends LinkVo {

    @ApiModelProperty("申请人id")
    private String userId;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("申请用户名称")
    private String userName;

    @ApiModelProperty("博客链接状态")
    private String status;
}
