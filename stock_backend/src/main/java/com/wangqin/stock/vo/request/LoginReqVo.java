package com.wangqin.stock.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Wrapper entity class for user login request information")
public class LoginReqVo {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "User's username in the login request")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "User's password in the login request")
    private String password;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "User's captcha code in the login request")
    private String code;
    /**
     * 会话Id
     */
    @ApiModelProperty(value = "Login request's session id")
    private String sessionId;
}