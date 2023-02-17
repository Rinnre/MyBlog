package com.wj.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 点赞、收藏记录
 * </p>
 *
 * @author w
 * @since 2023-02-15
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
    private String userId;

    @ApiModelProperty("记录类型")
    @TableField("type")
    private Integer type;

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

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                "}";
    }
}
