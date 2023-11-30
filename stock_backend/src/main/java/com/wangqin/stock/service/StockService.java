package com.wangqin.stock.service;

import com.wangqin.stock.pojo.domain.InnerMarketDomain;
import com.wangqin.stock.pojo.domain.StockBlockDomain;
import com.wangqin.stock.vo.response.R;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel(description = "其它省略......")
public interface StockService {
    //其它省略......

    /**
     * 获取国内大盘的实时数据
     *
     * @return
     */
    R<List<InnerMarketDomain>> innerIndexAll();

    /**
     * 查询沪深两市最新的板块行情数据，并按照交易金额降序排序展示前10条记录
     * @return R 10条板块行情数据
     */
    R<List<StockBlockDomain>> sectorAllLimit(int length);
}
