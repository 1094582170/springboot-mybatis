package com.kfit.spring_boot_mybatis.component;

import java.io.Serializable;

/**
 * 接口API统一返回的格式；
 * 
 * Created by feng.wei on 2017/08/11.
 *
 */
public class JSONResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer errcode;

	private String errmsg;

	private T data;

	public JSONResult() {
	}

	public JSONResult(Integer errcode, String errmsg) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public JSONResult(Integer errcode, String errmsg, T data) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
		this.data = data;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> JSONResult<T> Error(Integer errcode, String errmsg) {
		 return new JSONResult<T>(errcode, errmsg);
	}

	public static <T> JSONResult<T> systemError(String errmsg) {
		return new JSONResult<T>(CodeResult.CODE_FAILED_SYSTEM,errmsg);
	}

}
