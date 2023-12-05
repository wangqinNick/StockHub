package com.wangqin.stock.config;

import com.wangqin.stock.pojo.vo.StockInfoConfig;
import com.wangqin.stock.utils.IdWorker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StockInfoConfig.class)//开启常用参数配置bean
public class CommonConfig {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(2L, 1L);
    }
}
