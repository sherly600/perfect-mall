package com.pmall.comment.bootstrap;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.pmall.comment")
@MapperScan("com.pmall.comment.dal")
public class CommentProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentProviderApplication.class, args);
    }
}
