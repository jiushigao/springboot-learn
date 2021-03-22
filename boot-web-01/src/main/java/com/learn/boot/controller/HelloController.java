package com.learn.boot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    //静态资源路径  /static (or /public or /resources or /META-INF/resources)
    //请求进来先进controller看能不能处理，若不能处理再去静态资源里面去找
    @RequestMapping("/1.jpg")
    public String hi(){
        return "hi~";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){
        return "GET USER";
    }
//    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping("/user")
    public String postUser(){
        return "POST USER";
    }
//    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE USER";
    }
//    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser(){
        return "PUT USER";
    }



}
