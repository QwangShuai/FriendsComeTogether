package com.yiwo.friendscometogether;

/**
 * Created by Administrator on 2018/7/13.
 */

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;


import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.vise.xsnow.http.ViseHttp;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.network.UMConfig;
import com.yiwo.friendscometogether.utils.FTPTimeCount;
import com.yiwo.friendscometogether.utils.TimeCount;

import java.io.IOException;


/**
 * Created by 刘佳 on 2017/2/27.
 */
public class MyApplication extends Application {
    public static String versionCode = "V1.0.1";
    // 注册获取验证码倒计时
    public static TimeCount timecount;
    // 修改密码获取验证码倒计时
    public static FTPTimeCount ftptimecount;
    // log开关
    public static Boolean logSwitch = true;
    public static String pak_Name;
    public static String Version_Name;
    public static String Phone_model;
    public static String Phone_system_version;
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapterTools.init(this);
        UMShareAPI.get(this);
        UMConfigure.init(this,"5b5579fbb27b0a608200000d"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        {
            PlatformConfig.setWeixin(UMConfig.WECHAT_APPID,UMConfig.WECHAT_APPSECRET);
        }
        ViseHttp.init(this);
        ViseHttp.CONFIG()
                //配置请求主机地址
                .baseUrl(NetConfig.BaseUrl);

        //oncreate方法中写
        timecount =  new TimeCount(60000, 1000);
        ftptimecount =  new FTPTimeCount(60000, 1000);

    }
    /**
     * 获取当前客户端版本信息
     */
    private void getCurrentVersion() {

        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(
                    this.getPackageName(), 0);
            Version_Name = info.versionName;
            versionCode = "V" + Version_Name;
            Phone_model = Build.MODEL;// 手机型号
            Phone_system_version = "android " + Build.VERSION.RELEASE;// android系统版本信息
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }
}
