package com.wangqin.stock.pojo.domain;

import com.wangqin.stock.pojo.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDomain extends SysUser {
    private String createUserName;
    private String updateUserName;
}

