package com.example.springboot1amqp.service;

import com.example.springboot1amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "jiege.news")//监听消息队列内容,一旦产生消息触发方法调用
    public void receive(Book book){
        System.out.println("收到消息1"+book.toString());
    }

    @RabbitListener(queues = "jiege")
    public void receive1(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }

}
