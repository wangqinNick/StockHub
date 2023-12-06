package com.wangqin.stock.controller;

import com.wangqin.stock.pojo.domain.*;
import com.wangqin.stock.service.StockService;
import com.wangqin.stock.vo.response.PageResult;
import com.wangqin.stock.vo.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.wangqin.stock.constant.StockConstant.LIMIT;
import static com.wangqin.stock.constant.StockConstant.TOP_K;

@Api("/api/quot")
@RestController
@RequestMapping("/api/quot")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 获取国内最新大盘指数
     *
     * @return R 响应体
     */
    @ApiOperation(value = "获取国内最新大盘指数", notes = "获取国内最新大盘指数", httpMethod = "GET")
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> innerIndexAll() {
        return stockService.innerIndexAll();
    }

    /**
     * 查询沪深两市最新的板块行情数据，并按照交易金额降序排序展示前10条记录
     *
     * @return 10条板块行情数据
     */
    @ApiOperation(value = "查询沪深两市最新的板块行情数据，并按照交易金额降序排序展示前10条记录", notes = "查询沪深两市最新的板块行情数据，并按照交易金额降序排序展示前10条记录", httpMethod = "GET")
    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAll() {
        return stockService.sectorAllLimit(LIMIT);
    }

    /**
     * 分页查询沪深两市股票最新数据, 并按照涨幅排序
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示数量
     * @return R
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "当前页码"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "每页显示数量")
    })
    @ApiOperation(value = "分页查询沪深两市股票最新数据, 并按照涨幅排序", notes = "分页查询沪深两市股票最新数据, 并按照涨幅排序", httpMethod = "GET")
    @GetMapping("/stock/all")
    public R<PageResult<StockUpdownDomain>> pagingStockInfo(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return stockService.pagingStockInfo(pageNum, pageSize);
    }

    /**
     * 股票涨幅榜
     *
     * @return R
     */
    @ApiOperation(value = "股票涨幅榜", notes = "股票涨幅榜", httpMethod = "GET")
    @GetMapping("/stock/increase")
    public R<List<StockUpdownDomain>> topStocks() {
        return stockService.topStocks(TOP_K);
    }

    /**
     * 统计股票(最新交易日内)涨跌停的股票数量
     *
     * @return R
     */
    @ApiOperation(value = "统计股票(最新交易日内)涨跌停的股票数量", notes = "统计股票(最新交易日内)涨跌停的股票数量", httpMethod = "GET")
    @GetMapping("/stock/updown/count")
    public R<Map<String, List<Map<String, String>>>> getStockUpDownCount() {
        return stockService.getStockUpDownCount();
    }

    /**
     * 将指定页的股票信息导出为Excel表
     *
     * @param response HttpResponse
     * @param page     Page no.
     * @param pageSize page size
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "Page no."),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "page size")
    })
    @ApiOperation(value = "将指定页的股票信息导出为Excel表", notes = "将指定页的股票信息导出为Excel表", httpMethod = "GET")
    @GetMapping("/stock/export")
    public void stockExport(HttpServletResponse response,
                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        stockService.stockExport(response, page, pageSize);
    }

    /**
     * 统计A股大盘T日和T-1日成交量对比功能（成交量为沪深两市成交量之和）
     *
     * @return R
     */
    @ApiOperation(value = "统计A股大盘T日和T-1日成交量对比功能（成交量为沪深两市成交量之和）", notes = "统计A股大盘T日和T-1日成交量对比功能（成交量为沪深两市成交量之和）", httpMethod = "GET")
    @GetMapping("/stock/tradeAmt")
    public R<Map<String, List<Map<String, String>>>> stockTradeAmt4InnerMarketCompared() {
        return stockService.getStockTradeAmt4InnerMarketCompared();
    }

    /**
     * 查询当前时间下股票的涨跌幅度区间统计功能
     * 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询点
     *
     * @return R
     */
    @ApiOperation(value = "查询当前时间下股票的涨跌幅度区间统计功能 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询点", notes = "查询当前时间下股票的涨跌幅度区间统计功能 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询点", httpMethod = "GET")
    @GetMapping("/stock/updown")
    public R<Map<String, Object>> getStockRangeCount() {
        return stockService.getStockRangeCount();
    }

    /**
     * 查询单个个股的分时行情数据，也就是统计指定股票T日每分钟的交易数据；
     * 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点
     *
     * @param code 股票代码
     * @return R
     */
    @ApiOperation(value = "查询单个个股的分时行情数据，也就是统计指定股票T日每分钟的交易数据； 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点", notes = "查询单个个股的分时行情数据，也就是统计指定股票T日每分钟的交易数据； 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "code", value = "股票代码", required = true)
    })
    @GetMapping("/stock/screen/time-sharing")
    public R<List<Stock4MinuteDomain>> getStockScreenTimeSharing(String code) {
        return stockService.getStockScreenTimeSharing(code);
    }

    /**
     * 单个个股日K数据查询
     *
     * @param code 股票代码
     * @return R
     */
    @ApiOperation(value = "单个个股日K数据查询", notes = "单个个股日K数据查询", httpMethod = "GET")
    @GetMapping("/stock/screen/dkline")
    public R<List<Stock4DayDomain>> getDayKLineData(String code) {
        return stockService.getDayKLineData(code);
    }
}