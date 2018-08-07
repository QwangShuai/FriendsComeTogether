package com.youzhiapp.network.utils;



import android.util.Log;

import com.youzhiapp.network.action.Base64Prarams;
import com.youzhiapp.network.action.HttpResponseHandler;

/**
 * 优智网络http请求 
 *
 */
public class BaseHttpUtil {
	/**
	 * 无参数post请求
	 * @param urlString 请求地址
	 * @param responHandler 执行响应
	 */
	public  static void post(String urlString,HttpResponseHandler responHandler){
		Log.i("zlx", "URL==="+urlString);
		Base64Prarams params = new Base64Prarams();
		String key = MD5Util.getMD5Key(urlString);
		params.put("app_key",key );
		AsyncHttpUtil.post(urlString,params ,responHandler);

	};
	/**
	 * 有参数post请求
	 * @param urlString请求地址
	 * @param params 请求参数
	 * 用法（1）：普通参数
	 *  		RequestParams params = new RequestParams(); // 绑定参数
	 * 			 params.put("size", "10");
	 * 用法（2）：上传文件参数
	 * 			File file = new File(“文件路径，如：\mnt\scard\test.img”);
	 * 			RequestParams params = new RequestParams();
				params.put(“文件名key”, file);
	 * @param responHandler 执行响应
	 */
	public static void post(String urlString, Base64Prarams params,HttpResponseHandler responHandler) {
		Log.i("zlx", "URL==="+urlString);
		String key = MD5Util.getMD5Key(urlString);
		params.put("app_key",key);
		AsyncHttpUtil.post(urlString,params ,responHandler);
	}
	

}
