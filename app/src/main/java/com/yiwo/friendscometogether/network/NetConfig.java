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
    //文章详情
    public static String articleContentUrl = "action/ac_article/article_slide";
    //友聚列表
    public static String friendsTogetherUrl = "action/ac_activity/activity_all_list";
    //友聚详情
    public static String friendsTogetherDetailsUrl = "action/ac_activity/activity_info";
    //首页友记热门列表
    public static String homeHotFriendsRememberUrl = "action/ac_article/index_fmrecommend";
    //热门城市
    public static String hotCityUrl = "action/ac_activity/hot_city";
    //获取用户信息
    public static String userInformation = "action/ac_user/UserInformation";
    //我的关注
    public static String userFocus = "action/ac_user/my_look";
    //我的收藏
    public static String userCollection = "action/ac_user/Usercollection";
    //我的评论
    public static String userComment = "action/ac_article/article_comment_list";
    //获取标签
    public static String userLabel = "action/ac_public/Label";
    //友记发布
    public static String userRelease = "action/ac_article/InsertArticle";
    //用户友记列表(草稿)
    public static String userRemember = "action/ac_article/Listofarticles";
    //添加插文
    public static String userIntercalation = "action/ac_article/Intercalation";
    //续写友记
    public static String userRenewTheArticle = "action/ac_article/RenewTheArticle";
    //创建活动
    public static String createActivityUrl = "action/ac_activity/add_travel";
}
