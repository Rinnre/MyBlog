package com.wj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息接收历史
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("message_recipient")
@ApiModel(value = "MessageRecipient对象", description = "消息接收历史")
public class MessageRecipient implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("消息接收id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("消息id")
    @TableField("message_id")
    private String messageId;

    @ApiModelProperty("接收人")
    @TableField("recipient_id")
    private String recipientId;

    @ApiModelProperty("发送方式")
    @TableField("contact_way")
    private Integer contactWay;

    @ApiModelProperty("阅读时间")
    @TableField("read_time")
    private LocalDateTime readTime;

    @ApiModelProperty("是否删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public MessageRecipient setId(String id) {
        this.id = id;
        return this;
    }

    public String getMessageId() {
        return messageId;
    }

    public MessageRecipient setMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public MessageRecipient setRecipientId(String recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public Integer getContactWay() {
        return contactWay;
    }

    public MessageRecipient setContactWay(Integer contactWay) {
        this.contactWay = contactWay;
        return this;
    }

    public LocalDateTime getReadTime() {
        return readTime;
    }

    public MessageRecipient setReadTime(LocalDateTime readTime) {
        this.readTime = readTime;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public MessageRecipient setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        return "MessageRecipient{" +
                "id=" + id +
                ", messageId=" + messageId +
                ", recipientId=" + recipientId +
                ", contactWay=" + contactWay +
                ", readTime=" + readTime +
                ", isDelete=" + isDelete +
                "}";
    }
}
