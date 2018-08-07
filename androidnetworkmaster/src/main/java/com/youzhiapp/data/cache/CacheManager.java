package com.youzhiapp.data.cache;

import com.youzhiapp.network.entity.BaseJsonEntity;
import com.youzhiapp.network.utils.FastJsonUtils;

import android.content.Context;
import android.util.Log;

public class CacheManager {

	public static void getCache(Context context,String cacheKey,OnGetCacheLis lis){
		CacheEntity entity =DataCache.getInstance(context).queryCache(cacheKey);
		if(lis!=null){
			if(entity!=null&&!entity.getJsonStr().equals("")){	
				BaseJsonEntity baseJson = FastJsonUtils.parseObject(entity.getJsonStr(), BaseJsonEntity.class);
				lis.onGetCacheSuccess(entity.getUpdataTime(),baseJson);
				if(!entity.isValid()){
					lis.onGetCacheFail(true);
				}
				
			}else{				
				lis.onGetCacheFail(false);
			}
		}
		
	}

	public static void getCache(Context context,String cacheKey,OnGetPageCacheLis lis){
		CacheEntity entity =DataCache.getInstance(context).queryCache(cacheKey);
		if(lis!=null){
			if(entity!=null&&!entity.getJsonStr().equals("")){			
				BaseJsonEntity baseJson = FastJsonUtils.parseObject(entity.getJsonStr(), BaseJsonEntity.class);
				lis.onGetCacheSuccess(entity.getUpdataTime(),baseJson);
				if(!entity.isValid()){
					lis.onGetCacheFail(true);
				}
			}else{
				lis.onGetCacheFail(false);
			}
		}
	}

}
