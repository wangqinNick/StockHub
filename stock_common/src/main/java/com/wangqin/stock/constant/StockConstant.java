package com.wangqin.stock.constant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 常量信息
 */
@ApiModel(description = "常量信息")
public class StockConstant {

    /**
     * 定义校验码的前缀
     */
    @ApiModelProperty(value = "定义校验码的前缀", position = 1)
    public static final String CHECK_PREFIX = "CK:";

    /**
     * 定义校验码的过期时间(单位: 分)
     */
    @ApiModelProperty(value = "定义校验码的过期时间(单位: 分)", position = 2)
    public static final int TIMEOUT = 5;

    /**
     * 股票查询时间, 用于测试数据
     */
    @ApiModelProperty(value = "股票查询时间, 用于测试数据", position = 3)
    public static final String MOCK_DATE = "2021-12-21 09:30:00";

    /**
     * 涨幅榜更多->每页查询显示数量
     */
    @ApiModelProperty(value = "涨幅榜更多->每页查询显示数量", position = 4)
    public static final int LIMIT = 10;

    /**
     * 股票涨幅榜显示数量
     */
    @ApiModelProperty(value = "股票涨幅榜显示数量", position = 5)
    public static final int TOP_K = 4;

    /**
     * 分批采集个股数据样本大小
     */
    public static final int PARTITION_SIZE = 15;

    public static final String REFERER_URL = "https://finance.sina.com.cn/stock/";

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36";
}
