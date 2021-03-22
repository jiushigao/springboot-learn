package com.example.admin;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @BootstrapWith(SpringBootTestContextBootstrapper.class)
 * @ExtendWith({SpringExtension.class})  扩展spring功能
 */
@SpringBootTest
@DisplayName("单元测试类")
public class Junit5Test {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @DisplayName("单元测试方法")
    @Test
    public void testDisplayName(){
        System.out.println(123);
    }

    @Disabled//禁用，执行测试类时不会执行此方法
    @DisplayName("单元测试2")
    @Test
    public void test2(){
        System.out.println(321);
        System.out.println(jdbcTemplate);
    }

    @RepeatedTest(5)//设置重复测试次数
    @Test
    public void test3(){
        System.out.println(5555);
    }

    /**
     * 规定方法超时时间，超出规定时间抛出异常
     * @throws InterruptedException
     */
    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    @Test
    public void testTimeOut() throws InterruptedException {
        Thread.sleep(600);
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("测试即将开始");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("测试结束");
    }

    @BeforeAll
    public static void beforeAll(){
        System.out.println("所有测试即将开始");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("所有测试结束");
    }
}
