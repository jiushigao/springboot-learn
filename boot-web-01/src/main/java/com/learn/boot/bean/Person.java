package com.learn.boot.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ToString
public class Person {

    private String username;
    private Integer age;
    private Date birthday;
    private Pet pet;

}
