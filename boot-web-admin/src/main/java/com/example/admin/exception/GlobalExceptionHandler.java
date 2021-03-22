package com.example.admin.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 自定义处理异常方法：
 *  * • 自定义错误页
 *  *      • error/404.html   error/5xx.html；有精确的错误状态码页面就匹配精确，没有就找 4xx.html；如果都没有就触发白页
 *  * • @ControllerAdvice+@ExceptionHandler处理全局异常；底层是 ExceptionHandlerExceptionResolver 支持的
 *  * • @ResponseStatus+自定义异常 ；底层是 ResponseStatusExceptionResolver ，把responsestatus注解的信息底层调用 response.sendError(statusCode, resolvedReason)；tomcat发送的/error
 *  * • Spring底层的异常，如 参数类型转换异常；DefaultHandlerExceptionResolver 处理框架底层的异常。
 *  *      • response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
 *  * • 自定义实现 HandlerExceptionResolver 处理异常；可以作为默认的全局异常处理规则
 *  * • ErrorViewResolver  实现自定义处理异常；
 *  *      • response.sendError 。error请求就会转给controller
 *  *      • 你的异常没有任何人能处理。tomcat底层 response.sendError。error请求就会转给controller
 *  *      • basicErrorController 要去的页面地址是 ErrorViewResolver  ；
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})//处理异常
    public String handlerException(Exception e){
        log.error("异常是：{}",e);
        return "login";
    }


}
