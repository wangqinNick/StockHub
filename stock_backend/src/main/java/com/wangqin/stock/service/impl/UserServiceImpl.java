package com.wangqin.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import com.wangqin.stock.constant.StockConstant;
import com.wangqin.stock.mapper.SysUserMapper;
import com.wangqin.stock.pojo.entity.SysUser;
import com.wangqin.stock.service.UserService;
import com.wangqin.stock.utils.IdWorker;
import com.wangqin.stock.vo.request.LoginReqVo;
import com.wangqin.stock.vo.response.LoginRespVo;
import com.wangqin.stock.vo.response.R;
import com.wangqin.stock.vo.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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

        // 检查校验码是否为空
        if (StringUtils.isBlank(loginReqVo.getCode())) {
            return R.error(ResponseCode.CHECK_CODE_NOT_EMPTY);
        }

        // 检查sessionId是否为空
        if (StringUtils.isBlank(loginReqVo.getSessionId())) {
            return R.error(ResponseCode.DATA_ERROR);
        }

        // 判断redis中保存的校验码是否和用户输入的校验码一致 (忽略大小写)
        String redisCode = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + loginReqVo.getSessionId());
        // 判断验证码是否过期
        if (StringUtils.isBlank(redisCode)) {
            return R.error(ResponseCode.CHECK_CODE_TIMEOUT);
        }

        if (!redisCode.equalsIgnoreCase(loginReqVo.getCode())) {
            return R.error(ResponseCode.CHECK_CODE_ERROR);
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

    /**
     * Generate image captcha, convert it to base64 format, and encapsulate it into a response class.
     *
     * @return 响应
     */
    @Override
    public R<Map<String, String>> getCaptchaCode() {
        // 生成gif验证码
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(250, 40, 4);
        //LineCaptcha gifCaptcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);

        gifCaptcha.setBackground(Color.LIGHT_GRAY);

        // 获取数字验证码
        String code = gifCaptcha.getCode();

        // 获取经过base64编码处理的图片数据
        String gifCaptchaBase64 = gifCaptcha.getImageBase64();

        // 生成SessionId
        // 转为String, 避免Id过长被截取, 精度丢失
        String sessionId = String.valueOf(idWorker.nextId());

        log.info("当前生成的图片校验码为 {}, 会话id为 {}", code, sessionId);

        // 后台将sessionId作为key, 校验码作为value, 保存在redis中
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX + sessionId, code, StockConstant.TIMEOUT, TimeUnit.MINUTES);

        // 组装响应数据
        Map<String, String> data = new HashMap<>();
        data.put("sessionId", sessionId);
        data.put("imageData", gifCaptchaBase64);

        return R.ok(data);
    }
}
