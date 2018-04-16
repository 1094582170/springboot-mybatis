package com.kfit.spring_boot_mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.kfit.spring_boot_mybatis.pojo.db.Demo;
import com.kfit.spring_boot_mybatis.pojo.db.GLiveInfo;

import tk.mybatis.mapper.util.Sqls;

public interface DemoMappper {
	
	//#{name}:参数占位符
	@Select("select * from t where name=#{name}")
	public List<Demo> likeName(String name);
	
	
	@Select("select * from t where id = #{id}")
	public Demo getById(long id);
	
	@Select("select name from t where id = #{id}")
	public String getNameById(long id);

	
	/**
	 * 保存数据.
	 */
	@Insert("insert into t(name) values(#{name})")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	public void save(Demo demo);
	
	
	
}
