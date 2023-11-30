package com.wangqin.stock.service.impl;

import com.wangqin.stock.mapper.StockBlockRtInfoMapper;
import com.wangqin.stock.mapper.StockBusinessMapper;
import com.wangqin.stock.mapper.StockMarketIndexInfoMapper;
import com.wangqin.stock.pojo.domain.InnerMarketDomain;
import com.wangqin.stock.pojo.domain.StockBlockDomain;
import com.wangqin.stock.pojo.entity.StockBlockRtInfo;
import com.wangqin.stock.pojo.vo.StockInfoConfig;
import com.wangqin.stock.service.StockService;
import com.wangqin.stock.utils.DateTimeUtil;
import com.wangqin.stock.vo.response.R;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.wangqin.stock.constant.StockConstant.MOCK_DATE;

/**
 * @author by itheima
 * @Date 2021/12/19
 * @Description
 */
@Service("stockService")
public class StockServiceImpl implements StockService {


    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    @Autowired
    private StockInfoConfig stockInfoConfig;

    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    /**
     * 获取国内大盘的实时数据
     *
     * @return R
     */
    @Override
    public R<List<InnerMarketDomain>> innerIndexAll() {
        //1.获取国内A股大盘的id集合
        List<String> inners = stockInfoConfig.getInner();
        //2.获取最近股票交易日期
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock测试数据，后期数据通过第三方接口动态获取实时数据 可删除
        lastDate = DateTime.parse("2021-12-28 09:31:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //3.将获取的java Date传入接口
        List<InnerMarketDomain> list = stockMarketIndexInfoMapper.getMarketInfo(inners, lastDate);
        //4.返回查询结果
        return R.ok(list);
    }

    /**
     * 查询沪深两市最新的板块行情数据，并按照交易金额降序排序展示前10条记录
     *
     * @return R 10条板块行情数据
     */
    @Override
    public R<List<StockBlockDomain>> sectorAllLimit(int length) {
        //2.获取最近股票交易日期
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock测试数据，后期数据通过第三方接口动态获取实时数据 可删除
        lastDate = DateTime.parse(MOCK_DATE, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //3.将获取的java Date传入接口
        List<StockBlockDomain> list = stockBlockRtInfoMapper.selectSectorAllLimit(lastDate, length);
        //4.返回查询结果
        return R.ok(list);
    }
}