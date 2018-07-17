package com.yiwo.friendscometogether.network;

/**
 * Created by Administrator on 2018/7/12.
 */

public class NetConfig {

    public static String BaseUrl = "http://47.92.136.19/index.php/";
    //登录
    public static String loginUrl = BaseUrl + "action/ac_login/login";
    //获取验证码
    public static String getCodeUrl = BaseUrl + "action/ac_login/SendCode";
    //用户注册
    public static String registerUrl = BaseUrl + "action/ac_login/UseRregister";
    //找回密码
    public static String forgetPwUrl = BaseUrl +"action/ac_login/Retrievethepassword";
    //城市列表
    public static String cityUrl = BaseUrl +"action/ac_login/get_all_city";
    //文章列表
    public static String friendsRememberUrl = BaseUrl +"action/ac_article/article_list";
    //文章列表幻灯片
    public static String friendsRememberBannerUrl = BaseUrl +"action/ac_article/article_slide";
}
