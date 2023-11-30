package com.wangqin.stock.vo.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("用户登录响应实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespVo {
    /**
     * 用户ID
     * 将Long类型数字进行json格式转化时，转成String格式类型
     */
    @ApiModelProperty(value = "用户ID 将Long类型数字进行json格式转化时，转成String格式类型", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", position = 2)
    private String phone;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", position = 3)
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", position = 4)
    private String nickName;

}