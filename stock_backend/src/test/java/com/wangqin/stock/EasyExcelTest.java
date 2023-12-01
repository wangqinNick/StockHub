package com.wangqin.stock;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wangqin.stock.pojo.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyExcelTest {

    public List<User> init() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = User.builder().userName("Tom" + i).age(10 + i).address("London" + i).birthday(new Date()).build();
            users.add(user);
        }
        return users;
    }

    /**
     * 测试EasyExcel导出
     */
    @Test
    public void writeTest() {
        List<User> users = this.init();
        EasyExcel.write("/Users/qinwang/out/users.xls", User.class).sheet("用户信息").doWrite(users);
    }

    /**
     * 测试EasyExcel读取
     */
    @Test
    public void readTest() {
        List<User> users = new ArrayList<>();
        EasyExcel.read("/Users/qinwang/out/users.xls", User.class, new AnalysisEventListener<User>() {
            @Override
            public void invoke(User user, AnalysisContext analysisContext) {
                users.add(user);
                System.out.println(user);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("读取完毕");
            }
        }).sheet().doRead();

        System.out.println(users);
    }
}
