package com.example.admin.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
//@WebListener
public class MyServletContextListener implements ServletContextListener {//原生监听器
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("servletContext已创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("servletContext已销毁");
    }
}
