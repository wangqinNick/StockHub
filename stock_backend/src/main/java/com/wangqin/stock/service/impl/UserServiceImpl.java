package com.wangqin.stock.service.impl;

import com.wangqin.stock.mapper.SysUserMapper;
import com.wangqin.stock.pojo.entity.SysUser;
import com.wangqin.stock.service.UserService;
import com.wangqin.stock.vo.request.LoginReqVo;
import com.wangqin.stock.vo.response.LoginRespVo;
import com.wangqin.stock.vo.response.R;
import com.wangqin.stock.vo.response.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param username (unique)
     * @return User
     */
    @Override
    public SysUser getUserByUsername(String username) {
        return sysUserMapper.getByUsername(username);
    }

    /**
     * User login service
     *
     * @param loginReqVo login request
     * @return response vo
     */
    @Override
    public R<LoginRespVo> login(LoginReqVo loginReqVo) {
        // 1. 判断请求参数是否合法
        if (loginReqVo == null
                || StringUtils.isBlank(loginReqVo.getUsername())
                || StringUtils.isBlank(loginReqVo.getPassword())
                || StringUtils.isBlank(loginReqVo.getCode())) {
            return R.error(ResponseCode.DATA_ERROR);
        }

        // 2. 根据用户名去数据库查询用户信息, 获取密码的加密后密文
        SysUser dbUser = sysUserMapper.getByUsername(loginReqVo.getUsername());
        if (dbUser == null) {
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }

        // 3. 用密码匹配器去比对登录请求中的密码与数据库中密码的加密后密文
        if (!passwordEncoder.matches(loginReqVo.getPassword(), dbUser.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }

        // 4. 根据第三步结果进行响应
        LoginRespVo loginRespVo = new LoginRespVo();
        BeanUtils.copyProperties(dbUser, loginRespVo);
        return R.ok(loginRespVo);
    }
}
