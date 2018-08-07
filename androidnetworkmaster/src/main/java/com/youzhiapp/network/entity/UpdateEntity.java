package com.youzhiapp.network.entity;

import java.io.Serializable;


/**
 *  应用程序更新实体类
 * @version 1.0
 * @created 2012-3-21
 */
public class UpdateEntity implements Serializable{

	private int versionCode;//版本号
	private String versionName;//版本名
	private String downloadUrl;//下载地址
	private String updateLog;//版本简介
	
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getUpdateLog() {
		return updateLog;
	}
	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}
	
	
}
