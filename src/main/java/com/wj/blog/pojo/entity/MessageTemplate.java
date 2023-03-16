package com.wj.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息模板
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("message_template")
@ApiModel(value = "MessageTemplate对象", description = "消息模板")
public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("模板id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("模板名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    public String getId() {
        return id;
    }

    public MessageTemplate setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MessageTemplate setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageTemplate setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageTemplate setContent(String content) {
        this.content = content;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public MessageTemplate setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public MessageTemplate setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "MessageTemplate{" +
                "id=" + id +
                ", name=" + name +
                ", title=" + title +
                ", content=" + content +
                ", createTime=" + createTime +
                ", remark=" + remark +
                "}";
    }
}
