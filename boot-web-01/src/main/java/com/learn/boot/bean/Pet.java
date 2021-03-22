package com.learn.boot.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
public class Pet {
    private String name;
    private Integer age;
}
