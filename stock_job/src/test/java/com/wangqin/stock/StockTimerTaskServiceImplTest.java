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
    public void test01() {
        stockTimerTaskService.getInnerMarketInfo();
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
