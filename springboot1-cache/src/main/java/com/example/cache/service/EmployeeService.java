package com.example.cache.service;

import com.example.cache.bean.Employee;
import com.example.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp",cacheManager = "empCacheManager")//抽取缓存公共配置,若不配置cacheManager则使用默认的缓存管理器
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同数据，直接从缓存获取，不再调用方法
     * cacheManager管理多个cache组件的，对缓存真正的CRUD操作在cache组件中，每一个缓存组件都有自己唯一一个名字
     * cacheable注解属性：
     *      cacheNames/value: 指定缓存组件的名字
     *      key: 缓存数据使用的key，可以用它来指定；默认是使用方法参数的值  1-方法的返回值
     *          编写SPEL表达式  #id 参数id的值  等同于  #a0  #p0  #root.args[0]
     *      keyGenerator: key的生成器，可以自己指定key的生成器的组件id
     *          key/keyGenerator二选一
     *      cacheManager: 指定缓存管理器，活着cacheResolver指定缓存解析器
     *      condition: 指定符合条件的情况下才进行缓存
     *              condition="#id>0"
     *      unless: 否定缓存，当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
     *              unless="#result==null"
     *      sync: 缓存是否使用异步模式
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp"/*,keyGenerator = "myKeyGenerator",condition = "#id>1",unless = "#id==2"*/)
    public Employee getEmployee(Integer id){
        System.out.println("查询"+id+"号员工");
        return employeeMapper.getEmployee(id);
    }

    /**
     * 调用方法并更新缓存
     * 1.调用目标方法
     * 2.将方法返回参数放到缓存中
     * @param employee
     */
    @CachePut(/*cacheNames = "emp",*/key = "#result.id")
    public Employee updateEmployee(Employee employee){
        System.out.println("更新"+employee.getId()+"号员工,员工信息："+employee);
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    /**
     * 删除缓存
     * @param id
     * key: 指定要删除的缓存
     * allEntries: 删除指定缓存组件中的所有缓存
     * beforeInvocation: 是否在方法执行前清除缓存，默认在方法执行后清除缓存
     */
    @CacheEvict(cacheNames = "emp"/*,key = "#id"*/,allEntries = true,beforeInvocation = true)
    public void deleteEmployee(Integer id){
        System.out.println("删除"+id+"号员工");
//        employeeMapper.deleteEmployee(id);
        int i = 10/0;//若在方法执行后清除缓存，此处报错则不会清除缓存；若在之前清除缓存则此处即便报错，缓存仍会被清除
    }

    /**
     * @Caching 定义复杂缓存规则
     * 该方法会存入三个缓存值，由于有@CachePut下次查询仍会执行方法
     * @param lastName
     * @return
     */
    @Caching(
                cacheable = {
                      @Cacheable(cacheNames = "emp",key = "#lastName")
                },
                put = {
                        @CachePut(cacheNames = "emp",key = "#result.id"),
                        @CachePut(cacheNames = "emp",key = "#result.email")
                }
            )
    public Employee getEmpByLastName(String lastName){
        System.out.println("=====lastName:"+lastName);
        return employeeMapper.getEmpByLastName(lastName);
    }
}
