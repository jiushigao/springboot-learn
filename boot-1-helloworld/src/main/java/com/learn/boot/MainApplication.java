package com.learn.boot;

import ch.qos.logback.core.db.DBHelper;
import com.learn.boot.bean.Car;
import com.learn.boot.bean.Pet;
import com.learn.boot.bean.User;
import com.learn.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类
 * @SpringBootApplication这是一个springboot应用
 * 默认扫描主程序所在包及子包的组件，若要扫描其他包在@SpringBootApplication注解里添加scanBasePackages="com.learn"用于指定扫描包
 */
@SpringBootApplication(scanBasePackages="com.learn")
public class MainApplication {

    public static void main(String[] args) {
        //1.返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2.容器组件名称
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        //3.从容器中获取组件
//        Pet tom1 = run.getBean("tom", Pet.class);
//        Pet tom2 = run.getBean("tom", Pet.class);
//        System.out.println(tom1==tom2);
//
//        //如果@Configuration(proxyBeanMethods=true)，代理对象调用方法，springboot会从容器中查找是否有该返回值的实例，确保组件单例
//        MyConfig bean = run.getBean(MyConfig.class);
//        User user = bean.user01();
//        User user1 = bean.user01();
//        System.out.println(user==user1);
//
//        User user2 = run.getBean("user01", User.class);
//        Pet tom3 = run.getBean("tom", Pet.class);
//        System.out.println(user.getPet()==tom3);
//
//        System.out.println("==================");
//        String[] beanNamesForType = run.getBeanNamesForType(User.class);
//        for (String s : beanNamesForType) {
//            System.out.println(s);
//        }
//
//        DBHelper bean1 = run.getBean(DBHelper.class);
//        System.out.println(bean1);

        boolean tom = run.containsBean("tom");//判断容器中有没有该组件
        System.out.println("容器中tom组件："+tom);

        boolean user01 = run.containsBean("user01");
        System.out.println("容器中user01组件："+user01);

        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println(haha);
        System.out.println(hehe);

        boolean myConfig = run.containsBean("myConfig");
        System.out.println(myConfig);
    }

}
