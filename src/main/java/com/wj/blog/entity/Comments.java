package com.wj.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author w
 * @since 2022-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Comments对象", description="")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id")
    @TableId(value = "comment_id", type = IdType.ASSIGN_ID)
    private String commentId;

    @ApiModelProperty(value = "评论父类id")
    private String commentPid;

    @ApiModelProperty(value = "评论所属文章")
    private Integer commentArticleId;

    @ApiModelProperty(value = "评论内容")
    private String commentContext;

    @ApiModelProperty(value = "评论发布时间")
    private Date commentCreateTime;

    @ApiModelProperty(value = "评论发布人")
    private Integer commentUserId;


}
