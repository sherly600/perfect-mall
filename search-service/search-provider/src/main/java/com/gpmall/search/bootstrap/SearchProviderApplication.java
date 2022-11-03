package com.pmall.search.bootstrap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 搜索服务启动类
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.pmall.search")
@MapperScan(basePackages = "com.pmall.search.dal")
@EnableElasticsearchRepositories(basePackages = "com.pmall.search.repository")
public class SearchProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchProviderApplication.class, args);
	}

}

