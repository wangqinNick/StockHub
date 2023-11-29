package com.wangqin.stock.controller;

import com.wangqin.stock.pojo.entity.SysUser;
import com.wangqin.stock.service.UserService;
import com.wangqin.stock.vo.request.LoginReqVo;
import com.wangqin.stock.vo.response.LoginRespVo;
import com.wangqin.stock.vo.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public SysUser getUserByUsername(@PathVariable("username") String username) {
        return this.userService.getUserByUsername(username);
    }

    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo loginReqVo) {
        return this.userService.login(loginReqVo);
    }

    @GetMapping("/captcha")
    public R<Map> getCaptchaCode() {
        return userService.getCaptchaCode();
    }
}
