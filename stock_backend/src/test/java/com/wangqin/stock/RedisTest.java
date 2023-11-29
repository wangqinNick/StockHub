package com.wangqin.stock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("name", "Tom");
        String name = redisTemplate.opsForValue().get("name");
        Assertions.assertEquals(name, "Tom");
    }
}
