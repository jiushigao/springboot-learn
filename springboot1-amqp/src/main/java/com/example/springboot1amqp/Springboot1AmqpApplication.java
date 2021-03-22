package com.example.springboot1amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * rabbitmq：
 *      默认端口：5672
 *      页面端口：15672
 *      exchanges： 交换器
 *         direct： 点对点，根据绑定的路由键精确的将消息发给队列
 *         fanout： 群发，不处理路由键直接将消息发给所有绑定的队列
 *         topic : 根据正则表达式形式来匹配队列， *：1个单词  #：0个或多个单词  例：路由键 jiege.news和jiege.emp 均可通过jiege.#匹配并发给指定队列
 *
 *
 *
 * 自动配置：
 *      1.RabbitAutoConfiguration
 *      2.自动配置了链接工厂ConnectionFactory
 *      3.RabbitProperties封装了RabbitMQ配置
 *      4.RabbitTemplate给Rabbitmq发送和接收消息
 *      5.AmqpAdmin：Rabbitmq系统管理功能组件
 *        创建和删除Exchange、Binding、Queue
 *      6.@EnableRabbit+@RabbitListener 监听消息队列的内容
 */
@SpringBootApplication
@EnableRabbit//开启基于主节点rabbitmq
public class Springboot1AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot1AmqpApplication.class, args);
    }

}
