package com.wangqin.stock.service;

import com.wangqin.stock.pojo.domain.UserQueryDomain;
import com.wangqin.stock.vo.request.*;
import com.wangqin.stock.vo.response.*;

import java.util.List;

public interface UserServiceExt {
    /**
     * 用户登录功能
     *
     * @param vo
     * @return
     */
    R<LoginRespVoExt> login(LoginReqVo vo);

    /**
     * 多条件综合查询用户分页信息，条件包含：分页信息 用户创建日期范围
     *
     * @param userPageReqVo
     * @return
     */
    R<PageResult<UserQueryDomain>> pageUsers(UserPageReqVo userPageReqVo);

    /**
     * 添加用户信息
     *
     * @param vo
     * @return
     */
    R<String> addUser(UserAddReqVo vo);

    /**
     * 更新用户信息
     *
     * @param vo
     * @return
     */
    R<String> updateUser(UserEditReqVO vo);

    /**
     * 获取用户具有的角色信息，以及所有角色信息
     *
     * @param userId
     * @return
     */
    R<UserOwnRoleRespVo> getUserOwnRole(Long userId);

    /**
     * 更新用户角色信息
     *
     * @param vo
     * @return
     */
    R<String> updateUserOwnRoles(UserOwnRoleReqVo vo);

    /**
     * 批量删除用户信息
     *
     * @param userIds
     * @return
     */
    R<String> deleteUsers(List<Long> userIds);

    /**
     * 根据用户id查询用户详情信息
     *
     * @param id 用户id
     * @return
     */
    R<UserInfoRespVo> getUserInfo(Long id);

}
