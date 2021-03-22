package com.example.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查
 * 1.配置好拦截器要拦截那些请求
 * 2.把这些配置放在容器中
 *
 * 拦截器原理：
 * 1、根据当前请求，找到HandlerExecutionChain【可以处理请求的handler以及handler的所有 拦截器】
 * 2、先来顺序执行 所有拦截器的 preHandle方法
 * • 1、如果当前拦截器prehandler返回为true。则执行下一个拦截器的preHandle
 * • 2、如果当前拦截器返回为false。直接    倒序执行所有已经执行了的拦截器的  afterCompletion；
 * 3、如果任何一个拦截器返回false。直接跳出不执行目标方法
 * 4、所有拦截器都返回True。执行目标方法
 * 5、倒序执行所有拦截器的postHandle方法。
 * 6、前面的步骤有任何异常都会直接倒序触发 afterCompletion（未执行成功的拦截器的afterCompletion不会执行）
 * 7、页面成功渲染完成以后，也会倒序触发 afterCompletion
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle开始=======");
        log.info("拦截器拦截的路径："+request.getRequestURI());
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            return true;
        }
        session.setAttribute("msg","请先登陆！");
        response.sendRedirect("/");
        return false;
    }

    /**
     * 目标方法执行后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle开始=======",modelAndView);
    }

    /**
     * 页面渲染完之后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion开始=======",ex);
    }
}
