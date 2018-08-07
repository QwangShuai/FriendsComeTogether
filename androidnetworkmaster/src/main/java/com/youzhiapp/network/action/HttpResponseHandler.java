package com.youzhiapp.network.action;

import java.net.HttpURLConnection;
import java.util.Map;

import org.apache.http.Header;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.util.LogUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.youzhiapp.data.cache.CacheEntity;
import com.youzhiapp.data.cache.DataCache;
import com.youzhiapp.data.cache.OnGetCacheLis;
import com.youzhiapp.data.cache.OnGetPageCacheLis;
import com.youzhiapp.network.application.BaseApplication;
import com.youzhiapp.network.entity.BaseJsonEntity;
import com.youzhiapp.network.utils.Base64Util;
import com.youzhiapp.network.utils.FastJsonUtils;

import cn.trinea.android.common.util.LogUtil;

public abstract class HttpResponseHandler extends AsyncHttpResponseHandler{

	private static final String CODE_OK="200";//请求返回值成功可用
//	private static final String CODE_NO="400";//请求返回值错误不可用
	private static final String CODE="code";
//	private static final String MESSAGE="message";
//	private static final String OBJ="obj";
	private Context _context;
	private String errorMsg;
	private boolean isCache = false;//是否开启缓存
	private String cacheKey ="";//缓存key
	private long validTimeLong = BaseApplication.INSTANCE.getCacheTime();//缓存有效时间 
	public static final int FIRST_PAGE =1;
	/**
	 * 构造方法异步http请求
	 */
	public HttpResponseHandler(){
		
	}
	
	public HttpResponseHandler(Context context,String errorMsg){
		this._context = context;
		this.errorMsg =errorMsg;
	}
	
	/**
	 * 开启缓存
	 * @param cacheKey
	 */
	public void openCache(String cacheKey){
		this.cacheKey = cacheKey;
		isCache= true;
	}

	/**
	 * 开启分页列表，首页缓存
	 * @param cacheKey
	 * @param page
	 */
	public  void openPageCache(String cacheKey,int page){
		if(page==FIRST_PAGE){
			openCache(cacheKey);
		}else{
			isCache = false;
		}
		
	}
	
	
	@Override
	public void onFailure(Throwable arg0, String arg1) {
		super.onFailure(arg0, arg1);	
		String error ="网络不给力";		
		LogUtil.getInstance().error(arg1==null?"onFailure网络错误arg1=null":arg1);
		
		if(errorMsg!=null&&!errorMsg.equals("")){
			error = errorMsg;			
		}
		if(!BaseApplication.IS_DEBUG){
			error ="网络不给力";
		}
	
		onResponeseFail(arg0, error);
		BaseJsonEntity baseJson = new BaseJsonEntity();
		baseJson.setMessage(error);
		baseJson.setCode("400");
		baseJson.setObj("");
		onResponeseFail(baseJson);
	}



	@Override
	public void onFinish() {
		super.onFinish();
		onResponesefinish();
	}

	@Override
	public void onStart() {
		super.onStart();
	
		onResponeseStart();
	}

	@Override
	public void onSuccess(int arg0, String arg1) {
		super.onSuccess(arg0, arg1);
		if(arg0==HttpURLConnection.HTTP_OK){
			try {
				if(!BaseApplication.IS_DEBUG&&!arg1.equals("")){//是否是调试模式					
					arg1 = Base64Util.decryptBASE64(arg1);					
				}
				LogUtil.getInstance().info("Josn返回值:"+arg1);
				if(FastJsonUtils.isJson(arg1)){
					Map<String,String> respCode = FastJsonUtils.getStrArray(arg1, CODE);
					String code = respCode.get(CODE);
					BaseJsonEntity baseJson = FastJsonUtils.parseObject(arg1, BaseJsonEntity.class);
					if(code!=null&&code.equals(CODE_OK)){
						onResponeseSucess(arg0,baseJson);//成功
						if(isCache){//开启缓存
							DataCache.getInstance(BaseApplication.INSTANCE).saveToCache(cacheKey,arg1,getCacheValidTimeLong());
							isCache = false;
						}				
					}else{
						onResponeseFail(baseJson);
						onResponeseFail(new Throwable("code错误：code="+code),baseJson.getMessage());
					}
				}else{
					onResponeseFail(new BaseJsonEntity("数据错误"));
					onResponeseFail(new Throwable("返回数据不是json格式"),"数据错误");
				}
			} catch (Exception e) {
				LogUtil.getInstance().error("Josn返回值错误:"+arg1);
				onResponeseFail(new Throwable("Josn返回值错误:"),arg1);
				onResponeseFail(new BaseJsonEntity("Josn返回值错误"));
			}	
			
		}else{
			onResponeseFail(new BaseJsonEntity("数据错误"));
			onResponeseFail(new Throwable("http响应值："+arg0),"服务器响应值错误");
		}
		
		
		
	}
	/**
	 * 当请求成功code为200时候调用
	 * @param code 项目定义的服务器响应值
	 */
	public abstract void onResponeseSucess(int code, BaseJsonEntity baseJson);
	/**
	 * 请求发起前调用
	 */
	public  void onResponeseStart(){};
	/**
	 * 请求完成时候调用 无论失败成功都会调用
	 */
	public  void onResponesefinish(){};
	/**
	 * 请求超时，服务器无响应，无网络时候调用，错误调用
	 * @param reason 异常
	 * @param e 错误原因
	 */
	public  void onResponeseFail(Throwable reason, String e){};
	
	/**
	 * 错误调用
	 * @param baseJson
	 */
	public void onResponeseFail(BaseJsonEntity baseJson){}
	/**
	 * 获取有效时长
	 * @return
	 */
	public long getCacheValidTimeLong(){
		return validTimeLong;
	}
	
}
