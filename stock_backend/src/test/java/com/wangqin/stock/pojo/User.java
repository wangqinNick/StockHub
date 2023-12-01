package com.wangqin.stock.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于测试的虚拟实体类
 */
@ApiModel(description = "用于测试的虚拟实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    @ApiModelProperty(hidden = true)
    private String userName;
    @ApiModelProperty(hidden = true)
    private Integer age;
    @ApiModelProperty(hidden = true)
    private String address;
    @ApiModelProperty(hidden = true)
    private Date birthday;
}
