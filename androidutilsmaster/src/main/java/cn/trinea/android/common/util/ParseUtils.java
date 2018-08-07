package cn.trinea.android.common.util;

import android.util.Log;

/**
 * 强转工具类
 * @author slg
 *
 */
public class ParseUtils {
	private static final String LOG_TAG = ParseUtils.class.getSimpleName();

	/**
	 * 强转int
	 * @param str
	 * @param defaultNum
	 */
	public static int ParseToInt(String str,int defaultNum){
		int result = defaultNum;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			Log.d(LOG_TAG, "ParseToInt强转错误："+str);
		}
		return result;
	}
	/**
	 *
	 * @param str
	 * @param defaultNum
	 * @return
	 */
	public static double ParseToDouble(String str,int defaultNum){
		double result = defaultNum;
		try {
			result = Double.parseDouble(str);
		} catch (Exception e) {
			Log.d(LOG_TAG, "ParseToDouble强转错误："+str);
		}
		return result;
	}
	/**
	 *
	 * @param str
	 * @param defaultNum
	 * @return
	 */
	public static float ParseToFloat(String str,int defaultNum){
		float result = defaultNum;
		try {
			result = Float.parseFloat(str);
		} catch (Exception e) {
			Log.d(LOG_TAG, "ParseToFloat强转错误："+str);
		}
		return result;
	}


}
