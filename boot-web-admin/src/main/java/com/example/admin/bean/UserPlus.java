package com.example.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class UserPlus {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //默认要求实体类中字段和数据库表中字段一一对应，额外的字段需要添加此注解
    @TableField(exist = false)
    private String username;
}
