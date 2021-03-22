package com.learn.boot.controller;


import com.learn.boot.bean.Person;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String,String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String,String> headers,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("interests") List<String> interests,
                                      @RequestParam Map<String,String> rp,
                                      @CookieValue("_ga") String _ga,
                                      @CookieValue("_ga") Cookie cookie){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("id",id);
        result.put("name",name);
        result.put("pv",pv);
        result.put("userAgent",userAgent);
        result.put("headers",headers);
        result.put("age",age);
        result.put("interests",interests);
        result.put("rp",rp);
        result.put("_ga",_ga);
        result.put("cookie",cookie);
        return result;
    }

    @PostMapping("/email")
    public Map<String,Object> postTest(@RequestBody String content) throws UnsupportedEncodingException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("content",content);

        return map;
    }

    /**
     * 数据绑定：页面提交的请求数据（GET/POST）都可以和对象属性进行绑定
     * ServletModelAttributeMethodProcessor此参数解析器用于解析自定义数据类型
     * WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
     * WebDataBinder :web数据绑定器，将请求参数的值绑定到指定的JavaBean里面
     * WebDataBinder 利用它里面的 Converters 将请求数据转成指定的数据类型。再次封装到JavaBean中
     * GenericConversionService：在设置每一个值的时候，找它里面的所有converter那个可以将这个数据类型（request带来参数的字符串）转换到指定的类型
     * @param person
     * @return
     */
    @PostMapping("/person")
    public Person getPerson(Person person){
        return person;
    }
}
