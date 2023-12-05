package com.wangqin.stock;

import com.google.common.collect.Lists;
import com.wangqin.stock.mapper.StockBusinessMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class MapperTest {

    @Autowired
    private StockBusinessMapper stockBusinessMapper;

    @Test
    public void stockBusinessMapperTest() {
        // 获取个股代码
        List<String> allStockCodes = stockBusinessMapper.getAllStockCodes();

        // 个股代码添加大盘的业务前缀 sh/sz
        List<String> allStockCodesWithSuffix = allStockCodes.stream().map(code -> code.startsWith("6") ? "sh" + code : "sz" + code).collect(Collectors.toList());
        System.out.println(allStockCodesWithSuffix);

        // 将所有个股代码组成的大的集合拆分成小的集合(15个一组)
        List<List<String>> partition = Lists.partition(allStockCodesWithSuffix, 15);
        partition.forEach(System.out::println);
    }
}
