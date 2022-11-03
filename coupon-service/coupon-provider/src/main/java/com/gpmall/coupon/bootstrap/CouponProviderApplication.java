package com.pmall.coupon.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.pmall.coupon")
@MapperScan("com.pmall.coupon.dal.persistence")
public class CouponProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponProviderApplication.class, args);
	}

}
