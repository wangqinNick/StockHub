package com.wangqin.stock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class BackendTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 测试密码加密匹配器
     */
    @Test
    public void testPasswordEncoder() {
        String pwd = "1234";
        // 加密密码
        String encoded = passwordEncoder.encode(pwd);
        // 测试明文密码密文密码匹配
        Assertions.assertTrue(passwordEncoder.matches(pwd, encoded));
    }
}
