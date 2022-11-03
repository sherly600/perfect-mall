package com.pmall.shopping.pmallshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.pmall.shopping")
@SpringBootApplication
public class pmallShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(pmallShoppingApplication.class, args);
    }

}
