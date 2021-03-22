package com.example.cache.controller;

import com.example.cache.bean.Department;
import com.example.cache.bean.Employee;
import com.example.cache.mapper.DepartmentMapper;
import com.example.cache.service.DepartmentService;
import com.example.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CacheController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("/updateEmp")
    public void updateEmployee(Employee employee){
        employeeService.updateEmployee(employee);
    }

    @GetMapping("/delEmp/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/getEmpByLastName")
    public Employee getEmpByLastName(@RequestParam("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }

    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentService.getDepartment(id);
    }
}
