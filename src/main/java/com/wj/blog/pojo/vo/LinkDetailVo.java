package com.wj.blog.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
@EqualsAndHashCode(callSuper = true)
public class LinkDetailVo extends LinkVo {

    @ApiModelProperty("申请人id")
    @NotBlank(message = "申请人id不能为空")
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
