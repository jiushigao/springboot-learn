package com.learn.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 只有容器中的组件才能使用springbnoot提供的功能
 */
//@Component
@ConfigurationProperties(prefix = "mycar")//绑定配置文件中的属性
@Data//lombok注解自动生成属性的get、set方法
@ToString//lombok注解生成toString方法
@AllArgsConstructor//lombok注解生成全参构造方法
@NoArgsConstructor//lombok注解生成无参构造
public class Car {
    private String brand;
    private Integer price;


}
