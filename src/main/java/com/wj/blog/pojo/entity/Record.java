package com.wj.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 点赞、收藏记录
 * </p>
 *
 * @author w
 * @since 2023-03-08
 */
@TableName("record")
@ApiModel(value = "Record对象", description = "点赞、收藏记录")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    @NotBlank(message = "用户id不允许为空")
    private String userId;

    @ApiModelProperty("记录类型")
    @TableField("type")
    @NotEmpty(message = "类型不允许空")
    private Integer type;

    @ApiModelProperty("所属id")
    @NotBlank(message = "所属id不允许为空")
    @TableField("source_id")
    private String sourceId;

    @ApiModelProperty("所属id")
    @TableField("create_time")
    private LocalDateTime createTime;

    public String getId() {
        return id;
    }

    public Record setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Record setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Record setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getSourceId() {
        return sourceId;
    }

    public Record setSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", sourceId=" + sourceId +
                "}";
    }
}
