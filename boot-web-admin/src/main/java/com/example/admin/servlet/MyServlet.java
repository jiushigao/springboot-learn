package com.example.admin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的原生servlet为什么不会被springmvc的拦截器所拦截：
 * 拦截器在dispatchServlet中执行，而dispatcherServlet本身也是被注入的原生servlet（通过DispatcherServletRegistrationBean注入），
 * 而dispatcherServlet默认的路径为"/",若自定义路径为"/my",根据精确优先原则，则会调用自定义的servlet(即不会执行拦截器)，
 * 若没有自定义servlet，则会调用dispatcherServlet
 */
//@WebServlet(urlPatterns = "/my")
public class MyServlet extends HttpServlet {//原生servlet

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("66666");
    }
}
