package com.wangqin.stock.service;

import com.wangqin.stock.pojo.entity.SysUser;
import com.wangqin.stock.vo.request.LoginReqVo;
import com.wangqin.stock.vo.response.LoginRespVo;
import com.wangqin.stock.vo.response.R;

import java.util.Map;

public interface UserService {

    /**
     * @param username (unique)
     * @return User
     */
    SysUser getUserByUsername(String username);

    /**
     * User login service
     *
     * @param loginReqVo login request
     * @return response vo
     */
    R<LoginRespVo> login(LoginReqVo loginReqVo);

    /**
     * Generate image captcha, convert it to base64 format, and encapsulate it into a response class.
     *
     * @return 响应
     */
    R<Map<String, String>> getCaptchaCode();
}
