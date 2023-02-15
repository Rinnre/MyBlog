package com.wj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录记录
 * </p>
 *
 * @author w
 * @since 2023-02-15
 */
@TableName("user_login_record")
@ApiModel(value = "UserLoginRecord对象", description = "用户登录记录")
public class UserLoginRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("登录记录id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("所属用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("登录类型")
    @TableField("login_type")
    private Integer loginType;

    @ApiModelProperty("ip地址")
    @TableField("ip_address")
    private String ipAddress;

    @ApiModelProperty("IP来源")
    @TableField("ip_resource")
    private Integer ipResource;

    @ApiModelProperty("登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    @ApiModelProperty("是否删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    public String getId() {
        return id;
    }

    public UserLoginRecord setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserLoginRecord setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public UserLoginRecord setLoginType(Integer loginType) {
        this.loginType = loginType;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public UserLoginRecord setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public Integer getIpResource() {
        return ipResource;
    }

    public UserLoginRecord setIpResource(Integer ipResource) {
        this.ipResource = ipResource;
        return this;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public UserLoginRecord setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public UserLoginRecord setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    @Override
    public String toString() {
        return "UserLoginRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", loginType=" + loginType +
                ", ipAddress=" + ipAddress +
                ", ipResource=" + ipResource +
                ", loginTime=" + loginTime +
                ", isDelete=" + isDelete +
                "}";
    }
}
