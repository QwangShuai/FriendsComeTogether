package com.youzhiapp.network.action;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.youzhiapp.data.cache.CacheManager;
import com.youzhiapp.data.cache.DataFormType;
import com.youzhiapp.data.cache.OnGetCacheLis;
import com.youzhiapp.data.cache.OnGetPageCacheLis;
import com.youzhiapp.network.application.BaseApplication;
import com.youzhiapp.network.entity.BaseJsonEntity;
import com.youzhiapp.network.entity.QueueEntity;
import com.youzhiapp.network.utils.FastJsonUtils;
import com.youzhiapp.network.utils.SystemUtil;
import com.youzhiapp.network.utils.BaseHttpUtil;

/**
 * 基础请求
 */

public class BaseAction {

    private static final String SYS_TYPE = "android";
    private static final String DEVICE_TYPE = "android";
    private static final String MAIN_JSON_API = "api";// 请求地址json键
    private static final String IS_CLOSE = "is_close";
    private static final String IS_UPDATE = "is_update";
    private static final String TRUE = "1";

    private static final String welcome_img_ver = "img_v";//封面图片版本
    private static final String is_have_img = "is_have_img";//是否有封面图片
    private static final String img_click_type = "img_click_type";//封面图片类型 1.网络，0本地
    private static final String face_img_url = "face_img_url";//封面图片完整路径
    private static final String img_begintime = "img_begintime";//封面图片开始播放时间
    private static final String img_endtime = "img_endtime";//封面图片结束播放时间
    private static final String img_click_url = "img_click_url";
    private static String WELCOME_NATIVE_PATH = "";//下载封面图片保存路径
    private static final String PRELOAD_KEY = "PRELOAD_KEY";
    private static final boolean IS_ORDER = false;//是否启动主接口有序访问

    private volatile static BaseAction INSTANCE = null;

    public static BaseAction getInstance() {
        if (INSTANCE == null) {
            synchronized (BaseAction.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BaseAction();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 发送基础请求
     * <p>
     * 基础请求地址
     */
    public void SendBaseAction(final Context context, final OnGetBaseAction lis) {

        Base64Prarams params = new Base64Prarams();
        params.put("sys_type", SYS_TYPE);
        params.put("sys_version", SystemUtil.getSystemVersion());
        params.put("device_type", DEVICE_TYPE);
        params.put("brand", SystemUtil.getPhoneBrand());
        params.put("model", SystemUtil.getPhoneModel());
        params.put("u_id", BaseApplication.INSTANCE.getUserId());
        BaseHttpUtil.post(BaseApplication.BaseUrl, params, new HttpResponseHandler(BaseApplication.INSTANCE, "网络不给力") {

            @Override
            public void onResponesefinish() {
                if (lis != null) {
                    lis.onFinish();
                }
            }

            @Override
            public void onResponeseSucess(int code, BaseJsonEntity baseJson) {
                BaseApplication.MainJsonObjMap = FastJsonUtils.getJsonMap(baseJson.getObj());
                BaseApplication.RequestMap = FastJsonUtils.getJsonMap(BaseApplication.MainJsonObjMap.get(MAIN_JSON_API));
//                if (BaseApplication.INSTANCE.openCompelExit()) {
//                    String exit = BaseApplication.MainJsonObjMap.get(IS_CLOSE);
//                    if (exit != null && exit.equals(TRUE)) {
//                        SystemUtil.exitApp();
//                    }
//                }

//						String update = BaseApplication.MainJsonObjMap.get(IS_UPDATE);
//						if(update!=null&&update.equals(TRUE)){
//							if(BaseApplication.INSTANCE.openCompelUpdate()){
//								Log.i("zlx", "服务器要求强制更新程序");
//								SystemUtil.updateApp(context,true,true);
//							}
//
//						}else{//自动检查更新
//							if(BaseApplication.INSTANCE.openUpdateDialog()){
//								SystemUtil.updateApp(context,false,true);
//							}
//						}

//                if (BaseApplication.INSTANCE.openAutoDownWelocme()) {//自动更新开机图片
//                    //保存首选项
//                    BaseApplication.BASE_SHAREPREFERENCE.saveIsShowWelcome(BaseApplication.MainJsonObjMap.get(is_have_img));
//                    long begin = 0l;
//                    long end = 0l;
//                    try {
//                        begin = Long.parseLong(BaseApplication.MainJsonObjMap.get(img_begintime));
//                        end = Long.parseLong(BaseApplication.MainJsonObjMap.get(img_endtime));
//                    } catch (Exception e) {
//                    }
//                    BaseApplication.BASE_SHAREPREFERENCE.saveWelcomeBeginTime(begin);
//                    BaseApplication.BASE_SHAREPREFERENCE.saveWelcomeEndTime(end);
//                    String type = BaseApplication.MainJsonObjMap.get(img_click_type);//欢迎页面图片类型
//                    BaseApplication.BASE_SHAREPREFERENCE.saveWelcomeType(type);
//
//                    String url = BaseApplication.MainJsonObjMap.get(face_img_url);
//                    WELCOME_NATIVE_PATH = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath() + File.separator + getFileName(url);
//                    BaseApplication.BASE_SHAREPREFERENCE.saveWelcomePath("file://" + WELCOME_NATIVE_PATH);
//
//                    String version = BaseApplication.MainJsonObjMap.get(welcome_img_ver);
//                    long thisTime = new Date().getTime();
//
//                    boolean timeOut = (begin < thisTime) && (thisTime < end);//有效版本
//                    //	Log.d("zlx", "timeOut="+timeOut+"+++type=="+type+"+++version="+version+"||"+BaseApplication.BASE_SHAREPREFERENCE.readWelcomeVer());
//                    if (timeOut && type.equals("1") && !BaseApplication.BASE_SHAREPREFERENCE.readWelcomeVer().equals(version)) {//需要显示图片，并且本地无图片，下载
//                        downWelcomeImg(url, version);
//                    }
//                }

                if (lis != null) {
                    lis.onSuccess();
                }


            }

            @Override
            public void onResponeseFail(Throwable reason, String e) {
                if (lis != null) {
                    lis.onFail();
                }

            }

        });


    }

    /**
     * 获取文件名
     *
     * @param filePath
     * @return
     */
    private String getFileName(String filePath) {
        if (filePath == null || filePath.equals("")) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * 下载欢迎图片
     *
     * @param url
     */
    private void downWelcomeImg(String url, final String version) {
        HttpUtils http = new HttpUtils();
        http.download(url, WELCOME_NATIVE_PATH, true, true, new RequestCallBack<File>() {

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                Log.d("zlx", "封面图片下载成功：" + responseInfo.result.getPath());
                BaseApplication.BASE_SHAREPREFERENCE.saveWelcomeVer(version);
                BaseApplication.BASE_SHAREPREFERENCE.saveSkipUrl(BaseApplication.MainJsonObjMap.get(img_click_url));
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                BaseApplication.BASE_SHAREPREFERENCE.saveWelcomeType("0");//显示本地图片
                Log.d("zlx", "封面图片下载失败：" + arg1);
            }
        });
    }

    /**
     * 基础数据预加载
     */
    public void preload(Context context) {
        getRequestUrl(context, PRELOAD_KEY, null);
    }

    /**
     * 从基础数据接口中获取访问的url
     *
     * @param context
     * @param urlKey
     * @param lis
     */
    public void getRequestUrl(Context context, final String urlKey, final OnGetRequestUrl lis) {
        getRequestUrl(context, urlKey, DataFormType.HTTP, null, lis);
    }

    /**
     * 从基础数据接口中获取访问的url
     */
    public void getRequestUrl(Context context, final String urlKey, DataFormType from, HttpResponseHandler httpLis, final OnGetRequestUrl lis) {
        getRequestUrl(context, urlKey, urlKey, from, httpLis, lis);
    }

    /**
     * 从基础数据接口中获取访问的url
     *
     * @param urlKey
     * @param lis
     */
    public void getRequestUrl(Context context, final String urlKey, String cacheKey, DataFormType from, HttpResponseHandler httpLis, final OnGetRequestUrl lis) {
        switch (from) {
            case CACHE:
                if (httpLis instanceof OnGetCacheLis) {
                    CacheManager.getCache(context, cacheKey, (OnGetCacheLis) httpLis);
                } else if (httpLis instanceof OnGetPageCacheLis) {
                    int page = ((OnGetPageCacheLis) httpLis).getPage();
                    if (page == 1) {
                        CacheManager.getCache(context, cacheKey, (OnGetPageCacheLis) httpLis);
                    } else {
                        ((OnGetPageCacheLis) httpLis).onGetCacheFail(false);
                    }
                }
                break;
            case HTTP:
                if (httpLis != null) {
                    if (httpLis instanceof OnGetCacheLis) {//单接口缓存
                        httpLis.openCache(cacheKey);
                    } else if (httpLis instanceof OnGetPageCacheLis) {//分页缓存
                        int page = ((OnGetPageCacheLis) httpLis).getPage();
                        httpLis.openPageCache(cacheKey, page);

                    }
                }

                Map<String, String> RequestMap = BaseApplication.RequestMap;
                if (RequestMap == null || RequestMap.size() == 0) {
                    if (IS_ORDER) {
                        synchronized (baseQueue) {
                            QueueEntity ent = new QueueEntity();
                            ent.setKey(urlKey);
                            ent.setContext(context);
                            ent.setListener(lis);
                            baseQueue.offer(ent);
                            if (baseQueue.size() == 1) {
                                getUrl(baseQueue.peek());
                            }
                        }
                    } else {
                        BaseAction.getInstance().SendBaseAction(context, new OnGetBaseAction() {
                            @Override
                            public void onSuccess() {
                                if (lis != null) {
                                    lis.onSuccess(BaseApplication.RequestMap.get(urlKey));
                                }
                            }

                            @Override
                            public void onFail() {
                                if (lis != null) {
                                    lis.onFail(new Throwable("获取url访问地址失败"), "无网络连接");
                                }
                            }

                            @Override
                            public void onFinish() {
                                if (lis != null) {
                                    lis.onFinish();
                                }

                            }

                        });
                    }

                } else {
                    if (lis != null && urlKey != null) {
                        lis.onSuccess(RequestMap.get(urlKey));
                        lis.onFinish();
                    }

                }


                break;
            default:
                break;
        }


    }

    /**
     * 获取请求地址url
     *
     * @param urlKey
     * 请求地址键
     * @param lis
     * 获取后执行
     */
    private Queue<QueueEntity> baseQueue = new LinkedList<QueueEntity>();
    private static final int OK = 1;

    private Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case OK:
                    synchronized (baseQueue) {
                        baseQueue.poll();
                        if (baseQueue.size() > 0) {
                            getUrl(baseQueue.peek());
                        }
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 从服务器获取访问url
     *
     * @param queue
     */
    private void getUrl(final QueueEntity queue) {
        final OnGetRequestUrl lis = queue.getListener();
        Map<String, String> RequestMap = BaseApplication.RequestMap;
        if (RequestMap == null || RequestMap.size() == 0) {
            BaseAction.getInstance().SendBaseAction(queue.getContext(), new OnGetBaseAction() {
                @Override
                public void onSuccess() {
                    if (queue.getKey() != null && lis != null) {
                        lis.onSuccess(BaseApplication.RequestMap.get(queue.getKey()));
                    }
                }

                @Override
                public void onFail() {

                    if (lis != null) {
                        lis.onFail(new Throwable("获取url访问地址失败"), "无网络连接");
                    }
                }

                @Override
                public void onFinish() {

                    if (lis != null) {
                        lis.onFinish();
                    }
                    Message message = new Message();
                    message.what = OK;
                    myHandler.sendMessage(message);
                }

            });
        } else {
            Message message = new Message();
            message.what = OK;
            myHandler.sendMessage(message);
            if (lis != null && queue.getKey() != null) {
                lis.onSuccess(RequestMap.get(queue.getKey()));
                lis.onFinish();
            }

        }

    }
    /**
     * 取消请求
     * @param context
     */
//	public void cancleRequest(Context context){
//		AsyncHttpUtil.cancelRequests(context, true);
//	}

    /**
     * 获取基础请求后执行接口
     */
    public interface OnGetBaseAction {
        public void onSuccess();

        public void onFail();

        public void onFinish();
    }


}
