package com.wangqin.stock;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangqin.stock.mapper.SysUserMapper;
import com.wangqin.stock.pojo.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PageHelperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 测试分页查询PageHelper
     */
    @Test
    public void pagehelperTest() {
        int pageNum = 2;
        int pageSize = 5;

        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        // 查询
        List<SysUser> all = sysUserMapper.selectAll();

        // 将查询得到的对象封装到PageInfo下
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(all);

        // 获取想得到的数据
        int pages = sysUserPageInfo.getPages();
        int pageSize1 = sysUserPageInfo.getPageSize();
        int pageNum1 = sysUserPageInfo.getPageNum();
        List<SysUser> list = sysUserPageInfo.getList();

        System.out.println(list);
    }
}
