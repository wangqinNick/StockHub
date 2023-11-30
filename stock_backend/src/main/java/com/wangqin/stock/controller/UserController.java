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

@Api(value = "/api", tags = "定义用户登录相关接口的控制器")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "string", name = "username", value = "", required = true)
    })
    @ApiOperation(value = "Query user information by user name", notes = "User Query", httpMethod = "GET")
    @GetMapping("/user/{username}")
    public SysUser getUserByUsername(@PathVariable("username") String username) {
        return this.userService.getUserByUsername(username);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "LoginReqVo", name = "loginReqVo", value = "", required = true)
    })
    @ApiOperation(value = "User login", notes = "", httpMethod = "POST")
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo loginReqVo) {
        return this.userService.login(loginReqVo);
    }

    @ApiOperation(value = "Generate captcha code", notes = "", httpMethod = "GET")
    @GetMapping("/captcha")
    public R<Map<String, String>> getCaptchaCode() {
        return userService.getCaptchaCode();
    }
}
