package com.wangqin.stock.service;

public interface StockTimerTaskService {
    /**
     * 获取国内大盘的实时数据信息
     */
    void getInnerMarketInfo();

    /**
     * 定义获取分钟级(个)股票数据
     */
    void getStockRtIndex();

    /**
     * 板块实时数据采集
     */
    void getBlockIndex();
}