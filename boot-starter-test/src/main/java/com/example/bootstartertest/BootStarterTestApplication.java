package com.example.bootstartertest;

import com.example.hellospringbootstarterautoconfigure.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootStarterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootStarterTestApplication.class, args);

    }

}
