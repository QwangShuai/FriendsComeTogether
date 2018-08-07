package com.youzhiapp.network.application;

import android.content.Context;
import android.content.SharedPreferences;



public class  BaseSharePreference {

	private static final String DATABASE_NAME = "BASE_PF";
	private static final String WELCOME_VERSION="WELCOME_VERSION";
	private static final String IS_SHOW_WELCOME="IS_SHOW_WELCOME";
	private static final String WELCOME_SKIP_URL="WELCOME_SKIP_URL";
	private static final String WELCOME_PATH="WELCOME_PATH";
	private static final String WELCOME_TYPE="WELCOME_TYPE";
	private static final String WELCOME_BGEGIN_TIME="WELCOME_BEGIN_TIME";
	private static final String WELCOME_END_TIME="WELCOME_END_TIME";
	private static final String CITY="CITY";
	private static final String CHOOSE_CITY="CHOOSE_CITY";
	
	private SharedPreferences sharedPreferences;

	public void init(Context context)
	{
		sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
	}
	
	
	/**
	 * 欢迎页版本
	 * @param version
	 */
	public void saveWelcomeVer(String version) {
		sharedPreferences.edit().putString(WELCOME_VERSION, version).commit();
		
	}

	public String readWelcomeVer() {
		return sharedPreferences.getString(WELCOME_VERSION, "0");
		
	}
	/**
	 * 是否显示欢迎界面
	 * @param show
	 */
	public void saveIsShowWelcome(String show) {
		sharedPreferences.edit().putString(IS_SHOW_WELCOME, show).commit();
		
	}
	
	public String readIsShowWelcome() {
		return sharedPreferences.getString(IS_SHOW_WELCOME, "1");
	}
	
	/**
	 * 定位的城市
	 * @param show
	 */
	public void saveCity(String show) {
		sharedPreferences.edit().putString(CITY, show).commit();
		
	}
	
	public String readCity() {
		return sharedPreferences.getString(CITY, "");
	}
	
	/**
	 * 保存选择的城市
	 * @param show
	 */
	public void saveChooseCity(String show) {
		sharedPreferences.edit().putString(CHOOSE_CITY, show).commit();
		
	}
	
	public String readChooseCity() {
		return sharedPreferences.getString(CHOOSE_CITY, "");
	}
	/**
	 * 点击跳转web
	 * @param url
	 */
	public void saveSkipUrl(String url) {
		sharedPreferences.edit().putString(WELCOME_SKIP_URL, url).commit();
		
	}
	
	public String readSkipUrl() {
		return sharedPreferences.getString(WELCOME_SKIP_URL, "");
	}
	/**
	 * 图片加载路径
	 * @param path
	 */
	public void saveWelcomePath(String path) {
		sharedPreferences.edit().putString(WELCOME_PATH, path).commit();
		
	}
	
	public String readWelcomePath() {
		return sharedPreferences.getString(WELCOME_PATH, "");
		
	}
	/**
	 * 欢迎类型 0.预留图片 1.网络图片
	 * @param type
	 */
	
	public void saveWelcomeType(String type) {
		sharedPreferences.edit().putString(WELCOME_TYPE, type).commit();
		
	}
	
	public String readWelcomeType() {
		return sharedPreferences.getString(WELCOME_TYPE, "0");
		
	}
	/**
	 * 开始显示欢迎图片时间
	 * @param beginTime
	 */
	public void saveWelcomeBeginTime(long beginTime) {
		sharedPreferences.edit().putLong(WELCOME_BGEGIN_TIME, beginTime).commit();
		
	}
	
	public long readWelcomeBeginTime() {
		return sharedPreferences.getLong(WELCOME_BGEGIN_TIME,0);
	}
	/**
	 * 结束显示欢迎图片时间
	 * @param endTime
	 */
	public void saveWelcomeEndTime(long endTime) {
		sharedPreferences.edit().putLong(WELCOME_END_TIME, endTime).commit();
		
	}
	
	public long readWelcomeEndTime() {
		return sharedPreferences.getLong(WELCOME_END_TIME,0);
	}
	

	
}
