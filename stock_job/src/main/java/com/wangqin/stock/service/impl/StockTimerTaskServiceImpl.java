package com.wangqin.stock.service.impl;

import com.google.common.collect.Lists;
import com.wangqin.stock.constant.ParseType;
import com.wangqin.stock.constant.StockConstant;
import com.wangqin.stock.mapper.StockBusinessMapper;
import com.wangqin.stock.mapper.StockMarketIndexInfoMapper;
import com.wangqin.stock.mapper.StockRtInfoMapper;
import com.wangqin.stock.pojo.entity.StockMarketIndexInfo;
import com.wangqin.stock.pojo.entity.StockRtInfo;
import com.wangqin.stock.pojo.vo.StockInfoConfig;
import com.wangqin.stock.service.StockTimerTaskService;
import com.wangqin.stock.utils.IdWorker;
import com.wangqin.stock.utils.ParserStockInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service("stockTimerTaskService")
@Slf4j
public class StockTimerTaskServiceImpl implements StockTimerTaskService {

    @Autowired
    private StockInfoConfig stockInfoConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    @Autowired
    private StockBusinessMapper stockBusinessMapper;

    @Autowired
    private ParserStockInfoUtil parserStockInfoUtil;

    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;

    // StockTimerTaskServiceImpl 这个Bean创建的时候创建httpEntity
    private HttpEntity<Object> httpEntity;

    @PostConstruct
    public void init() {
        HttpHeaders headers = new HttpHeaders();
        //必须填写，否则数据采集不到
        headers.add("Referer", StockConstant.REFERER_URL);
        headers.add("User-Agent", StockConstant.USER_AGENT);
        this.httpEntity = new HttpEntity<>(headers);
    }

    /**
     * 获取国内大盘的实时数据信息
     */
    @Override
    public void getInnerMarketInfo() {
        //1.定义采集的url接口
        String url = stockInfoConfig.getMarketUrl() + String.join(",", stockInfoConfig.getInner());
        //2.调用restTemplate采集数据
        //2.1 组装请求头
        //2.2 组装请求对象
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, this.httpEntity, String.class);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        if (statusCodeValue != 200) {
            log.error("当前时间: {}, 采集大盘数据出现问题, HTTP状态码: {}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"), statusCodeValue);
            return;
        }

        String jsData = responseEntity.getBody();
        log.info("当前时间: {}, 采集大盘数据: {}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"), jsData);

        // 3 解析数据
        List<StockMarketIndexInfo> list = parserStockInfoUtil.parser4StockOrMarketInfo(jsData, ParseType.INNER);

        // 收集大盘数据入集合
        log.info("采集的当前大盘数据：{}", list);
        //批量插入
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        // 4 使用 myBatis Mapper 批量入库
        int count = stockMarketIndexInfoMapper.insertBatch(list);
        if (count > 0)
            log.info("当前时间: {}, 成功插入{}条大盘数据.", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"), count);
        else log.error("当前时间: {}, 插入大盘数据失败", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 定义获取分钟级(个)股票数据
     */
    @Override
    public void getStockRtIndex() {
        // 1. 组装采集的URL
        // 1.1 获取所有股票的代码Code集合
        List<String> allStockCodes = stockBusinessMapper.getAllStockCodes();
        List<String> allStockCodesWithSuffix = allStockCodes.stream().map(code -> code.startsWith("6") ? "sh" + code : "sz" + code).collect(Collectors.toList());
        // 1.2 每15只股票拆分为一组, 分批次拉取数据
        List<List<String>> partitions = Lists.partition(allStockCodesWithSuffix, StockConstant.PARTITION_SIZE);
        partitions.forEach(codes -> {
            String url = stockInfoConfig.getMarketUrl() + String.join(",", codes);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, this.httpEntity, String.class);
            int statusCodeValue = responseEntity.getStatusCodeValue();

            if (statusCodeValue != 200) {
                log.error("当前时间: {}, 采集个股数据出现问题, HTTP状态码: {}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"), statusCodeValue);
                return;
            }

            String jsData = responseEntity.getBody();

            // 2.3 调用工具类解析响应数据 (个股)
            List<StockRtInfo> list = parserStockInfoUtil.parser4StockOrMarketInfo(jsData, ParseType.ASHARE);
            log.info("当前时间: {}, 采集个股数据: {}", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"), list);

            // 3 批量插入
            int count = stockRtInfoMapper.insertBatch(list);
            if (count > 0)
                log.info("当前时间: {}, 成功插入{}条个股数据.", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"), count);
            else log.error("当前时间: {}, 插入个股数据失败", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

        });
    }
}
