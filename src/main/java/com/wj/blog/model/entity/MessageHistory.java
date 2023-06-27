package com.wj.blog.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 消息历史
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("message_history")
@ApiModel(value = "MessageHistory对象", description = "消息历史")
public class MessageHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("消息历史id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("发送者")
    @TableField("creator")
    private String creator;

    @ApiModelProperty("消息标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("消息类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("消息内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("是否删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public MessageHistory setId(String id) {
        this.id = id;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public MessageHistory setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageHistory setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public MessageHistory setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageHistory setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public MessageHistory setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        return "MessageHistory{" +
                "id=" + id +
                ", creator=" + creator +
                ", title=" + title +
                ", type=" + type +
                ", content=" + content +
                ", isDelete=" + isDelete +
                "}";
    }
}
