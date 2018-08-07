package com.youzhiapp.network.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * 获取手机系统信息 添加权限<uses-permission
 * android:name="android.permission.READ_PHONE_STATE" />
 * 
 */
public class SystemUtil {

	/**
	 * 获取 app版本号
	 * 
	 * @param context
	 *            包名 如"cn.testgethandsetinfo"
	 * @return
	 */
	public static String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取当前应用开发的版本号
	 */

	public static int getVersionCode(Context context) {

		try {

			return context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;

		} catch (NameNotFoundException e) {

			e.printStackTrace();

		}
		return 0;

	}

	/**
	 * 获取手机品牌
	 * 
	 * @return
	 */
	public static String getPhoneBrand() {
		return android.os.Build.BRAND;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public static String getPhoneModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * 获取系统版版号
	 * 
	 * @return
	 */
	public static String getSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	// /**
	// * 获取应用图标
	// * @param context
	// * @return
	// */
	// public static Drawable getAppIcon(Context context){
	//
	// PackageManager packageManager = null;
	// ApplicationInfo applicationInfo = null;
	// try {
	// packageManager =context. getApplicationContext().getPackageManager();
	// applicationInfo =
	// packageManager.getApplicationInfo(context.getPackageName(), 0);
	//
	// } catch (PackageManager.NameNotFoundException e) {
	// e.printStackTrace();
	// }
	//
	// return applicationInfo.loadIcon(packageManager);
	//
	//
	// }
	/**
	 * 启动默认浏览器打开连接
	 * 
	 * @param context
	 * @param url
	 */
	public static void openBrowser(Context context, String url) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri.parse(url);
		intent.setData(content_url);
		context.startActivity(intent);
	}

	/**
	 * 获取程序的名字
	 * 
	 * @param context
	 * @return
	 */
	public static String getApplicationName(Context context) {
		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = context.getApplicationContext()
					.getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(
					context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		String applicationName = (String) packageManager
				.getApplicationLabel(applicationInfo);
		return applicationName;
	}

	/**
	 * 获取imei
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();

	}

	/** 获取本机ip */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						Pattern p = Pattern
								.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
						Matcher m = p.matcher(inetAddress.getHostAddress()
								.toString());
						if (m.matches())
							return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("slg", "获取本机ip失败：" + ex.toString());
		}
		return "";
	}

	/**
	 * 退出程序
	 */
	public static void exitApp() {
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(1);
	}

	/**
	 * .获取手机MAC地址 只有手机开启wifi才能获取到mac地址 <!-- 获取mac地址权限 --> <uses-permission
	 * android:name="android.permission.ACCESS_WIFI_STATE" />
	 */
	public static String getMacAddress(Context context) {
		String result = "";
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		result = wifiInfo.getMacAddress();
		return result;
	}

	/**
	 * 手动检查更新
	 */
//	public static void checkUpdateApp(Context context) {
//		updateApp(context, false, false);
//	}
//
//	public static void checkUpdateAppAotu(Context context, boolean auto) {
//		updateApp(context, false, auto);
//	}

	/**
	 * 检查更新
	 * 
	 * @param context
	 * @param paserUpdate
	 *            是否强制更新
	 */
//	public static void updateApp(final Context context, final boolean paserUpdate, final boolean auto) {
//		final ProgressDialog mProDialog = ProgressDialog.show(context, null,"正在检查版本，请稍后...", false, true);
//
//		SeverRequire.getInstance().getDownPath(context, new OnGetSeverParameter() {
//					@Override
//					public void onSuccessLis(final String down_url) {
////						final String appName = getApplicationName(context);
//						final String appName = "yiwo-person";
//						String version = BaseApplication.MainJsonObjMap.get(SeverRequire.APP_VERSIONS);
//						double serverVersion = 0;
//						try {
//							serverVersion = Double.parseDouble(version);
//						} catch (Exception e) {
//							Log.d("slg", "服务器版本号错误：" + version);
//						}
//
//						String versonStr = BaseApplication.MainJsonObjMap.get(SeverRequire.APP_VERSIONS_NAME);
//						int localVersion = getVersionCode(context);
//						AlertDialog.Builder alert = new AlertDialog.Builder(context);
//
//						if (localVersion < serverVersion) {
//							// 发现新版本，提示用户更新
//							//String info = BaseApplication.MainJsonObjMap.get(SeverRequire.VERSION_INFO);
//							String info="提示用户体验，修复错误。";
//							alert.setTitle("发现新版本" + versonStr).setMessage(info).setPositiveButton(
//											"更新",
//											new DialogInterface.OnClickListener() {
//												public void onClick( DialogInterface dialog, int which) {
//													update(context, appName, down_url);
//												}
//											}).setNegativeButton(
//											"取消",
//											new DialogInterface.OnClickListener() {
//												public void onClick(DialogInterface dialog, int which) {
//													dialog.dismiss();
//													if (paserUpdate) {
//														exitApp();
//													}
//
//												}
//											});
//							alert.create().show();
//
//						} else {
//							if (!auto) {
//								alert.setTitle("无需更新").setMessage("已是最新版本，无需更新").setPositiveButton("确定", null);
//								alert.create().show();
//							}
//
//						}
//					}
//
//					@Override
//					public void onFinish() {
//						if (mProDialog != null) {
//							mProDialog.dismiss();
//						}
//					}
//
//					@Override
//					public void onFailLis() {
//					}
//				});
//
//	}

	/**
	 * 启动更新下载服务
	 * 
	 * @param context
	 * @param appName
	 * @param down_url
	 */
//	private static void update(Context context, String appName, String down_url) {
//
//		// 开启更新服务UpdateService
//		// 这里为了把update更好模块化，可以传一些updateService依赖的值
//		// 如布局ID，资源ID，动态获取的标题,这里以app_name为例
//		Intent updateIntent = new Intent(context, UpdateService.class);
//		updateIntent.putExtra("app_name", appName);
//		updateIntent.putExtra("down_url", down_url);
//		context.startService(updateIntent);
//	}

	/**
	 * 判断当前activity是否在前台运行
	 * 
	 * @return
	 */
	public static boolean isRunningForeground(Context context) {
		String packageName = getPackageName(context);
		String topActivityClassName = getTopActivityName(context);
		if (packageName != null && topActivityClassName != null&& topActivityClassName.startsWith(packageName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取当前显示activity名字
	 */
	public static String getTopActivityName(Context context) {
		String topActivityClassName = null;
		ActivityManager activityManager = (ActivityManager) (context.getSystemService(android.content.Context.ACTIVITY_SERVICE));
		List<RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
		if (runningTaskInfos != null) {
			ComponentName f = runningTaskInfos.get(0).topActivity;
			topActivityClassName = f.getClassName();
		}
		return topActivityClassName;
	}

	/**
	 * 获取当前包名
	 * 
	 * @param context
	 * @return
	 */
	public static String getPackageName(Context context) {
		String packageName = context.getPackageName();
		return packageName;
	}

	/**
	 * 判断App是否在前台运行
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isAppRunningForeground(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String currentPackageName = cn.getPackageName();
		if (!TextUtils.isEmpty(currentPackageName)&& currentPackageName.equals(getPackageName(context))) {
			return true;
		}

		return false;
	}
	/**
	 * 拨打电话
	 * @param context
	 * @param phoneNum
	 */
	public static void callPhone(Context context, String phoneNum) {
		phoneNum = phoneNum.trim();// 删除字符串首部和尾部的空格
		if (phoneNum != null && !phoneNum.equals("")) {		
				// 调用系统的拨号服务实现电话拨打功能
				// 封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ phoneNum));
				context.startActivity(intent);// 内部类

		}	
		
	}
	/**
	 * 拨打电话
	 * @param context
	 * @param tels 已“，”分割的tel
	 */
	public static void callPhones(final Context context, String tels){
		if (tels != null && !tels.equals("")) {	
			tels = tels.replace("'", "");
			final String[] ts = tels.split(",");
			if(ts.length>1){
				  Dialog alertDialog = new AlertDialog.Builder(context).
						    setTitle("请选择：")	
						    .setItems(ts, new DialogInterface.OnClickListener() {						 
							     @Override
							     public void onClick(DialogInterface dialog, int which) {
							    	 callPhone(context, ts[which]);
							     }
						    }).
						    setNegativeButton("取消", null).
						    create();
						  alertDialog.show();
			}else{
				callPhone(context,tels);
			}
		}

	}
	/**
	 * 发送短信
	 */
	public static void sendSMS(Context context,String tel,String text){
		if (tel != null && !tel.equals("")) {	
			Uri uri = Uri.parse("smsto:"+tel);            
			Intent it = new Intent(Intent.ACTION_SENDTO, uri);            
			it.putExtra("sms_body", text);            
			context.startActivity(it);  
		}
	}

}
