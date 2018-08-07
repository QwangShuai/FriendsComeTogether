package com.youzhiapp.data.cache;

import com.youzhiapp.network.entity.BaseJsonEntity;

public interface OnGetCacheLis {
	/**
	 * 当成功获取缓存数据
	 */
	public  void onGetCacheSuccess(long updataTime, BaseJsonEntity baseJson);
	/**
	 * 当数据过期或者无缓存数据
	 * @param hasCache 是否有缓存
	 */
	public  void onGetCacheFail(boolean hasCache);

	
}
