package com.example.hellospringbootstarterautoconfigure.service;

import com.example.hellospringbootstarterautoconfigure.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认不放在容器中
 */
public class HelloService {

    @Autowired
    HelloProperties helloProperties;

    public String sayHello(String username){
        return helloProperties.getPerfix()+":"+username+">"+helloProperties.getSuffix();
    }
}
