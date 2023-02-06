package com.wj.blog.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author w
 * @since 2022-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String nickName;

    private String userPhone;

    private String userEmail;

    private String userPassword;

    private Integer userGender;

    private String userAvatar;

    private Integer userIsAdmin;

    private String userGithubAddress;

    private String userQq;

    private String userWechat;


}
