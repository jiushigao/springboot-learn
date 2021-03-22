package com.example.springboot1task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron = "0/10 * * * * ?")//定时任务
    public void hello(){
        System.out.println("hello");
    }
}
