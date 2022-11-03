package com.pmall.cashier.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.pmall.cashier")
@SpringBootApplication
public class pmallCashierApplication {

    public static void main(String[] args) {
        SpringApplication.run(pmallCashierApplication.class, args);
    }

}
