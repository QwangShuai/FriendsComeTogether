package com.youzhiapp.data.cache;

public class CacheEntity {
	
	private boolean isValid =false;//是否有效
	private String jsonStr;//缓存的json字符串
	private long updataTime=0l;
	
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	public long getUpdataTime() {
		return updataTime;
	}
	public void setUpdataTime(long updataTime) {
		this.updataTime = updataTime;
	}
	
	

}
