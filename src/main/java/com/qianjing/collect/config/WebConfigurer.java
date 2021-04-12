package com.qianjing.collect.config;

import com.qianjing.collect.converter.StrToDateTime;
import com.qianjing.collect.interceptor.LoginIntercept;
import com.qianjing.collect.interceptor.PowerIntercept;
import com.qianjing.collect.interceptor.SessionIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private SessionIntercept sessionIntercept;

    @Autowired
    private LoginIntercept loginIntercept;

    @Autowired
    private PowerIntercept powerIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionIntercept);
        registry.addInterceptor(loginIntercept).addPathPatterns("/task/publish","/task/publish/page","/task/collects/page");
        registry.addInterceptor(powerIntercept).addPathPatterns("/task/publish/page","/task/publish","/task/collects/page");
    }

    @Value("${collect.work.root-path}")
    private String actualPath;

    @Value("${collect.work.url-path}")
    private String virtualPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(virtualPath + "**").addResourceLocations(actualPath);
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(31556926);
    }

    @Autowired
    private StrToDateTime strToDateTime;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(strToDateTime);
    }
}