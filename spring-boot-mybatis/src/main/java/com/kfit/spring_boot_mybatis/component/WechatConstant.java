package com.kfit.spring_boot_mybatis.component;

import org.springframework.beans.factory.annotation.Value;

import com.kfit.spring_boot_mybatis.utils.EnvironmentAwareUtils;

public class WechatConstant {
	
	
	public final static String WX_TOKEN = "langwei";
	
	/**
	 * access token map key
	 */
	public final static String ACCESS_TOKEN = "access_token";
	
	/**
	 * access token map key
	 */
	public final static String ACCESS_TOKEN_CREATE_TIME = "access_token_create_time";
	
	/**
	 * 消息加密密钥由43位字符组成，可随机修改，字符范围为A-Z，a-z，0-9。
	 * 记一下，暂时是明文访问方式
	 */
	//public final static String ENCODING_AES_KEY = "bnbfZctjQbrwhiTBwQlywcHEg4YcTgpjxwWANSdx1v7";
	/**
	 * 指定二维码创建类型
	 * 通过时间戳创建的，用于没有cases id的病例草稿
	 */
	public final static String CREATE_QR_BY_TIME = "10";
	
	/**
	 * 指定二维码创建类型
	 * 通过cases id创建的，用户已经有cases id的病例或者草稿
	 */
	public final static String CREATE_QR_BY_CASESID = "20";
	
	/**
	 * 开发者微信号
	 */
	public final static String WX_ID = EnvironmentAwareUtils.getProperties("weixin.wx_id");
	
	/**
	 * 开发者ID
	 */
	public final static String APP_ID = EnvironmentAwareUtils.getProperties("weixin.app_id");
	
	/**
	 * 开发者密码
	 */
	public final static String APP_KEY = EnvironmentAwareUtils.getProperties("weixin.app_key");
	/**
	 * 二维码有效期 - 6小时
	 */
	public final static Integer QR_INDATE = 60 * 60 * 6;
	
	/**
	 * 微信传图限制最大10M
	 */
	public final static Integer PicMaxSize = 10 * 1024 * 1024;
	
	/**
	 * 临时二维码  
	 */
	public final static String QR_SCENE = "QR_SCENE";  
	
	// 永久二维码  
	//public final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";  
	// 永久二维码(字符串)  
	//public final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";  
	
	/**
	 * 获取access_token
	 */
	public static final String WX_URL_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APP_ID&secret=SECRET";
	
	/**
	 * 下载图片
	 */
	public static final String WX_URL_GET_PIC = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
	/**
	 * 获取二维码的ticket
	 */
	public static final String WX_URL_QR_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取二维码
	 */
	public static final String WX_URL_QR = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	/**
	 * 获取用户信息第二步 获取用户token和openid
	 */
	public static final String WX_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APP_ID+"&secret="+APP_KEY+"&code=CODE&grant_type=authorization_code";
}
