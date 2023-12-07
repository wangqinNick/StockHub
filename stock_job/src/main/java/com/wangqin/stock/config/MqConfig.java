package com.wangqin.stock.config;


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

    /**
     * 国内大盘信息队列
     *
     * @return Queue
     */
    @Bean
    public Queue innerMarketQueue() {
        return new Queue("innerMarketQueue", true);
    }

    /**
     * 国内个股信息队列
     *
     * @return Queue
     */
    @Bean
    public Queue stockQueue() {
        return new Queue("stockQueue", true);
    }


    /**
     * 定义路由股票信息的交换机
     *
     * @return TopicExchange
     */
    @Bean
    public TopicExchange stockTopicExchange() {
        return new TopicExchange("stockTopicExchange", true, false);
    }

    /**
     * 绑定国内大盘信息队列到指定交换机
     *
     * @return Binding
     */
    @Bean
    public Binding bindingInnerMarketExchange() {
        return BindingBuilder.bind(innerMarketQueue()).to(stockTopicExchange()).with("inner.market");
    }

    /**
     * 绑定国内个股信息队列到指定交换机
     *
     * @return Binding
     */
    @Bean
    public Binding bindingStockExchange() {
        return BindingBuilder.bind(stockQueue()).to(stockTopicExchange()).with("inner.stock");
    }
}