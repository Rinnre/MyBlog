package com.wj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("article")
@ApiModel(value = "Article对象", description = "文章")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("作者id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("文章标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("文章主体内容")
    @TableField("context")
    private String context;

    @ApiModelProperty("文章头图")
    @TableField("thumbnail")
    private String thumbnail;

    @ApiModelProperty("文章分类id")
    @TableField("category_id")
    private String categoryId;

    @ApiModelProperty("文章密码")
    @TableField("article_password")
    private String articlePassword;

    @ApiModelProperty("文章转载链接")
    @TableField("source_link")
    private String sourceLink;

    @ApiModelProperty("文章状态（1：草稿，2：待发布，3：已发布）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("文章创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("文章修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("文章发布时间")
    @TableField("release_time")
    private LocalDateTime releaseTime;

    @ApiModelProperty("逻辑删除（0：未删除，1：已删除）")
    @TableField("is_delete")
    @TableLogic
    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public Article setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Article setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContext() {
        return context;
    }

    public Article setContext(String context) {
        this.context = context;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Article setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Article setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getArticlePassword() {
        return articlePassword;
    }

    public Article setArticlePassword(String articlePassword) {
        this.articlePassword = articlePassword;
        return this;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public Article setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Article setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Article setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Article setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public Article setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
        return this;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public Article setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userId=" + userId +
                ", title=" + title +
                ", context=" + context +
                ", thumbnail=" + thumbnail +
                ", categoryId=" + categoryId +
                ", articlePassword=" + articlePassword +
                ", sourceLink=" + sourceLink +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", releaseTime=" + releaseTime +
                ", isDelete=" + isDelete +
                "}";
    }
}
