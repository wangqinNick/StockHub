package com.wangqin.stock.mapper;

import com.wangqin.stock.pojo.entity.SysRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
* @author qinwang
* @description 针对表【sys_role_permission(角色权限表)】的数据库操作Mapper
* @createDate 2023-11-28 01:11:28
* @Entity com.wangqin.stock.pojo.entity.SysRolePermission
*/
public interface SysRolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

    /**
     * 批量添加用户角色集合
     * @param rps
     * @return
     */
    int addRolePermissionBatch(@Param("rps") List<SysRolePermission> rps);

    /**
     * 根据角色id查询对应的权限id集合
     * @param roleId 角色id
     * @return
     */
    Set<Long> getPermissionIdsByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色id删除关联的权限信息
     * @param id 角色id
     * @return
     */
    int deleteByRoleId(@Param("roleId") Long id);

    /**
     * 根据权限id删除关联的角色信息
     * @param permissionId
     * @return
     */
    int deleteByPermissionId(@Param("permissionId") Long permissionId);

}
