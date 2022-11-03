package com.pmall.user.pmalluser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.pmall.user")
@SpringBootApplication
public class pmallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(pmallUserApplication.class, args);
    }

}
