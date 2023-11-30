package com.wangqin.stock.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户登录请求实体类")
@Data
public class LoginReqVo {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", position = 1)
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", position = 2)
    private String password;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", position = 3)
    private String code;
    /**
     * 会话Id
     */
    @ApiModelProperty(value = "会话Id", position = 4)
    private String sessionId;
}