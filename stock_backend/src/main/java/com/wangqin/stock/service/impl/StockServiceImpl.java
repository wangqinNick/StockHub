package com.wangqin.stock.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangqin.stock.mapper.StockBlockRtInfoMapper;
import com.wangqin.stock.mapper.StockMarketIndexInfoMapper;
import com.wangqin.stock.mapper.StockRtInfoMapper;
import com.wangqin.stock.pojo.domain.InnerMarketDomain;
import com.wangqin.stock.pojo.domain.StockBlockDomain;
import com.wangqin.stock.pojo.domain.StockUpdownDomain;
import com.wangqin.stock.pojo.vo.StockInfoConfig;
import com.wangqin.stock.service.StockService;
import com.wangqin.stock.utils.DateTimeUtil;
import com.wangqin.stock.vo.response.PageResult;
import com.wangqin.stock.vo.response.R;
import com.wangqin.stock.vo.response.ResponseCode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;

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

    /**
     * 分页查询沪深两市股票最新数据, 并按照涨幅排序
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示数量
     * @return R
     */
    @Override
    public R<PageResult<StockUpdownDomain>> pagingStockInfo(Integer pageNum, Integer pageSize) {
        //1.设置PageHelper分页参数
        PageHelper.startPage(pageNum, pageSize);
        //2.获取当前最新的股票交易时间点
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //todo
        curDate = DateTime.parse("2021-12-30 09:42:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //3.调用mapper接口查询
        List<StockUpdownDomain> infos = stockRtInfoMapper.getNewestStockInfo(curDate);
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA);
        }
        // 4.组装PageInfo对象，获取分页的具体信息,因为PageInfo包含了丰富的分页信息，而部分分页信息是前端不需要的
        PageResult<StockUpdownDomain> pageResult = new PageResult<>(new PageInfo<>(infos));
        //5.封装响应数据
        return R.ok(pageResult);
    }

    /**
     * 查询并返回涨幅榜排名前K个股票信息
     *
     * @param topK 前K个
     * @return R
     */
    @Override
    public R<List<StockUpdownDomain>> topStocks(int topK) {
        PageHelper.startPage(1, 4);
        // 1. 获取股票最新交易时间点
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //todo
        curDate = DateTime.parse("2021-12-30 09:42:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //2.调用mapper接口查询
        List<StockUpdownDomain> infos = stockRtInfoMapper.getNewestStockInfo(curDate);
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA);
        }
        return R.ok(infos);
    }

    /**
     * 统计股票(最新交易日内)涨跌停的股票数量
     *
     * @return R
     */
    @Override
    public R<Map<String, List<Map<String, String>>>>  getStockUpDownCount() {
        // 1. 获取股票最新交易时间范围
        // 1.1 获取股票最新交易时间点
        DateTime curDateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date curDate = curDateTime.toDate();
        //todo
        curDate = DateTime.parse("2022-01-06 14:25:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();

        // 1.2 获取“股票最新交易时间点”对应的开盘时间
        DateTime openDateTime = DateTimeUtil.getOpenDate(curDateTime);
        Date openDate = openDateTime.toDate();
        //todo
        openDate = DateTime.parse("2022-01-06 09:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();

        // 统计涨停
        List<Map<String, String>> upList = stockRtInfoMapper.getStockUpdownCount(openDate, curDate, 1);
        // 统计跌停
        List<Map<String, String>> downList = stockRtInfoMapper.getStockUpdownCount(openDate, curDate, 0);

        Map<String, List<Map<String, String>>> upDownList = new HashMap<>();
        upDownList.put("upList", upList);
        upDownList.put("downList", downList);

        return R.ok(upDownList);
    }
}