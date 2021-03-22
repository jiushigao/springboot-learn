package com.example.ticket.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Reference//远程调用，根据全类名去注册中心获取
    TicketService ticketService;

    public void getTicket(){
        System.out.println("买到票啦"+ticketService.getTicket());

    }
}
