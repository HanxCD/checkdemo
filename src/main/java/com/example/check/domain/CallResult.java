package com.example.check.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <b>System：</b>NCC<br/>
 * <b>Title：</b>CallResult.java<br/>
 * <b>Description：</b> 接口返回结果统一对象 <br/>
 * <b>@author： </b>chenyibing<br/>
 * <b>@date：</b>2018年08月2018/8/21日 15:14<br/>
 * <b>@version：</b> 1.0.0.0<br/>
 * <b>Copyright (c) 2017 ASPire Tech.</b>
 */
@Getter
@Setter
public class CallResult implements Serializable {
	/**
	 * 消息提示
	 */
	private String msg;
	/**
	 * 成功失败标识，true：成功，false:失败
	 */
	private boolean success;
	/**
	 * 返回数据
	 */
	private Object data;

	public CallResult(boolean success){
		this.success = success;
	}

	public CallResult(boolean success,String msg){
		this.success = success;
		this.msg = msg;
	}

	public CallResult(boolean success,String msg,Object data){
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
}
