package com.wj.blog.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@ApiModel(value="Tag对象", description="")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类id")
    @TableId(value = "tag_id", type = IdType.ASSIGN_ID)
    private String tagId;

    @ApiModelProperty(value = "分类名称")
    private String tagName;


}
