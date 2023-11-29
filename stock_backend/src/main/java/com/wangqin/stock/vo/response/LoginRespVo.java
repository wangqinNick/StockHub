package com.wangqin.stock.vo.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Wrapper entity class for user login response information")
public class LoginRespVo {
    /**
     * 用户ID
     * 将Long类型数字进行json格式转化时，转成String格式类型
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "User's id, primary key in the database")
    private Long id;
    /**
     * 电话
     */
    @ApiModelProperty(value = "User's phone number")
    private String phone;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "User's username")
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "User's nickname")
    private String nickName;

}