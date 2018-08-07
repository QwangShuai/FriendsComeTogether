package cn.trinea.android.common.util;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;


public class DialogUtils {

	public static Dialog getAppDialog(Context context) {
		final Dialog dialog = new Dialog(context);
		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		return dialog;
	}
	
}
