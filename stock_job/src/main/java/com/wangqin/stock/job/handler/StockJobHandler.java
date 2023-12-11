package com.wangqin.stock.job.handler;

import com.wangqin.stock.service.StockTimerTaskService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定义xxljob的任务执行器的Bean
 */
@Component
public class StockJobHandler {

    /**
     * 注入股票定时任务服务bean
     */
    @Autowired
    private StockTimerTaskService stockTimerTaskService;

    /**
     * 定义定时任务，采集国内大盘数据
     */
    @XxlJob("getStockInnerMarketInfos")
    public void getStockInnerMarketInfos(){

        stockTimerTaskService.getInnerMarketInfo();
        System.out.println("定时任务，采集国内大盘数据执行");
    }

    /**
     * 定时采集A股个股数据
     */
    @XxlJob("getStockInfos")
    public void getStockInfos(){
        stockTimerTaskService.getStockRtIndex();
        System.out.println("定时任务，采集A股个股数据执行");
    }

    /**
     * 定时采集A股板块数据
     */
    @XxlJob("getBlockInfos")
    public void getBlockInfos(){
        stockTimerTaskService.getBlockIndex();
        System.out.println("定时任务，采集A股板块数据执行");
    }

    @XxlJob("getStockOuterMarketInfos")
    public void getOuterMarketInfos() {
        stockTimerTaskService.getOuterMarketInfos();
        System.out.println("定时任务，采集外盘数据执行");
    }
}
