package com.wangqin.stock.controller;

import com.wangqin.stock.pojo.domain.InnerMarketDomain;
import com.wangqin.stock.pojo.domain.StockBlockDomain;
import com.wangqin.stock.pojo.domain.StockUpdownDomain;
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
    public R<PageResult<StockUpdownDomain>> pagingStockInfo(@RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNum,
                                                            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
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
}