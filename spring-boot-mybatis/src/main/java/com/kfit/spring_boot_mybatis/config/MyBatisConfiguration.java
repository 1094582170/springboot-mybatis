package com.kfit.spring_boot_mybatis.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfiguration {
	//这里设置pagehelper会覆盖配置文件
//	@Bean
//    public PageHelper pageHelper() {
//		System.out.println("MyBatisConfiguration.pageHelper()");
//        PageHelper pageHelper = new PageHelper();
//        Properties p = new Properties();
//        p.setProperty("offsetAsPageNum", "true");
//        p.setProperty("rowBoundsWithCount", "true");
//        p.setProperty("reasonable", "true");
//        pageHelper.setProperties(p);
//        return pageHelper;
//    }
}
