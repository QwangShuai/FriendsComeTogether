package com.youzhiapp.network.action;

import android.content.Context;
import android.util.Log;

import com.youzhiapp.data.cache.DataFormType;
import com.youzhiapp.network.action.BaseAction.OnGetBaseAction;
import com.youzhiapp.network.application.BaseApplication;

/**
 * 获取系统要求
 * 
 */
public class SeverRequire {

	public static final String APP_VERSIONS = "android_v";//当前版本int
	public static final String DOWN_URL = "android_download";
	private static final String UPDAT_INFO = "android_desc";
	private static final String IS_SHOW_COVER = "is_have_img";
	private static final String COVER_URL = "face_img_url";
	private static final String COVER_BEGIN_TIME = "img_begintime";
	private static final String COVER_END_TIME = "img_endtime";
	private static final String COVER_TYPE = "img_click_type";
	private static final String COVER_JUMP_URL = "img_click_url";	
	public  static final String APP_VERSIONS_NAME ="android_ver_name";//版本名称
	public static final String VERSION_INFO="android_desc";//版本简介
	
	private volatile static SeverRequire INSTANCE=null;

	public static SeverRequire getInstance() {
		if (INSTANCE == null) {
			synchronized (SeverRequire.class) {
				if (INSTANCE == null) {
					INSTANCE = new SeverRequire();
				}
			}
		}
		return INSTANCE;
	}


	private  void getMainJsonObjMapValue(Context context,final String key,final OnGetSeverParameter lis) {
			
		BaseAction.getInstance().getRequestUrl(context,key,DataFormType.HTTP,null, new OnGetRequestUrl() {
			
			@Override
			public void onSuccess(String urlString) {
				lis.onSuccessLis(BaseApplication.MainJsonObjMap.get(key));
				
			}
			@Override
			public void onFail(Throwable reason, String e) {
				super.onFail(reason, e);
				lis.onFailLis();
			}

			@Override
			public void onFinish() {
				lis.onFinish();					
			}
		});
		
		
	}

	/**
	 * 获取服务器当前软件版本号
	 * 
	 * @return
	 */
	public  void getAppVersions(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,APP_VERSIONS, lis);
	}
	/**
	 * 获取服务器当前软件版本名称
	 * @param lis
	 */
	public  void getAppVersionsName(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,APP_VERSIONS_NAME, lis);
	}

	/**
	 * 获取下载地址
	 * 
	 * @return
	 */
	public  void getDownPath(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue( context,DOWN_URL, lis);

	}

	/**
	 * 获取更行简介
	 * 
	 * @param lis
	 * @return
	 */
	public  void getUpdateInfo(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,UPDAT_INFO, lis);

	}

	/**
	 * 获取是否显示封面
	 * 
	 * @return 显示返回“1”
	 */
	public  void isShowCover(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,IS_SHOW_COVER, lis);
	}

	/**
	 * 获取显示封面url路径
	 * 
	 * @return
	 */
	public  void getCoverUrl(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,COVER_URL, lis);
	}

	/**
	 * 开始封面时间戳
	 * 
	 * @return
	 */
	public  void getBeginCoverTime(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,COVER_BEGIN_TIME, lis);
	}

	/**
	 * 结束封面时间戳
	 * 
	 * @return(0为跳转html,1为跳转程序内部)
	 */
	public  void getEndCoverTime(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,COVER_END_TIME, lis);
	}

	/**
	 * 封面类型
	 * 
	 * @return
	 */
	public  void getCoverType(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,COVER_TYPE, lis);
	}

	/**
	 * 点击封面跳转地址
	 * 
	 * @return
	 */
	public  void getCoverJumpUrl(Context context,OnGetSeverParameter lis) {
		getMainJsonObjMapValue(context,COVER_JUMP_URL, lis);
	}

	public interface OnGetSeverParameter {
		public void onSuccessLis(String parameter);

		public void onFailLis();
		
		public void onFinish();
	}

}
