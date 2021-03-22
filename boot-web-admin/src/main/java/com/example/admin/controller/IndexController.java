package com.example.admin.controller;

import com.example.admin.bean.City;
import com.example.admin.bean.User;
import com.example.admin.service.CityService;
import com.example.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;

    @Autowired
    CityService cityService;

//    @Autowired
//    StringRedisTemplate redisTemplate;

    @GetMapping("/getUser")
    @ResponseBody
    public String getUser(@RequestParam("id") Long id){
        return userService.getUser(id).toString();
    }

    @PostMapping("/insertCity")
    @ResponseBody
    public String getCity(City city){
        cityService.insertCity(city);
        return city.toString();
    }

    @GetMapping("/getCity")
    @ResponseBody
    public String getCity(@RequestParam("id") Long id){
        return cityService.getCity(id).toString();
    }

    @ResponseBody
    @GetMapping("/query")
    public String query(){
        Long count = jdbcTemplate.queryForObject("select count(*) from t_user",Long.class);
        return String.valueOf(count);
    }

    @GetMapping(value={"/","/login"})
    public String loginPage(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    //利用重定向解决登陆后刷新页面会重复登录的问题
    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){//RedirectAttributes
       if(StringUtils.hasLength(user.getUserName()) && StringUtils.hasLength(user.getPassword())){
           session.setAttribute("user",user);
           return "redirect:main.html";
       }else{
            model.addAttribute("msg","账号或密码错误！");
            return "login";
       }

    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
//        if(session.getAttribute("user")==null){
//            model.addAttribute("msg","请先登陆！");
//            return "login";
//        }
//        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//        String mainCount = operations.get("/main.html");
//        String queryCount = operations.get("/query");
//        model.addAttribute("mainCount",mainCount);
//        model.addAttribute("queryCount",queryCount);
        log.info("main开始执行");
        return "main";
    }
}
