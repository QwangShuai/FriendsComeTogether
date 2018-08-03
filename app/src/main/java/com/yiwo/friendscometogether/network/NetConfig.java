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
    //用户插文列表
    public static String userIntercalationListUrl = "action/ac_article/MylistoFinserts";
    //编辑友记
    public static String editorFriendRememberUrl = "action/ac_article/Friendeditor";
    //删除续写
    public static String deleteRenewUrl = "action/ac_article/DeleteRenew";
    //活动报名
    public static String applyActivityUrl = "action/ac_order/user_join";
    //删除友记
    public static String deleteFriendRememberUrl = "action/ac_article/delArticles";
    //发布草稿
    public static String releaseDraftUrl = "action/ac_article/draftRadio";
    //搜索友聚列表
    public static String searchFriendTogetherUrl = "action/ac_activity/search_activity_list";
    //关注活动
    public static String focusOnToFriendTogetherUrl = "action/ac_activity/follow_attention";
    //补充内容
    public static String addContentFriendTogetherUrl = "action/ac_activity/add_travel_info";
    //是否实名认证
    public static String isRealNameUrl = "action/ac_order/check_usercodeok";
    //相册列表
    public static String myPictureListUrl = "action/ac_user/AlbumList";
    //相册上传图片
    public static String myPictureUploadUrl = "action/ac_user/Photoalbum";
    //相册删除图片
    public static String myPictureDeleteUrl = "action/ac_user/deleteAlbum";
    //文章详情
    public static String detailsOfFriendsUrl = "action/ac_article/ArticleContent";
    //浏览历史
    public static String lookHistoryUrl = "action/ac_article/BrowsEhistory";
    //删除浏览历史
    public static String deleteLookHistoryUrl = "action/ac_article/deletehistory";
    //文章点赞
    public static String articlePraiseUrl = "action/ac_article/Give";
    //文章收藏
    public static String articleCollectionUrl = "action/ac_article/Collection";
    //保存用户信息
    public static String saveUserInformationUrl = "action/ac_user/saveUserinfo";
    //用户上传头像
    public static String userUploadHeaderUrl = "action/ac_user/UploadSheader";
    //实名认证
    public static String realNameUrl = "action/ac_user/Realnameauthentication";
    //删除收藏
    public static String deleteCollectionUrl = "action/ac_user/deletecollection";
    //获取插文位置列表
    public static String intercalationLocationUrl = "action/ac_article/IntercalationPosition";
    //添加插文
    public static String insertIntercalationUrl = "action/ac_article/Intercalation";
    //添加关注
    public static String userFocusUrl = "action/ac_user/ExecutiveAttention";
    //取消关注人
    public static String userCancelFocusUrl = "action/ac_user/AbolishConcern";
    //个人中心插文删除
    public static String userDeleteIntercalationFocusUrl = "action/ac_article/Deletetheinsert";
    //队友插文列表
    public static String teamIntercalationListUrl = "action/ac_article/IntercalationList";
    //设置插文是否展示
    public static String intercalationShowUrl = "action/ac_article/Exhibition";
    //设置屏蔽插文
    public static String sheildIntercalationUrl = "action/ac_article/ShieldedArticles";
    //文章评论
    public static String articleCommentUrl = "action/ac_article/ArticleReviews";
    //文章评论列表
    public static String articleCommentListUrl = "action/ac_article/ReviewList";
    //首页友聚
    public static String homeTogetherListUrl = "action/ac_activity/index_activity";
    //回复评论
    public static String replyCommentUrl = "action/ac_article/Reply";
}
