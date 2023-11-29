package com.wangqin.stock.vo.request;

import lombok.Data;

@Data
public class LoginReqVo {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * 会话Id
     */
    private String sessionId;
}