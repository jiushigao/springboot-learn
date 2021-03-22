package com.example.springboot1amqp;


import com.example.springboot1amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot1AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));

//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",
                Binding.DestinationType.QUEUE,
                "amqpadmin.exchange",
                "amqpadmin.haha",
                null));
        System.out.println("创建完成");

        amqpAdmin.deleteExchange("");//删除交换器
        amqpAdmin.deleteQueue("");//删除队列
    }

    @Test
    public void contextLoads() {
        //Message需要自己构造一个；定制消息体内容和消息头
        //rabbitTemplate.send(exchange,routeKey,message);

        //object默认当成消息体，传入的对象会自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(exchange,routeKey,object);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("msg","这是一个消息");
        map.put("data", Arrays.asList("helloworld",123,true));
//        rabbitTemplate.convertAndSend("exchange.direct","jiege.news",map);
        rabbitTemplate.convertAndSend("amqpadmin.exchange","amqpadmin.haha",map);
    }

    //接收数据
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("jiege.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    //广播
    @Test
    public void senMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("三国","罗贯中"));
    }

}
