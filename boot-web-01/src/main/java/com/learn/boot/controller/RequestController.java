package com.learn.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/getAttr")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("result","请求成功！");
        return "forward:/success";
    }

    /**
     * map和model参数中存放的数据会放到request的请求域中，相当于request.setAttribute
     * 请求处理完最后会将map和model放到同一个对象modelandviewContainer中，在渲染视图的时候，会将合并后的model暴露到request的请求域中
     * @param map
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/params")
    public String testParam(Map map, Model model, HttpServletRequest request, HttpServletResponse response){
        map.put("hello","world!!!");
        model.addAttribute("modelAttr","modelValue");
        request.setAttribute("reqAttr","reqValue");
        Cookie cookie = new Cookie("cookieName","cookieValue");
        response.addCookie(cookie);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute(value = "result",required = false) String result,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("annotation",result);
        map.put("request",request.getAttribute("result"));

        Object mapValue = request.getAttribute("hello");
        Object modelValue = request.getAttribute("modelAttr");
        Object reqValue = request.getAttribute("reqAttr");

        map.put("mapValue",mapValue);
        map.put("modelValue",modelValue);
        map.put("reqValue",reqValue);
        return map;
    }

    /**
     * 1.语法： cars/sell;low=34;types=bm,byd,bc
     * 2.springboot默认是禁用矩阵变量的
     *   手动开启：原理：对于路径的处理，UrlPathHelper进行解析,
     *                  removeSemicolonContent（移除分号内容）支持矩阵变量的
     * 3.矩阵变量必须有url路径才能解析
     */
    @GetMapping("/cars/{path}")
    @ResponseBody
    public Map sellCars(@MatrixVariable("low") Integer low,@MatrixVariable("types") List<String> types,@PathVariable("path") String path){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("low",low);
        map.put("types",types);
        map.put("path",path);
        return map;
    }

    ///boss/1;age=34/2;age=45
    @GetMapping("/boss/{bossId}/{empId}")
    @ResponseBody
    public Map boss(@MatrixVariable(value="age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value="age",pathVar = "empId") Integer empAge){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }

}
