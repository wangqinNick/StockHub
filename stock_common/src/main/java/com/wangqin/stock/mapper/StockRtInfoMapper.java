package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.domain.StockUpdownDomain;
import com.wangqin.stock.pojo.entity.StockRtInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author qinwang
 * @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
 * @createDate 2023-11-28 01:11:28
 * @Entity com.wangqin.stock.pojo.entity.StockRtInfo
 */
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

    List<StockUpdownDomain> getNewestStockInfo(@Param("timePoint") Date curDate);

    /**
     * 统计股票(最新交易日内)涨跌停的股票数量
     *
     * @param openDate 开盘时间
     * @param curDate  当前时间
     * @param i        i=1 涨停, i=0 跌停
     * @return List
     */
    @MapKey("time")
    List<Map<String, String>> getStockUpdownCount(@Param("openDate") Date openDate,
                                                  @Param("curDate") Date curDate,
                                                  @Param("flag") int i);

    /**
     * 查询当前时间下股票的涨跌幅度区间统计功能
     *
     * @return List
     */
    @MapKey("title")
    List<Map<String, String>> getStockRangeCount(@Param("curDate") Date curDate);
}
