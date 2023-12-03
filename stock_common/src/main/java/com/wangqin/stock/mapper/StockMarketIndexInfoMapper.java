package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.domain.InnerMarketDomain;
import com.wangqin.stock.pojo.entity.StockMarketIndexInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author qinwang
 * @description 针对表【stock_market_index_info(国内大盘数据详情表)】的数据库操作Mapper
 * @createDate 2023-11-28 01:11:28
 * @Entity com.wangqin.stock.pojo.entity.StockMarketIndexInfo
 */
public interface StockMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockMarketIndexInfo record);

    int insertSelective(StockMarketIndexInfo record);

    StockMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketIndexInfo record);

    int updateByPrimaryKey(StockMarketIndexInfo record);

    List<InnerMarketDomain> getMarketInfo(@Param("marketIds") List<String> marketIds, @Param("timePoint") Date timePoint);

    @MapKey("time")
    List<Map<String, String>> getStockTradeAmt4InnerMarket(@Param("startDate") Date start4T,
                                                           @Param("endDate") Date end4T,
                                                           @Param("marketCodes") List<String> inner);
}
