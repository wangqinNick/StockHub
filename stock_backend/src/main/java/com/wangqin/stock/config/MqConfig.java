package com.wangqin.stock.config;


import io.swagger.annotations.ApiModel;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义RabbitMQ相关配置
 */
@ApiModel(description = "定义RabbitMQ相关配置")
@Configuration
public class MqConfig {

    /**
     * 重新定义消息序列化的方式，改为基于json格式序列化和反序列化
     *
     * @return MessageConverter
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
