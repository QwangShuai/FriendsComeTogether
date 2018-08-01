package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/1.
 */

public class LookHistoryModel {

    /**
     * code : 200
     * message : 获取成功
     * obj : [{"id":"2","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:02:46"},{"id":"3","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:03:02"},{"id":"4","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:25:27"},{"id":"5","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:26:51"},{"id":"6","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:34:03"},{"id":"7","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:39:10"},{"id":"8","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:40:43"},{"id":"9","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:41:10"},{"id":"10","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:49:20"},{"id":"11","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:52:29"},{"id":"12","title":"飞","pic_url":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","look_time":"2018-07-10 14:56:22"},{"id":"18","title":"登峨眉山！","pic_url":"http://47.92.136.19/","look_time":"2018-07-24 16:50:21"},{"id":"19","title":"登峨眉山！","pic_url":"http://47.92.136.19/","look_time":"2018-07-24 16:50:31"},{"id":"20","title":"登峨眉山！","pic_url":"http://47.92.136.19/","look_time":"2018-07-24 17:01:05"},{"id":"21","title":"洱海10天游","pic_url":"http://47.92.136.19/","look_time":"2018-07-24 17:01:14"},{"id":"22","title":"登峨眉山！","pic_url":"http://47.92.136.19/","look_time":"2018-07-24 17:02:22"},{"id":"23","title":"登峨眉山！","pic_url":"http://47.92.136.19/","look_time":"2018-07-24 17:02:26"},{"id":"24","title":"登峨眉山！","pic_url":"http://47.92.136.19/","look_time":"2018-07-24 17:03:47"},{"id":"25","title":"登峨眉山！","pic_url":"http://47.92.136.19/","look_time":"2018-07-26 14:19:34"},{"id":"26","title":"登峨眉山！","pic_url":"http://47.92.136.19/","look_time":"2018-07-26 14:19:37"},{"id":"86","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 14:32:02"},{"id":"108","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:09:22"},{"id":"113","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:26:06"},{"id":"114","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:32:47"},{"id":"115","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:33:34"},{"id":"116","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:33:36"},{"id":"117","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:33:43"},{"id":"118","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:33:49"},{"id":"119","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:33:52"},{"id":"120","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:34:23"},{"id":"121","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:34:34"},{"id":"122","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 15:43:36"},{"id":"124","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 16:03:53"},{"id":"125","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 16:19:25"},{"id":"126","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 16:20:25"},{"id":"127","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 16:36:53"},{"id":"128","title":"登峨眉山！","pic_url":"http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg","look_time":"2018-07-31 16:44:41"},{"id":"129","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 16:48:56"},{"id":"130","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 16:49:47"},{"id":"131","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 16:51:12"},{"id":"132","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 16:56:00"},{"id":"133","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 17:09:07"},{"id":"134","title":"北京","pic_url":"http://47.92.136.19/uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","look_time":"2018-07-31 17:09:11"}]
     */

    private int code;
    private String message;
    private List<ObjBean> obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * id : 2
         * title : 飞
         * pic_url : http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg
         * look_time : 2018-07-10 14:02:46
         */

        private String id;
        private String title;
        private String pic_url;
        private String look_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getLook_time() {
            return look_time;
        }

        public void setLook_time(String look_time) {
            this.look_time = look_time;
        }
    }
}
