package com.qianjing.collect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.qianjing.collect.dao")
@EnableAspectJAutoProxy
public class CollectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class,args);
    }
}
