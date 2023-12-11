package com.wangqin.stock.config;

import com.wangqin.stock.pojo.vo.TaskThreadPoolInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 配置, 创建线程池Bean对象的配置类
 */
@Configuration
public class TaskExecutePool {

    @Autowired
    private TaskThreadPoolInfo threadPoolInfo;

    @Bean(name = "threadPoolTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数：核心线程数（获取硬件）：线程池创建时候初始化的线程数
        taskExecutor.setCorePoolSize(threadPoolInfo.getCorePoolSize());
        //最大线程数：只有在缓冲队列满了之后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize(threadPoolInfo.getMaxPoolSize());
        //缓冲队列：用来缓冲执行任务的队列
        taskExecutor.setQueueCapacity(threadPoolInfo.getQueueCapacity());
        //允许线程的空闲时间：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds(threadPoolInfo.getKeepAliveSeconds());
        //线程名称前缀
        taskExecutor.setThreadNamePrefix("StockThread-");
        //设置拒绝策略
        // taskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler());
        //参数初始化
        taskExecutor.initialize();
        return taskExecutor;
    }
}
