package com.learn.boot.config;

import com.learn.boot.bean.Pet;
import com.learn.boot.converter.JiegeMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
public class WebConfig /*implements WebMvcConfigurer*/ {

    /**
     * 自定义此组件后WebMvcAutoConfiguration配置类中配置的OrderedHiddenHttpMethodFilter组件则不会生效。
     * 可以更改原先定义的_method属性
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
        filter.setMethodParam("_m");
        return filter;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){//自定义配置
        return new WebMvcConfigurer(){

            //自定义内容协商策略
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> mediaTypes = new HashMap<String,MediaType>();
                mediaTypes.put("json",MediaType.APPLICATION_JSON);
                mediaTypes.put("xml",MediaType.APPLICATION_XML);
                mediaTypes.put("jiege",MediaType.parseMediaType("application/x-jiege"));
                //请求参数策略（根据请求参数中的format字段来确定请求端可接受的媒体类型）
                ParameterContentNegotiationStrategy parameterContentNegotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
//                parameterContentNegotiationStrategy.setParameterName("ff");//更改默认的format字段

                //请求头策略（根据请求头中的Accept字段来确定请求端可接受的媒体类型）
                HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(parameterContentNegotiationStrategy,headerContentNegotiationStrategy));
            }

            //添加自定义的消息转换器======针对返回参数
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new JiegeMessageConverter());
            }

            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper yph = new UrlPathHelper();
                //不移除请求路径中;后的内容，矩阵变量功能就可以生效
                yph.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(yph);
            }

            //自定义converter转换器,将String类型转换为Pet类型=====针对请求参数
            public void addFormatters(FormatterRegistry registry) {
                Converter<String, Pet> converter = new Converter<String,Pet>() {
                    @Override
                    public Pet convert(String s) {
                        Pet p = new Pet();
                        p.setName(s.split(",")[0]);
                        p.setAge(Integer.parseInt(s.split(",")[1]));
                        return p;
                    }

                };
                registry.addConverter(converter);

            }
        };
    }

    /*@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper yph = new UrlPathHelper();
        //不移除请求路径中;后的内容，矩阵变量功能就可以生效
        yph.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(yph);
    }*/

}
