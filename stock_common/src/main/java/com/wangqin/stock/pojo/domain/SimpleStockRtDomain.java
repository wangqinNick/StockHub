package com.wangqin.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个股信息的最简略封装
 */
@ApiModel(description = "个股信息的最简略封装")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleStockRtDomain {

    /**
     * 日期，eg:202201280809
     */
    @ApiModelProperty("日期，eg:202201280809")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date date;

    /**
     * 交易量
     */
    @ApiModelProperty("交易量")
    private BigDecimal tradeAmt;

    /**
     * 当前交易总金额
     */
    @ApiModelProperty("当前交易总金额")
    private BigDecimal tradeVol;

    /**
     * 当前价格
     */
    @ApiModelProperty("当前价格")
    private BigDecimal tradePrice;
}
