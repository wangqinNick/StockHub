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
@ApiModel(description = "用户表")
@Data
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", position = 1)
    private Long id;

    /**
     * 账户
     */
    @ApiModelProperty(value = "账户", position = 2)
    private String username;

    /**
     * 用户密码密文
     */
    @ApiModelProperty(value = "用户密码密文", position = 3)
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", position = 4)
    private String phone;

    /**
     * 真实名称
     */
    @ApiModelProperty(value = "真实名称", position = 5)
    private String realName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", position = 6)
    private String nickName;

    /**
     * 邮箱(唯一)
     */
    @ApiModelProperty(value = "邮箱(唯一)", position = 7)
    private String email;

    /**
     * 账户状态(1.正常 2.锁定 )
     */
    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )", position = 8)
    private Integer status;

    /**
     * 性别(1.男 2.女)
     */
    @ApiModelProperty(value = "性别(1.男 2.女)", position = 9)
    private Integer sex;

    /**
     * 是否删除(1未删除；0已删除)
     */
    @ApiModelProperty(value = "是否删除(1未删除；0已删除)", position = 10)
    private Integer deleted;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", position = 11)
    private Long createId;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", position = 12)
    private Long updateId;

    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    @ApiModelProperty(value = "创建来源(1.web 2.android 3.ios )", position = 13)
    private Integer createWhere;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", position = 14)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", position = 15)
    private Date updateTime;

    @ApiModelProperty(hidden = true)
    private static final long serialVersionUID = 1L;
}