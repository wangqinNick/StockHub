package com.wangqin.stock;

import com.wangqin.stock.service.StockTimerTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class StockTimerTaskServiceImplTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockTimerTaskService stockTimerTaskService;

    @Test
    public void test01() throws InterruptedException {
        stockTimerTaskService.getInnerMarketInfo();
        Thread.sleep(5000); // 让主线程休眠, 等待子线程执行任务
    }

    @Test
    public void test02() {
        stockTimerTaskService.getStockRtIndex();
    }

    @Test
    public void test03() {
        stockTimerTaskService.getBlockIndex();
    }

}
