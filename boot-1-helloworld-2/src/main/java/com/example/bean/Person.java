package com.example.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ToString
@ConfigurationProperties(prefix = "person")
@Component
public class Person {
    String name;
    Integer age;
    Date birthDay;
    List<String> sports;
    Map<String,Integer> scores;
    Pet pet;
    Map<String,List<Pet>> pets;

}
