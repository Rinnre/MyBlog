package com.wj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 友链
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("link")
@ApiModel(value = "Link对象", description = "友链")
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("友链主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("博主名称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty("博客链接状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("友链地址")
    @TableField("url")
    private String url;

    @ApiModelProperty("博主简介")
    @TableField("description")
    private String description;

    @ApiModelProperty("申请用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("逻辑删除（0：未删除，1：已删除）")
    @TableField("is_delete")
    @TableLogic
    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public Link setId(String id) {
        this.id = id;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public Link setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Link setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Link setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Link setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Link setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Link setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Link setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public Link setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", nickName=" + nickName +
                ", status=" + status +
                ", url=" + url +
                ", description=" + description +
                ", userId=" + userId +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                "}";
    }
}
