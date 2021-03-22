package com.example.admin.service;

import com.example.admin.bean.User;
import com.example.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserMapper userMapper;

    public User getUser(Long id){
        return userMapper.getUser(id);
    }
}
