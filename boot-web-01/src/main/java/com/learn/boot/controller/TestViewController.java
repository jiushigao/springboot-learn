package com.learn.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestViewController {

    @GetMapping("/goSuccess")
    public String goSuccess(Model model){
        //model中的属性默认会放到request请求域中
        model.addAttribute("hello","fight!!!");
        model.addAttribute("link","https://www.baidu.com/");
        return "success";
    }
}
