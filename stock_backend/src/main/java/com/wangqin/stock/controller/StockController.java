package com.wangqin.stock.controller;

import com.wangqin.stock.pojo.domain.InnerMarketDomain;
import com.wangqin.stock.pojo.domain.StockBlockDomain;
import com.wangqin.stock.service.StockService;
import com.wangqin.stock.vo.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wangqin.stock.constant.StockConstant.LIMIT;

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
}