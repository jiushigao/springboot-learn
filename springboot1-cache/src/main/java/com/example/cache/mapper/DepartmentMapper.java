package com.example.cache.mapper;

import com.example.cache.bean.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    Department getDepartment(Integer id);

    @Update("update department set d_name=#{dName}")
    void updateDepartment(Department department);

    @Insert("insert into department (d_name) values (#{dName})")
    void createDepartment(Department department);

    @Delete("delete from department where id=#{id}")
    void deleteDepartment(Integer id);
}
