package com.wangqin.stock.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 封装线程池参数
 */
@ApiModel(description = "封装线程池参数")
@ConfigurationProperties(prefix = "task.pool")
@Data
public class TaskThreadPoolInfo {
    /**
     *  核心线程数（获取硬件）：线程池创建时候初始化的线程数
     */
    @ApiModelProperty("核心线程数（获取硬件）：线程池创建时候初始化的线程数")
    private Integer corePoolSize;
    @ApiModelProperty(hidden = true)
    private Integer maxPoolSize;
    @ApiModelProperty(hidden = true)
    private Integer keepAliveSeconds;
    @ApiModelProperty(hidden = true)
    private Integer queueCapacity;
}
