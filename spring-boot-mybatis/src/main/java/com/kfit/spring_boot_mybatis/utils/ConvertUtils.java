package com.kfit.spring_boot_mybatis.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class ConvertUtils {
	private static final Logger logger = LoggerFactory.getLogger(ConvertUtils.class.getSimpleName());
	
		// 把结构相似的两个数组对象相同的属性赋值
		public static <T> List<T> copyArrayByJson(List<?> object, Class<T> clazz) {
			if (CollectionUtils.isEmpty(object)) {
				return new ArrayList<>();
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				return objectMapper.readValue(objectMapper.writeValueAsString(object),
						TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
			} catch (IOException e) {
				logger.info("Failed to convert object: ", e.getMessage());
			}
			return null;
		}

		// 把结构相似的两个对象相同的属性赋值
		public static <T> T copyObjectByJson(Object object, Class<T> clazz) throws Exception {

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				if (object == null) {
					return clazz.newInstance();
				}
				return objectMapper.readValue(objectMapper.writeValueAsString(object), clazz);
			} catch (Exception e) {
				logger.info("对象转换失败"+e.getMessage(), e.getMessage());
				throw new Exception();
			}
		}
}
