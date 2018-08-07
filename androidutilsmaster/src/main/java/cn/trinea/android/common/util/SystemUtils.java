package cn.trinea.android.common.util;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * SystemUtils
 *
 * 系统信息工具类，可用于得到线程池合适的大小，目前功能薄弱，后面会进行增强。如： getDefaultThreadPoolSize()
 * 得到跟系统配置相符的线程池大小 源码可见SystemUtils.java，更多方法及更详细参数介绍可见SystemUtils Api Guide。
 */
public class SystemUtils {

	/**
	 * recommend default thread pool size according to system available
	 * processors, {@link #getDefaultThreadPoolSize()}
	 **/
	public static final int DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();

	/**
	 * get recommend default thread pool size
	 *
	 * @return if 2 * availableProcessors + 1 less than 8, return it, else
	 *         return 8;
	 * @see {@link #getDefaultThreadPoolSize(int)} max is 8
	 */
	public static int getDefaultThreadPoolSize() {
		return getDefaultThreadPoolSize(8);
	}

	/**
	 * get recommend default thread pool size
	 *
	 * @param max
	 * @return if 2 * availableProcessors + 1 less than max, return it, else
	 *         return max;
	 */
	public static int getDefaultThreadPoolSize(int max) {
		int availableProcessors = 2 * Runtime.getRuntime()
				.availableProcessors() + 1;
		return availableProcessors > max ? max : availableProcessors;
	}
	/**
	 * 隐藏系统键盘
	 * @param a
	 * @return
	 */
	public static boolean hideSoftInput(Activity a){
		//a.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//它可以用来抑制键盘出现
		return ((InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(a.getCurrentFocus().getWindowToken(), 0);
	}
	/**
	 * 显示系统键盘
	 * @return
	 */
	public static void showSoftInput(final EditText editText, final Context context) {
		editText.requestFocus();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(editText, 0);
			}
		}, 100);


	}
	/**
	 * 判断手机是否有SD卡。
	 *
	 * @return 有SD卡返回true，没有返回false。
	 */
	public static boolean hasSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
}
