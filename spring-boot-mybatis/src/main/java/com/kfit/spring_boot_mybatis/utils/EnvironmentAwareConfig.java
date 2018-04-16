package com.kfit.spring_boot_mybatis.utils;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvironmentAwareConfig implements EnvironmentAware{

	private static Environment environment;

	private static RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment e) {
		environment = e;
		//设置前缀
		propertyResolver  = new RelaxedPropertyResolver(e, "");
	}

	public static RelaxedPropertyResolver getPropertyResolver() {
		return propertyResolver;
	}

	public static void setPropertyResolver(RelaxedPropertyResolver propertyResolver) {
		EnvironmentAwareConfig.propertyResolver = propertyResolver;
	}

	public static Environment getEnvironment() {
		return environment;
	}
	
	

}
