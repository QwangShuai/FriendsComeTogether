package cn.trinea.android.common.util;

import android.app.ProgressDialog;
import android.content.Context;

public class ShowDialog {
	
	public static ProgressDialog mProgressDialog = null;
	
	public static void showProgressDialog(Context context,String msg, boolean cancelable) 
	{
   		dismissProgressDialog();
		try{
		
			if(mProgressDialog == null){
				mProgressDialog = new ProgressDialog(context);
				mProgressDialog.setMessage(msg);
				mProgressDialog.setCancelable(cancelable);
				mProgressDialog.setCanceledOnTouchOutside(false);
				mProgressDialog.show();
				mProgressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void showProgressDialog(Context context,int msg, boolean cancelable) 
	{
   		dismissProgressDialog();
		try{
		
			if(mProgressDialog == null){
				mProgressDialog = new ProgressDialog(context);
				mProgressDialog.setMessage(context.getResources().getString(msg));
				mProgressDialog.setCancelable(cancelable);
				mProgressDialog.setCanceledOnTouchOutside(false);
				mProgressDialog.show();
				mProgressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
   public static void dismissProgressDialog() {
		if(null != mProgressDialog) {
			try{
				mProgressDialog.dismiss();
				mProgressDialog = null;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
   
   public static boolean isshowing(){
	   if(null != mProgressDialog){
		   return true;
	   }
	   return false;
   }
   
}
