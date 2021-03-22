package com.example.admin.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义异常解析器
 *
 */
/*
* 最高优先级,默认只有两个异常解析器，这个自定义的设为最高优先级后，
* 所有的异常解析都会最先走这个异常解析器，由于返回了modelandview，
* 后面的异常解析器都不会进行处理,所以返回的错误码都是511
* */
//@Order(value= Ordered.HIGHEST_PRECEDENCE)
@Component
class CustomerHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            response.sendError(511,"我喜欢的错误码");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
