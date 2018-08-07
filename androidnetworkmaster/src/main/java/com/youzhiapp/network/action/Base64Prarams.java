package com.youzhiapp.network.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.util.Log;

import com.loopj.android.http.RequestParams;
import com.youzhiapp.network.application.BaseApplication;
import com.youzhiapp.network.utils.Base64Util;

public  class Base64Prarams extends RequestParams{



	@Override
	public void put(String key, File file) throws FileNotFoundException {
		
		super.put(key, file);
	}

	@Override
	public void put(String key, InputStream inputStream, String fileName) {
		super.put(key, inputStream, fileName);
	}

	@Override
	public void put(String key, InputStream inputStream) {
		super.put(key, inputStream);
	}

	@Override
	public void put(String key, String value) {
		Log.i("zlx", key+"-------"+value);
		if(!BaseApplication.IS_DEBUG){
			value = Base64Util.encryptBASE64(value);
		}
		super.put(key, value);
	}


	@Override
	public void put(String key, InputStream stream, String name,String contentType) {
		super.put(key, stream, name, contentType);
	}

	public void put(String key, int value){
		put(key,String.valueOf(value));
	}
	public void put(String key, double value){
		put(key,String.valueOf(value));
	}
	public void put(String key, float value){
		put(key,String.valueOf(value));
	}
	
	

}
