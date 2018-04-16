package com.kfit.spring_boot_mybatis.component;

/**
 * 请求返回不同状态码，固定的状态码对应的描述
 * 
 * Created by feng.wei on 2017/11/14.
 *
 */
public class MsgResult {

	/**
	 * 参数错误
	 */
	public static final String FAILED_PARAM = "参数错误.";
	/**
	 * 系统错误
	 */
	public static final String FAILED_SYSTEM = "系统错误.";
	/**
	 * 对不起，您没有访问权限
	 */
	public static final String FAILED_PERMISSIONS = "对不起,您没有访问权限.";
	/**
	 * 对不起，您不被允许访问此url
	 */
	public static final String FAILED_URL_AUTH = "对不起,您不被允许访问此url.";
	/**
	 * 成功
	 */
	public static final String ACCESSOK = "success.";// 成功

	/**
	 * 失败
	 */
	public static final String FAILED = "失败.";// 失败
}
