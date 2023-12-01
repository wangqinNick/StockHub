package com.wangqin.stock.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wangqin.stock.constant.StockConstant.MOCK_DATE;

@Service("stockService")
@Slf4j
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
    public R<Map<String, List<Map<String, String>>>> getStockUpDownCount() {
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

    /**
     * 将指定页的股票信息导出为Excel表
     *
     * @param response HttpResponse
     * @param page     Page no.
     * @param pageSize page size
     */
    @Override
    public void stockExport(HttpServletResponse response, Integer page, Integer pageSize) {
        try {
            //1.获取最近最新的一次股票有效交易时间点（精确分钟）
            Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
            //因为对于当前来说，我们没有实现股票信息实时采集的功能，所以最新时间点下的数据
            //在数据库中是没有的，所以，先临时指定一个假数据,后续注释掉该代码即可
            // todo
            curDate = DateTime.parse("2022-01-05 09:47:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
            //2.设置分页参数 底层会拦截mybatis发送的sql，并动态追加limit语句实现分页
            log.debug("Page value: {}", page);
            log.debug("PageSize value: {}", pageSize);
            PageHelper.startPage(page, pageSize);
            //3.查询
            List<StockUpdownDomain> infos = stockRtInfoMapper.getNewestStockInfo(curDate);
            //如果集合为空，响应错误提示信息
            if (CollectionUtils.isEmpty(infos)) {
                //响应提示信息
                response.setCharacterEncoding("utf-8");
                R<Object> r = R.error(ResponseCode.NO_RESPONSE_DATA);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(r));
                return;
            }
            //设置响应excel文件格式类型
            response.setContentType("application/vnd.ms-excel");
            //2.设置响应数据的编码格式
            response.setCharacterEncoding("utf-8");
            //3.设置默认的文件名称
            // 这里URLEncoder.encode可以防止中文乱码 当然和EasyExcel没有关系
            String fileName = URLEncoder.encode("stockRt", "UTF-8");
            //设置默认文件名称：兼容一些特殊浏览器
            response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //4.响应excel流
            EasyExcel
                    .write(response.getOutputStream(), StockUpdownDomain.class)
                    .sheet("股票信息")
                    .doWrite(infos);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("当前导出数据异常，当前页：{},每页大小：{},异常信息：{}", page, pageSize, e.getMessage());
        }
    }

    /**
     * 统计A股大盘T日和T-1日成交量对比功能（成交量为沪深两市成交量之和）
     *
     * @return R
     */
    @Override
    public R<Map<String, List<Map<String, String>>>> getStockTradeAmt4InnerMarketCompared() {

        // 1.1 获取最近最新的一次股票有效交易时间点T（开始, 结束）
        // T
        DateTime end4TDateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date end4T = end4TDateTime.toDate();
        DateTime start4DateTime = DateTimeUtil.getOpenDate(end4TDateTime);
        Date start4T = start4DateTime.toDate();
        // 1.2 获取T-1日的时间点 (开始, 结束)
        // T-1
        DateTime end4T_DateTime = DateTimeUtil.getPreviousTradingDay(end4TDateTime);
        Date end4T_ = end4T_DateTime.toDate();
        DateTime start4DateTime_ = DateTimeUtil.getOpenDate(end4T_DateTime);
        Date start4T_ = start4DateTime_.toDate();

        // 因为对于当前来说，我们没有实现股票信息实时采集的功能，所以最新时间点下的数据
        // 在数据库中是没有的，所以，先临时指定一个假数据,后续注释掉该代码即可
        // todo
        end4T = DateTime.parse("2022-01-03 14:40:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        start4T = DateTime.parse("2022-01-03 9:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        end4T_ = DateTime.parse("2022-01-02 14:40:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        start4T_ = DateTime.parse("2022-01-02 9:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();

        // 2. 获取当前日从开盘到现在时间点的每分钟股市总交易量
        List<Map<String, String>> amtInfo = stockMarketIndexInfoMapper.getStockTradeAmt4InnerMarket(start4T, end4T, stockInfoConfig.getInner());
        List<Map<String, String>> yesAmtInfo = stockMarketIndexInfoMapper.getStockTradeAmt4InnerMarket(start4T_, end4T_, stockInfoConfig.getInner());
        //
        // 3. 封装响应
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        map.put("amtList", amtInfo);
        map.put("yesAmtList", yesAmtInfo);
        return R.ok(map);
    }
}