package com.example.cache.service;

import com.example.cache.bean.Department;
import com.example.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "department",cacheManager = "deptCacheManager")
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 缓存的数据能存入redis
     * 第二次从缓存中查询就不能反序列化回来
     * 存的是department的json数据，CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     * @param id
     * @return
     */
    @Cacheable
    public Department getDepartment(Integer id){
        Department department = departmentMapper.getDepartment(id);
        return department;
    }
}
