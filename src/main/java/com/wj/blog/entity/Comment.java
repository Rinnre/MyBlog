package com.wj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("comment")
@ApiModel(value = "Comment对象", description = "评论")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("评论父类id")
    @TableField("pid")
    private String pid;

    @ApiModelProperty("回复用户id")
    @TableField("replay_user_id")
    private String replayUserId;

    @ApiModelProperty("评论所属文章、动态、留言")
    @TableField("source_id")
    private String sourceId;

    @ApiModelProperty("所属类型")
    @TableField("source_type")
    private Integer sourceType;

    @ApiModelProperty("评论内容")
    @TableField("context")
    private String context;

    @ApiModelProperty("评论发布时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("评论发布人")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("评论状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("是否删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public Comment setId(String id) {
        this.id = id;
        return this;
    }

    public String getPid() {
        return pid;
    }

    public Comment setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getReplayUserId() {
        return replayUserId;
    }

    public Comment setReplayUserId(String replayUserId) {
        this.replayUserId = replayUserId;
        return this;
    }

    public String getSourceId() {
        return sourceId;
    }

    public Comment setSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public Comment setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public String getContext() {
        return context;
    }

    public Comment setContext(String context) {
        this.context = context;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Comment setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Comment setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Comment setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public Comment setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", pid=" + pid +
                ", replayUserId=" + replayUserId +
                ", sourceId=" + sourceId +
                ", sourceType=" + sourceType +
                ", context=" + context +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", status=" + status +
                ", isDelete=" + isDelete +
                "}";
    }
}
