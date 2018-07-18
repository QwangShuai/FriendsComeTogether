package com.yiwo.friendscometogether.network;

/**
 * Created by Administrator on 2018/7/12.
 */

public class NetConfig {

    public static String BaseUrl = "http://47.92.136.19/index.php/";
    //登录
    public static String loginUrl = "action/ac_login/login";
    //获取验证码
    public static String getCodeUrl = "action/ac_login/SendCode";
    //用户注册
    public static String registerUrl = "action/ac_login/UseRregister";
    //找回密码
    public static String forgetPwUrl = "action/ac_login/Retrievethepassword";
    //城市列表
    public static String cityUrl = "action/ac_login/get_all_city";
    //文章列表
    public static String friendsRememberUrl = "action/ac_article/article_list";
    //文章列表幻灯片
    public static String friendsRememberBannerUrl = "action/ac_article/article_slide";
}
