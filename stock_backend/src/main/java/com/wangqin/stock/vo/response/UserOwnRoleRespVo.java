package com.wangqin.stock.vo.response;

import com.wangqin.stock.pojo.entity.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class UserOwnRoleRespVo {
    /**
     * 用户用户的角色id集合
     */
    private List<Long> ownRoleIds;
    /**
     * 所有角色集合
     */
    private List<SysRole> allRole;
}
