package com.wangqin.stock.service;

import com.wangqin.stock.pojo.entity.SysUser;
import com.wangqin.stock.vo.request.LoginReqVo;
import com.wangqin.stock.vo.response.LoginRespVo;
import com.wangqin.stock.vo.response.R;

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
}
