package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.domain.OuterMarketDomain;
import com.wangqin.stock.pojo.entity.StockMarketIndexInfo;
import com.wangqin.stock.pojo.entity.StockOuterMarketIndexInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author qinwang
 * @description 针对表【stock_outer_market_index_info(外盘详情信息表)】的数据库操作Mapper
 * @createDate 2023-11-28 01:11:28
 * @Entity com.wangqin.stock.pojo.entity.StockOuterMarketIndexInfo
 */
public interface StockOuterMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockOuterMarketIndexInfo record);

    int insertSelective(StockOuterMarketIndexInfo record);

    StockOuterMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockOuterMarketIndexInfo record);

    int updateByPrimaryKey(StockOuterMarketIndexInfo record);

    /**
     * 获取国外大盘数据
     *
     * @param lastDate 最新日期
     * @param outers   外盘代码集合
     * @return R
     */
    List<OuterMarketDomain> getOuterIndexAll(
            @Param("marketIds") List<String> outers,
            @Param("lastDate") Date lastDate);

    int insertBatch(List<StockMarketIndexInfo> list);
}
