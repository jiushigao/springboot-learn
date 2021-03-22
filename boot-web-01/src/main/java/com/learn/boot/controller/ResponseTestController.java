package com.learn.boot.controller;

import com.learn.boot.bean.Person;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ResponseTestController {

    /**
     * RequestResponseBodyMethodProcessor返回值处理器会处理带有@ResponseBody注解的方法的返回值
     * • 1、内容协商（浏览器默认会以请求头的方式告诉服务器他能接受什么样的内容类型）
     * • 2、服务器最终根据自己自身的能力，决定服务器能生产出什么样内容类型的数据，
     * • 3、SpringMVC会挨个遍历所有容器底层的 HttpMessageConverter （消息转换器），看谁能处理？
     *      • 1、得到MappingJackson2HttpMessageConverter可以将对象写为json
     *      • 2、利用MappingJackson2HttpMessageConverter将对象转为json再写出去
     * @return
     *
     * 内容协商原理：
     * 1、判断当前响应头中是否已经有确定的媒体类型。MediaType
     * 2、获取客户端（PostMan、浏览器）支持接收的内容类型。（获取客户端Accept请求头字段）
     * 3、遍历循环所有当前系统的 MessageConverter，看谁支持操作这个对象（Person）
     * 4、找到支持操作Person的converter，把converter支持的媒体类型统计出来。
     * 5、客户端需要【application/xml】。服务端能力【10种、json、xml】
     * 6、进行内容协商的最佳匹配媒体类型
     * 7、用 支持 将对象转为 最佳匹配媒体类型 的converter。调用它进行转化 。
     */
    @ResponseBody
    @GetMapping("/test/person")
    public Person person(){
        Person p = new Person();
        p.setUsername("zhangsan");
        p.setAge(22);
        p.setBirthday(new Date());
        return p;
    }

    @ResponseBody
    @GetMapping("/fileResource")
    public FileSystemResource resourceConverter(){
        FileSystemResource fsr = new FileSystemResource("target/classes/static/3.jpg");
        return fsr;
    }
}
