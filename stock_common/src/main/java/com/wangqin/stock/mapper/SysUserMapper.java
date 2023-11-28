package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author qinwang
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @createDate 2023-11-28 01:11:28
 * @Entity com.wangqin.stock.pojo.entity.SysUser
 */
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getByUsername(@Param("username") String name);
}
