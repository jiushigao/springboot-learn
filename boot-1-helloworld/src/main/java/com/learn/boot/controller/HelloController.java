package com.learn.boot.controller;

import com.learn.boot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j//lombok注解，生成日志
@RestController
public class HelloController {

    @Autowired
    Car car;

    @GetMapping("/hello")
    public String hello(){
        log.info("lombok 生成日志");
        return "Hello Spring Boot 2!";
    }

    @GetMapping("/car")
    public Car car(){
        return car;
    }

}
