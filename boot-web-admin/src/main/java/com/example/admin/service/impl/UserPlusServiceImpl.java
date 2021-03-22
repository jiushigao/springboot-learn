package com.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.bean.UserPlus;
import com.example.admin.mapper.UserPlusMapper;
import com.example.admin.service.UserPlusService;
import org.springframework.stereotype.Service;

@Service
public class UserPlusServiceImpl extends ServiceImpl<UserPlusMapper, UserPlus> implements UserPlusService {
}
