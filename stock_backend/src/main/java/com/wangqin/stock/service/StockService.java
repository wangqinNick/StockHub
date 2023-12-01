package com.wangqin.stock.service;

import com.wangqin.stock.pojo.domain.InnerMarketDomain;
import com.wangqin.stock.pojo.domain.StockBlockDomain;
import com.wangqin.stock.pojo.domain.StockUpdownDomain;
import com.wangqin.stock.vo.response.PageResult;
import com.wangqin.stock.vo.response.R;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.Map;

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
     *
     * @return R 10条板块行情数据
     */
    R<List<StockBlockDomain>> sectorAllLimit(int length);

    /**
     * 分页查询沪深两市股票最新数据, 并按照涨幅排序
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示数量
     * @return R
     */
    R<PageResult<StockUpdownDomain>> pagingStockInfo(Integer pageNum, Integer pageSize);

    /**
     * 查询并返回涨幅榜排名前K个股票信息
     *
     * @param topK 前K个
     * @return R
     */
    R<List<StockUpdownDomain>> topStocks(int topK);

    /**
     * 统计股票(最新交易日内)涨跌停的股票数量
     *
     * @return R
     */
    R<Map<String, List<Map<String, String>>>>  getStockUpDownCount();
}
