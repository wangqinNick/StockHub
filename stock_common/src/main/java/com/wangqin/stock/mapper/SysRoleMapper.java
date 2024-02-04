package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qinwang
 * @description 针对表【sys_role(角色表)】的数据库操作Mapper
 * @createDate 2023-11-28 01:11:28
 * @Entity com.wangqin.stock.pojo.entity.SysRole
 */
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 查询所有角色信息
     *
     * @return
     */
    List<SysRole> findAll();

    /**
     * 根据用户id查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUserId(@Param("userId") Long userId);
}
