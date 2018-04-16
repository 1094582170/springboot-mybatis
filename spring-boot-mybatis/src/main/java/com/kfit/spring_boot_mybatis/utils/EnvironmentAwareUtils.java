package com.kfit.spring_boot_mybatis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentAwareUtils{

	private static final Logger logger = LoggerFactory.getLogger(EnvironmentAwareUtils.class.getSimpleName());
	
	public static String getProperties(String propertiesName){
		try {
			return EnvironmentAwareConfig.getPropertyResolver().getProperty(propertiesName);
		} catch (Exception e) {
			logger.info("获取环境变量出错",e.getMessage());
		}
		return null;
	}
	

}
