package com.wj.blog.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 友链vo对象
 *
 * @author wj
 * @date 2023/02/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {

    @ApiModelProperty("友链主键")
    private String id;

    @ApiModelProperty("博主名称")
    @NotBlank(message = "博主名称不能为空")
    private String nickName;

    @ApiModelProperty("友链地址")
    @NotBlank(message = "友链地址不能为空")
    private String url;

    @ApiModelProperty("博主简介")
    private String description;

    @ApiModelProperty("博主头像")
    @TableField("avatar")
    private String avatar;
}
