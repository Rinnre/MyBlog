package com.wj.blog.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 图片地址存储
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("image")
@ApiModel(value = "Image对象", description = "图片地址存储")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("图片地址")
    @TableField("url")
    private String url;

    @ApiModelProperty("图片所属")
    @TableField("source_id")
    private String sourceId;

    @ApiModelProperty("图片所属类型")
    @TableField("source_type")
    private Integer sourceType;

    @ApiModelProperty("图片上传时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("图片修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除（0：未删除，1：已删除）")
    @TableField("is_delete")
    @TableLogic
    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public Image setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Image setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getSourceId() {
        return sourceId;
    }

    public Image setSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public Image setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Image setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public Image setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public Image setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url=" + url +
                ", sourceId=" + sourceId +
                ", sourceType=" + sourceType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                "}";
    }
}
