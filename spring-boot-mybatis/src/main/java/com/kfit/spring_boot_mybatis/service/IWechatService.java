package com.kfit.spring_boot_mybatis.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信公众号操作 IWXService
 *
 * Created by zhang.yang on 20170920.
 */
public interface IWechatService {
	/**
	 * 微信服务器回调
	 * 
	 * @param request
	 * @return
	 */
	String wechatServerCallback(HttpServletRequest request) throws Exception;

 
	

	/**
	 * 获取带参数的二维码流
	 * 主要参数： 创建二维码的时间戳
	 * 
	 * @param request
	 * @return
	 */
	Map<String, String> getQrCode(HttpServletRequest request) throws Exception;

	/**
	 * 微信服务器校验回调
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	boolean checkName(String signature, String timestamp, String nonce);
	
}
