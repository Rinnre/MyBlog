package com.wj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 动态表
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("dynamic")
@ApiModel(value = "Dynamic对象", description = "动态表")
public class Dynamic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("动态id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("内容")
    @TableField("context")
    private String context;

    @ApiModelProperty("状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("发布人")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    public String getId() {
        return id;
    }

    public Dynamic setId(String id) {
        this.id = id;
        return this;
    }

    public String getContext() {
        return context;
    }

    public Dynamic setContext(String context) {
        this.context = context;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Dynamic setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Dynamic setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Dynamic setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Dynamic setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "Dynamic{" +
                "id=" + id +
                ", context=" + context +
                ", status=" + status +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
