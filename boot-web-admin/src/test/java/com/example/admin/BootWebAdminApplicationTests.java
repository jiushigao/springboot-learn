package com.example.admin;

import com.example.admin.bean.UserPlus;
import com.example.admin.mapper.UserPlusMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@SpringBootTest
class BootWebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserPlusMapper userPlusMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Test
    void contextLoads() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_user");
        for (Map<String, Object> map : maps) {
            Set<String> strings = map.keySet();
            Iterator<String> iterator = strings.iterator();
            while(iterator.hasNext()){
                String key = iterator.next();
                log.info(key+":"+map.get(key));
            }
        }

        log.info("数据源类型："+dataSource.getClass());
    }

    @Test
    public void test(){
        UserPlus userPlus = userPlusMapper.selectById(1);
        System.out.println(userPlus);
    }

    @Test
    public void test1() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserPlusMapper mapper = sqlSession.getMapper(UserPlusMapper.class);
        UserPlus userPlus = mapper.selectById(2);
        System.out.println(userPlus);

    }

    /**
     * springboot的redis起步依赖默认使用lettuce操作redis，
     * 可以通过引入jedis的jar包并更改配置spring.redis.client-type=jedis来切换为jedis
     */
    @Test
    public void testRedis(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("hello","world");
        String hello = operations.get("hello");
        System.out.println(hello);

        System.out.println(redisConnectionFactory.getClass());
    }


}
