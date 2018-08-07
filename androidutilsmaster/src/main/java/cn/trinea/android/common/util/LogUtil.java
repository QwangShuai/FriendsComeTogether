package cn.trinea.android.common.util;
  
import android.util.Log;  
  
/** 
 * ***************************************** 
 * 
 * @作 者:邓湘 
 * @包 名：com.downjoy.myfirstapp 
 * @工程名称:My First App 
 * @创建时间: 2015/3/2 10:46 
 * @文件描述: ***************************************** 
 */  
public class LogUtil {  
    public static  final  boolean DEBUG = true;  
    public static final String TAG = "TAG";  
    private static LogUtil sLogUtil;  
  
    private LogUtil() {  
    }  
  
    public static LogUtil getInstance() {  
        if (sLogUtil == null) {  
            synchronized (LogUtil.class) {  
                if (sLogUtil == null) {  
                    sLogUtil = new LogUtil();  
                }  
            }  
        }  
        return sLogUtil;  
    }  
  
    public void debug(String msg){  
        if(DEBUG){  
            Log.d(TAG,msg);  
        }  
    }  
  
    public void info(String msg){  
        if(DEBUG){  
            Log.i(TAG,msg);  
        }  
    }  
  
    public void error(String msg){  
        if(DEBUG){  
            Log.e(TAG,msg);  
        }  
    }  
  
    public void warn(String msg){  
        if(DEBUG){  
            Log.w(TAG,msg);  
        }  
    }  
}  