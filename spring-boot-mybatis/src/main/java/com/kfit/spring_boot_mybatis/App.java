package com.kfit.spring_boot_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot启动类.
 * @author Angel -- 守护天使
 * @version v.0.1
 * @date 2017年1月1日
 */
@SpringBootApplication
@MapperScan("com.kfit.spring_boot_mybatis.mapper.*")//扫描：该包下相应的class,主要是MyBatis的持久化类.
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
