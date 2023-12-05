package com.wangqin.stock.config;

import com.wangqin.stock.pojo.vo.StockInfoConfig;
import com.wangqin.stock.utils.IdWorker;
import com.wangqin.stock.utils.ParserStockInfoUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StockInfoConfig.class)//开启常用参数配置bean
public class CommonConfig {

    /**
     * 创建雪花编号生成器对象
     *
     * @return IdWorker
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(2L, 1L);
    }

    /**
     * 创建个股信息转换器对象
     *
     * @return ParserStockInfoUtil
     */
    @Bean
    public ParserStockInfoUtil parserStockInfoUtil() {
        return new ParserStockInfoUtil(idWorker());
    }
}
