package com.kfit.spring_boot_mybatis.mapper;

import java.util.List;

import com.kfit.spring_boot_mybatis.config.MyMapper;
import com.kfit.spring_boot_mybatis.pojo.db.GLiveInfo;

import tk.mybatis.mapper.entity.Example;

public interface GLiveInfoMapper extends MyMapper<GLiveInfo> {
	List<GLiveInfoMapper> ss(Example example);
}