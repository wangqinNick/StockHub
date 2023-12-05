package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.entity.StockBusiness;

import java.util.List;

/**
 * @author qinwang
 * @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
 * @createDate 2023-11-28 01:11:28
 * @Entity com.wangqin.stock.pojo.entity.StockBusiness
 */
public interface StockBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBusiness record);

    int insertSelective(StockBusiness record);

    StockBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBusiness record);

    int updateByPrimaryKey(StockBusiness record);

    /**
     * 获取A股所有股票的代码集合
     *
     * @return A股所有股票的代码集合
     */
    List<String> getAllStockCodes();
}
