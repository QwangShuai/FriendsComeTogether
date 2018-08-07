package cn.trinea.android.common.util;

import java.lang.reflect.Method;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class AppSizeUtil {

	private Handler handler =null;
	public static final String ATTR_PACKAGE_STATS="PackageStats";
	public static final int RESULT_OK=1;
	public static final int RESULT_NO =0;
	
	public AppSizeUtil() {

	}

	public void getpkginfo(Activity activity, String pkg,Handler handler) {
		this.handler = handler;
		PackageManager pm = activity.getPackageManager();
		try {
			Method getPackageSizeInfo = pm.getClass().getMethod("getPackageSizeInfo", String.class);
			getPackageSizeInfo.invoke(pm, pkg, new PkgSizeObserver());
			//getPackageSizeInfo.invoke(pm, pkg, android.os.Process.myUid()/100000,new PkgSizeObserver());  
		} catch (Exception e) {
		}
	}

	private class PkgSizeObserver {
		public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) {
			if(handler!=null){
				  Message msg = handler.obtainMessage(succeeded?RESULT_OK:RESULT_NO);
		             Bundle data = new Bundle();
		             data.putParcelable(ATTR_PACKAGE_STATS, pStats);
		             msg.setData(data);
		             handler.sendMessage(msg);
			}

		}
	}

	public String formatFileSize(long length) {
		String result = null;
		int sub_string = 0;
		if (length >= 1073741824) {
			sub_string = String.valueOf((float) length / 1073741824).indexOf(
					".");
			result = ((float) length / 1073741824 + "000").substring(0,
					sub_string + 3) + "GB";
		} else if (length >= 1048576) {
			sub_string = String.valueOf((float) length / 1048576).indexOf(".");
			result = ((float) length / 1048576 + "000").substring(0,
					sub_string + 3) + "MB";
		} else if (length >= 1024) {
			sub_string = String.valueOf((float) length / 1024).indexOf(".");
			result = ((float) length / 1024 + "000").substring(0,
					sub_string + 3) + "KB";
		} else if (length < 1024)
			result = Long.toString(length) + "B";
		return result;
	}



}
