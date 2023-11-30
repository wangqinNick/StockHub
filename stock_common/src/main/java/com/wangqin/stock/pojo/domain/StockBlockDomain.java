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
 * 国内板块指数分析
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "国内板块指数分析")
public class StockBlockDomain {
    @ApiModelProperty(value = "公司数量", position = 1)
    private Integer companyNum; //公司数量
    @ApiModelProperty(value = "交易量", position = 2)
    private Long tradeAmt; //交易量
    @ApiModelProperty(value = "板块编码", position = 3)
    private String code; //板块编码
    @ApiModelProperty(value = "平均价格", position = 4)
    private BigDecimal avgPrice; //平均价格
    @ApiModelProperty(value = "板块名称", position = 5)
    private String name; //板块名称
    @ApiModelProperty(value = "当前日期", position = 6)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate; //当前日期

    @ApiModelProperty(value = "交易总金额", position = 7)
    private BigDecimal tradeVol; //交易总金额
    @ApiModelProperty(value = "涨幅", position = 8)
    private BigDecimal updownRate; //涨幅
}
