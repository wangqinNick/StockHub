package com.wangqin.stock.service;

import com.wangqin.stock.pojo.domain.InnerMarketDomain;
import com.wangqin.stock.vo.response.R;

import java.util.List;

/**
 * @author by itheima
 * @Date 2021/12/19
 * @Description 定义股票服务接口
 */
public interface StockService {
    //其它省略......

    /**
     * 获取国内大盘的实时数据
     *
     * @return
     */
    R<List<InnerMarketDomain>> innerIndexAll();
}
