package com.wangqin.stock.vo.request;

import lombok.Data;

import java.util.List;

@Data
public class UserOwnRoleReqVo {

    private Long userId;

    private List<Long> roleIds;
}
