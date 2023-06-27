package com.wj.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 文章、动态数据统计
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("statistics")
@ApiModel(value = "Statistics对象", description = "文章、动态数据统计")
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("点赞次数")
    @TableField("like_count")
    private Integer likeCount;

    @ApiModelProperty("收藏次数")
    @TableField("collect_count")
    private Integer collectCount;

    @ApiModelProperty("评论次数")
    @TableField("comment_count")
    private Integer commentCount;

    @ApiModelProperty("浏览次数")
    @TableField("view_count")
    private Integer viewCount;

    @ApiModelProperty("所属id")
    @TableField("source_id")
    private String sourceId;

    @ApiModelProperty("所属类型")
    @TableField("source_type")
    private Integer sourceType;

    public String getId() {
        return id;
    }

    public Statistics setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Statistics setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public Statistics setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Statistics setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Statistics setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public String getSourceId() {
        return sourceId;
    }

    public Statistics setSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public Statistics setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", likeCount=" + likeCount +
                ", collectCount=" + collectCount +
                ", commentCount=" + commentCount +
                ", viewCount=" + viewCount +
                ", sourceId=" + sourceId +
                ", sourceType=" + sourceType +
                "}";
    }
}
