package com.wangqin.stock.vo.response;

import lombok.Data;

import java.util.List;

@Data
public class UpDownInfo {

    private List upList;
    private List downList;
}
