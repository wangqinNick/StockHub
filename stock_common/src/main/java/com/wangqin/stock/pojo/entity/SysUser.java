package com.wangqin.stock.pojo.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户表
 * @TableName sys_user
 */
@Data
@ApiModel(description = "Basic information about the user")
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "Primary key Id")
    private Long id;

    /**
     * 账户
     */
    @ApiModelProperty(value = "User's login name")
    private String username;

    /**
     * 用户密码密文
     */
    @ApiModelProperty(value = "User's password")
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "User's phone number")
    private String phone;

    /**
     * 真实名称
     */
    @ApiModelProperty(value = "User's real name")
    private String realName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "User's nickname")
    private String nickName;

    /**
     * 邮箱(唯一)
     */
    @ApiModelProperty(value = "User's email")
    private String email;

    /**
     * 账户状态(1.正常 2.锁定 )
     */
    @ApiModelProperty(value = "The status of the user's account")
    private Integer status;

    /**
     * 性别(1.男 2.女)
     */
    @ApiModelProperty(value = "User's gender")
    private Integer sex;

    /**
     * 是否删除(1未删除；0已删除)
     */
    private Integer deleted;

    /**
     * 创建人
     */
    private Long createId;

    /**
     * 更新人
     */
    private Long updateId;

    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    private Integer createWhere;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}