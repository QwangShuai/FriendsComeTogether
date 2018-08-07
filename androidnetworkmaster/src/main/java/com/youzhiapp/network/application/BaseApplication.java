package com.youzhiapp.network.application;

import java.util.Map;
import android.app.Application;

public abstract class BaseApplication extends Application {
	public static BaseApplication INSTANCE;
	public static Map<String, String> RequestMap;// 保存所有请求的地址，每次打开应用更新，如果为空则认为未获得请求连接
	public static Map<String, String> MainJsonObjMap;// main.json的obj内容
	public static String BaseUrl = "";
	public static final BaseSharePreference BASE_SHAREPREFERENCE=new BaseSharePreference();
	public static boolean IS_DEBUG =false;

	@Override
	public void onCreate() {
		super.onCreate();		
		INSTANCE = this;
		BASE_SHAREPREFERENCE.init(this);
		BaseUrl = getBaseUrl();
		IS_DEBUG = getIsDebug();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	

	/**
	 * 获取猪请求地址
	 * @return
	 */
	public abstract String getBaseUrl();
	/**
	 * 是否开启调试
	 * 开启则不启动base64加密
	 * @return
	 */
	public abstract boolean getIsDebug();
	/**
	 * 获取用户id
	 * @return 有则返回，没有返回“0”
	 */
	public abstract String getUserId();
	
	
	
	
	/**
	 * 是否开启程序访问 主接口前百度定位，关闭返回坐标（0.00,0.00）
	 */
	public abstract boolean openBaiduLocation();
	/**
	 * 是否开启程序首次访问主接口，自动提示更新dialog
	 */
	public abstract boolean openUpdateDialog();
	/**
	 * 是否开启强制更新
	 */
	public abstract boolean openCompelUpdate();
	/**
	 * 是否开启强制退出
	 */
	public abstract boolean openCompelExit();
	/**
	 * 是否开启开机图片自动下载
	 */
	public abstract boolean openAutoDownWelocme();
	/**
	 * 设置数据库缓存有效时间   毫秒数
	 * @return
	 */
	public abstract long getCacheTime();
	
}
