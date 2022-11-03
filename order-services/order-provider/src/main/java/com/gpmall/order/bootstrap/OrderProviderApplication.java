package com.pmall.order.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@ComponentScan(basePackages ={"com.pmall.order","com.pmall.commons.mq"})
@MapperScan(basePackages = "com.pmall.order.dal")
@SpringBootApplication
public class OrderProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderProviderApplication.class, args);
    }

}
