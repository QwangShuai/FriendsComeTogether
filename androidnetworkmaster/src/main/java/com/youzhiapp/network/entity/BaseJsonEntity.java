package com.youzhiapp.network.entity;
/**
 * 规定基本json格式
 *
 */
public class BaseJsonEntity {
	private String code="";// 状态码
	private String message="";//返回提示
	private String obj="";//返回json实体
	
	public BaseJsonEntity(){
	}
	public BaseJsonEntity(String message){
		this.message= message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	
	
}
