package com.example.admin.mapper;

import com.example.admin.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 导入mybatis官方starter
 * 编写mapper接口。标注@Mapper注解
 * 编写sql映射文件并绑定mapper接口
 * 在application.yaml中指定Mapper配置文件的位置，以及指定全局配置文件的信息 （建议；配置在mybatis.configuration）
 */
//@Mapper
public interface UserMapper {

    public User getUser(Long id);
}
