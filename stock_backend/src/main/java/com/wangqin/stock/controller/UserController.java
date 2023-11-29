package com.wangqin.stock.controller;

import com.wangqin.stock.pojo.entity.SysUser;
import com.wangqin.stock.service.UserService;
import com.wangqin.stock.vo.request.LoginReqVo;
import com.wangqin.stock.vo.response.LoginRespVo;
import com.wangqin.stock.vo.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = "User authentication related APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    @ApiOperation(value = "Query user information by user name", notes = "User Query", response = SysUser.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username", required = true, paramType = "path")
    })
    public SysUser getUserByUsername(@PathVariable("username") String username) {
        return this.userService.getUserByUsername(username);
    }

    @PostMapping("/login")
    @ApiOperation(value = "User login", response = R.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginReqVo", value = "Wrapper entity class for user login request information", required = true, paramType = "body")
    })
    public R<LoginRespVo> login(@RequestBody LoginReqVo loginReqVo) {
        return this.userService.login(loginReqVo);
    }

    @GetMapping("/captcha")
    @ApiOperation(value = "Generate captcha code", response = R.class)
    public R<Map<String, String>> getCaptchaCode() {
        return userService.getCaptchaCode();
    }
}
