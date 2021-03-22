package com.example.cache.mapper;

import com.example.cache.bean.Department;
import com.example.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id=#{id}")
    Employee getEmployee(Integer id);

    @Update("update employee set last_name=#{lastName},email=#{email},gender=#{gender},d_id=#{dId}")
    void updateEmployee(Employee employee);

    @Insert("insert into employee (last_name,email,gender,d_id) values (#{lastName},#{email},#{gender},#{dId})")
    void createEmployee(Employee employee);

    @Delete("delete from employee where id=#{id}")
    void deleteEmployee(Integer id);

    @Select("select * from employee where last_name=#{lastName}")
    Employee getEmpByLastName(String lastName);
}
