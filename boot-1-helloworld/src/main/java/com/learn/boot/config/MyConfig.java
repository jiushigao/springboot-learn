package com.learn.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.learn.boot.bean.Car;
import com.learn.boot.bean.Pet;
import com.learn.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 1.配置类里面使用@Bean注解标记在方法上给容器添加组件，默认是单例
 * 2.配置类本身也是组件
 * 3.proxyBeanMethods:代理bean的方法
 *   Full(proxyBeanMethods=true)全量配置，若组件之间存在依赖使用该配置
 *   Lite(proxyBeanMethods=false)轻量配置，若组件之间没有依赖使用该配置，避免从容器中查找实例可以提高性能
 * 4.@Import({User.class, DBHelper.class})
 *   给容器中自动创建组件，默认组件的名字就是全类名
 * 5.@ImportResource("classpath:beans.xml")导入spring配置文件
 */

@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods=true)//告诉springboot这是一个配置类 == 配置文件
//@ConditionalOnBean(name={"tom"})//条件装配，若容器中有tom组件则注册以下组件
@ConditionalOnMissingBean(name={"tom22"})
@ImportResource("classpath:beans.xml")
@EnableConfigurationProperties(Car.class)
/**
 * 1.开启配置绑定功能
 * 2.将组件注册到容器中
 * 使用场景：若要在第三方包中的类中使用配置绑定功能而那个类没有@Component注解，则可使用该注解EnableConfigurationProperties
 */

public class MyConfig {

    //若proxyBeanMethods=true，则外部调用配置类中注册组件的方法，无论调用多少次获取到的都是已注册到容器中的组件
    @Bean //给容器中添加组件，默认以方法名作为组件id，返回类型就是组件类型，返回的值就是容器中的实例
    public User user01(){
        User user = new User("jiege", 26);
        user.setPet(tomcatPet());
        return user;
    }

    @Bean("tom")
    public Pet tomcatPet(){
        return new Pet("tomcat");
    }
}
