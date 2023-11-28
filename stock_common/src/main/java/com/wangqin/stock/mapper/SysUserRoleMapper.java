package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.entity.SysUserRole;

/**
* @author qinwang
* @description 针对表【sys_user_role(用户角色表)】的数据库操作Mapper
* @createDate 2023-11-28 01:11:28
* @Entity com.wangqin.stock.pojo.entity.SysUserRole
*/
public interface SysUserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

}
