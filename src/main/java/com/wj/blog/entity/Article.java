package com.wj.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
 * @since 2022-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Article对象", description="")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章主键")
    @TableId(value = "article_id", type = IdType.ASSIGN_ID)
    private String articleId;

    @ApiModelProperty(value = "作者ID")
    private String authorId;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章主体内容")
    private String articleContext;

    @ApiModelProperty(value = "文章描述")
    private String articleDescription;

    @ApiModelProperty(value = "文章查看次数")
    private Integer articleViewCount;

    @ApiModelProperty(value = "文章评论次数")
    private Integer articleCommentCount;

    @ApiModelProperty(value = "文章喜欢次数")
    private Integer articleLikeCount;

    @ApiModelProperty(value = "文章头图")
    private String articleThumbnail;

    @ApiModelProperty(value = "文章状态（1：草稿，2：待发布，3：已发布）")
    private Integer articleStatus;

    @ApiModelProperty(value = "文章标签")
    @TableField(exist = false)
    private List<Tag> tagList;

    @ApiModelProperty(value = "文章类型")
    @TableField(exist = false)
    private Category category;

    @ApiModelProperty(value = "文章评论")
    @TableField(exist = false)
    private List<Comments> comments;

    @ApiModelProperty(value = "文章发布时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "文章修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除（0：未删除，1：已删除）")
    @TableLogic
    private Boolean isDelete;


}
