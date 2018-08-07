package com.youzhiapp.network.entity;

import android.content.Context;

import com.youzhiapp.network.action.OnGetRequestUrl;

public class QueueEntity {

	private String key;
	private OnGetRequestUrl listener;
	private Context context;
	
	
	
	
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public OnGetRequestUrl getListener() {
		return listener;
	}
	public void setListener(OnGetRequestUrl listener) {
		this.listener = listener;
	}
	
	
	
	
}
