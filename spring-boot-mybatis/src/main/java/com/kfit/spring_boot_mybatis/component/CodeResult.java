package com.kfit.spring_boot_mybatis.component;

/**
 * 请求返回的状态码标识类
 *
 * Created by feng.wei on 2017/07/11.
 *
 */

public class CodeResult {

	/**
	 * CODE_SUCCESS(0成功)
	 */
	public static final Integer CODE_SUCCESS = 0;
	/**
	 * CODE_FAILED(4失败)
	 */
	public static final Integer CODE_FAILED = 4;

	/**
	 * FAILED_SYSTEM(1系统失败)
	 */
	public static final Integer CODE_FAILED_SYSTEM = 1;
	/**
	 * CODE_FAILED_PARAM(2参数错误)
	 */
	public static final Integer CODE_FAILED_PARAM = 2;
	/**
	 * CODE_FAILED_NO_AUTH(没有权限)
	 */
	public static final Integer CODE_FAILED_NO_AUTH = 3;
	/**
	 * CODE_FAILED_NO_THIS_URL_AUTH(没有当前url的权限)
	 */
	public static final Integer CODE_FAILED_NO_THIS_URL_AUTH = 5;
	/**
	 * 支付继续轮询的标识 10
	 */
	public static final Integer PAY_POLLING_CODE = 10;
	/**
	 * 远程服务不可用的标识 10
	 */
	public static final Integer REMOTE_INVOCATION_ERROR = 20;

}
