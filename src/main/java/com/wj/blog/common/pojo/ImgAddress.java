package com.wj.blog.common.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author w
 * @since 2022-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ImgAddress对象", description="")
public class ImgAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "图片地址")
    private String url;

    @ApiModelProperty(value = "图片所属")
    private String linkId;

    @ApiModelProperty(value = "图片上传时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
