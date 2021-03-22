package com.example.cache;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.cache.bean.Employee;
import com.example.cache.mapper.EmployeeMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot1CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisTemplate empRedisTemplate;

    /**
     * Redis 常见五大数据类型：
     * String(字符串)  List（列表）  Hash（散列）  Set（集合）  ZSet（有序集合）
     */
    @Test
    public void contextLoads() {
        Employee e = employeeMapper.getEmployee(1);
        System.out.println(e);
    }

    @Test
    public void TestRedis(){
        ValueOperations<String, String> sso = stringRedisTemplate.opsForValue();
        System.out.println(sso.get("msg"));

    }

    @Test
    public void testResdisObject(){
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        Employee e = employeeMapper.getEmployee(1);
//        valueOperations.set("emp-01",e);
        //将对象转为json存储
        //(1).自己将对象转为json
        //(2).redisTemplate默认的序列化机制
//        Employee e = employeeMapper.getEmployee(1);
//        String s = JSONObject.toJSONString(e);
//        System.out.println(s);

        ValueOperations valueOperations1 = empRedisTemplate.opsForValue();
        Employee e = employeeMapper.getEmployee(1);
        valueOperations1.set("emp-02",e);


    }

}
