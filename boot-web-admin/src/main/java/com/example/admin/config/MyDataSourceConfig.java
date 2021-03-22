package com.example.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@Deprecated//太拉了，用官方starter来整
@Configuration
public class MyDataSourceConfig {

    @ConfigurationProperties("spring.datasource")//和配置文件中的属性绑定
//    @Bean//根据条件装配规则，自定义的数据源会使默认的HikariDataSource数据源失效
    DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setFilters("stat,wall");//开启监控功能,防火墙功能
        return druidDataSource;
    }

    /**
     * 配置druid监控页功能,注入一个servlet
     * @return
     */
//    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean(statViewServlet, "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        return servletRegistrationBean;
    }

    /**
     * WebStatFilter 用于采集web-jdbc关联监控的数据
     * @return
     */
//    @Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> servletRegistrationBean = new FilterRegistrationBean(webStatFilter);
        servletRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        servletRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return servletRegistrationBean;
    }
}
