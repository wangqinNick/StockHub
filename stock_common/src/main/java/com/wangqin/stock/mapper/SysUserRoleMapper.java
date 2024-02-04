package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据用户id查询角色集合
     *
     * @param userId
     * @return
     */
    List<Long> findRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id删除关联的角色
     *
     * @param userId
     * @return
     */
    int deleteByUserId(@Param("userId") Long userId);

    /**
     * 批量插入信息
     *
     * @param list
     * @return
     */
    int insertBatch(@Param("urs") List<SysUserRole> list);


}
