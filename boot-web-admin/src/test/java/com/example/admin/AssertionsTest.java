package com.example.admin;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    @DisplayName("测试简单断言")
    @Test
    void testSimpleAssertion(){
        int cal = cal(2, 3);
        //相等
        assertEquals(5,cal,"业务逻辑错误");
        Object obj1 = new Object();
        Object obj2 = new Object();
        //判断两个引用是否指向同一对象
        assertSame(obj1,obj2,"不是同一对象");
    }
    
    int cal(int a,int b){
        return a+b;
    }

    @DisplayName("数组断言")
    @Test
    void array(){
        assertArrayEquals(new int[]{1,2},new int[]{1,2});
    }

    @DisplayName("组合断言")
    @Test
    void all(){
        /**
         * 所有断言都需要成功
         */
        assertAll("test",
                ()->assertTrue(true&&true),
                ()->assertEquals(1,1));
        System.out.println("测试成功");
    }

    //需要抛出指定异常
    @DisplayName("异常断言")
    @Test
    void testException(){
        assertThrows(ArithmeticException.class,()->{int i = 10/1;},"没有发生数学计算异常");
    }

    @DisplayName("快速失败")
    @Test
    void testFail(){
        if(1==1){
            fail("测试失败");
        }
    }

    @DisplayName("测试前置条件")
    @Test
    void testAssumptions(){
        Assumptions.assumeTrue(false,"前置条件失败");
        System.out.println("======");
    }
}
