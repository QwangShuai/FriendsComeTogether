package com.youzhiapp.network.utils;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.entity.StringEntity;


import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;

import com.loopj.android.http.AsyncHttpResponseHandler;

import com.loopj.android.http.BinaryHttpResponseHandler;

import com.loopj.android.http.JsonHttpResponseHandler;

import com.loopj.android.http.RequestParams;

/**
 * http 请求 权限<uses-permission android:name="android.permission.INTERNET" />
 * 
 * @author slg
 * 
 */
public class AsyncHttpUtil {

	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象

	static

	{

		client.setTimeout(30*1000); // 设置链接超时，如果不设置，默认为30s
//		client.setMaxRetriesAndTimeout(2, 30*1000);
	}

	/**
	 * 取消请求
	 * @param context
	 * @param b
	 */
//	public static void cancelRequests(Context context ,boolean b){
//		client.cancelRequests(context, b);
//	}
	
	/**
	 * http无参数get请求
	 * 
	 * @param res
	 *            用法： String urlString =
	 *            "http://client.azrj.cn/json/cook/cook_list.jsp?type=1&p=2
	 *            HttpUtil.get(urlString, new AsyncHttpResponseHandler() {
	 *            
		 *            public void onSuccess(String arg0) { // 获取数据成功会调用这里
		 * 
		 *            };
		 *            public void onFailure(Throwable arg0) { // 失败，调用
		 * 
		 *            };
		 * 
		 *            public void onFinish() { // 完成后调用，失败，成功，都要掉
		 * 
		 *            };
	 * 
	 *            });
	 * 
	 *            }
	 * 
	 * 
	 */
	public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象

	{

		client.get(urlString, res);

	}

	public static void post(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象

	{

		client.post(urlString, res);

	}

	/**
	 * http有参数get请求
	 * 
	 * @param urlString
	 *            请求网址
	 * @param params
	 *            请求参数
	 * @param res
	 *            异步执行
	 */
	public static void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) 

	{

		client.get(urlString, params, res);

	}

	public static void post(String urlString, RequestParams params,AsyncHttpResponseHandler res) 

	{

		client.post(urlString, params, res);

	}

	public static void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组

	{

		client.get(urlString, res);

	}

	public static void post(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组

	{

		client.post(urlString, res);

	}

	/**
	 * 
	 * @param urlString
	 * @param params
	 * @param res
	 *            * 用法（1）：返回json字符串 String urlString =
	 *            "http://client.azrj.cn/json/cook/cook_list.jsp?";
	 * 
	 *            RequestParams params = new RequestParams(); // 绑定参数
	 *            params.put("size", "10");
	 * 
	 *            HttpUtil.get(urlString, params, new JsonHttpResponseHandler()
	 *            {
	 * 
	 *            public void onFailure(Throwable arg0) {
	 * 
	 *            };
	 * 
	 *            public void onFinish() {
	 * 
	 *            };
	 * 
	 *            public void onSuccess(JSONObject arg0) {
	 *            //返回的是JSONObject，会调用这里
	 * 
	 *            };
	 * 
	 *            });
	 */
	public static void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) // 带参数，获取json对象或者数组

	{

		client.get(urlString, params, res);

	}

	public static void post(String urlString, RequestParams params,
			JsonHttpResponseHandler res) // 带参数，获取json对象或者数组

	{

		client.post(urlString, params, res);

	}

	/**
	 * 
	 * @param uString
	 * @param bHandler
	 *            用法（2）：返回字节 HttpUtil.get(url, new BinaryHttpResponseHandler() {
	 * @Override
	 * 
	 *           public void onSuccess(byte[] arg0) { 
	 *           super.onSuccess(arg0);
	 *         	  File file = Environment.getExternalStorageDirectory();
	 *          File  file2 = new File(file, "cat");
	 *           file2.mkdir(); 
	 *           file2 = new  File(file2, "cat.jpg"); 
	 *           try { 
	 *           FileOutputStream oStream = new FileOutputStream(file2);
	 *            oStream.write(arg0); 
	 *            oStream.flush();
	 *           oStream.close();
				textView.setText("可爱的猫咪已经保存在sdcard里面"); 
				}
	 *           catch (Exception e) 
	 *           { e.printStackTrace();
	 *            Log.i("hck",
	 *           e.toString()); } } });
	 */
	public static void get(String uString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据

	{

		client.get(uString, bHandler);

	}

	public static void post(String uString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据

	{

		client.post(uString, bHandler);

	}

	public static void postJosn(Context context, String uString,
			StringEntity stringEntity, JsonHttpResponseHandler jHandler) {
		client.post(context, uString, stringEntity, "application/json",
				jHandler);
	}

	/**
	 * 上传文件
	 * 
	 * @param uString
	 * @param aHandler
	 */
	public static void postFile(String uString, String filePath,String fileTag, AsyncHttpResponseHandler aHandler) {

		try {
			File file = new File(filePath);
			RequestParams params = new RequestParams();
			params.put(fileTag, file);
			client.post(uString, params, aHandler);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static AsyncHttpClient getClient()
	{

		return client;

	}

}
