package com.youzhiapp.network.utils;

import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import com.loopj.android.http.PersistentCookieStore;

import android.content.Context;

/**
 * cookie操纵
 * @author Administrator
 *
 */
public class CookUtil {
	
	private static PersistentCookieStore myCookieStore;
	/**
	 * 在Application中初始化
	 * @param context
	 */
	public void init(Context context){
		 myCookieStore = new PersistentCookieStore(context);  
		AsyncHttpUtil.getClient().setCookieStore(myCookieStore);  	
	}
	/**
	 * 保存httpcookid
	 * @param key
	 * @param cookie
	 * @param domain 领域（如：com.slg）
	 */
	public static void saveCookie(String key,String cookie,String domain){
		BasicClientCookie newCookie = new BasicClientCookie(key,cookie);  
		newCookie.setVersion(1);  
		newCookie.setDomain(domain);//设置使用领域  
		newCookie.setPath("/");  
		myCookieStore.addCookie(newCookie); 
	}
	/**
	 * 根据名字获取cookie的value
	 * @param key
	 * @return 没有返回“”
	 */
	public static String getCookid(String key){
		String cookie ="";
		List<Cookie> cookies = myCookieStore.getCookies();
		for(Cookie c:cookies){
			if(c.getName().equals(key)){
				cookie= c.getValue();
			}
		}	
		return cookie;
	}

}
