package com.wangqin.stock.service;

import com.wangqin.stock.pojo.domain.*;
import com.wangqin.stock.vo.response.PageResult;
import com.wangqin.stock.vo.response.R;
import io.swagger.annotations.ApiModel;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@ApiModel(description = "其它省略......")
public interface StockService {
    //其它省略......

    /**
     * 获取国内大盘的实时数据
     *
     * @return R
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
    R<Map<String, List<Map<String, String>>>> getStockUpDownCount();

    /**
     * 将指定页的股票信息导出为Excel表
     *
     * @param response HttpResponse
     * @param page     Page no.
     * @param pageSize page size
     */
    void stockExport(HttpServletResponse response, Integer page, Integer pageSize);

    /**
     * 统计A股大盘T日和T-1日成交量对比功能（成交量为沪深两市成交量之和）
     *
     * @return R
     */
    R<Map<String, List<Map<String, String>>>> getStockTradeAmt4InnerMarketCompared();

    /**
     * 查询当前时间下股票的涨跌幅度区间统计功能
     * 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询点
     *
     * @return R
     */
    R<Map<String, Object>> getStockRangeCount();

    /**
     * 查询单个个股的分时行情数据，也就是统计指定股票T日每分钟的交易数据；
     * 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点
     *
     * @param code 股票代码
     * @return R
     */
    R<List<Stock4MinuteDomain>> getStockScreenTimeSharing(String code);

    /**
     * 单个个股日K数据查询
     *
     * @param code 股票代码
     * @return R
     */
    R<List<Stock4DayDomain>> getDayKLineData(String code);

    /**
     * 获取个股最新分时行情数据，主要包含：
     * 开盘价、前收盘价、最新价、最高价、最低价、成交金额和成交量、交易时间信息;
     *
     * @param code 股票代码
     * @return R
     */
    R<StockRtDomain> getStockRtDetail(String code);

    /**
     * 个股交易流水行情数据查询--查询最新交易流水，按照交易时间降序取前10
     *
     * @param code 股票代码
     * @return R
     */
    R<List<SimpleStockRtDomain>> getStockSecond(String code);
}
