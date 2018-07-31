package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/20.
 */

public class HomeHotFriendsRememberModel {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"info":[{"userphone":"15244615472","fmhot":"0","fmID":"12","upicurl":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","fmtitle":"不扔给","fmcontent":"2访问方法","fmpic":"http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg","fmtime":"","fmlook":"0","fmcomment":"0","username":"zp","type":"1"},{"userphone":"15244615472","fmhot":"0","fmID":"11","upicurl":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","fmtitle":"2方法","fmcontent":"2沃尔沃二","fmpic":"http://47.92.136.19/http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg","fmtime":"","fmlook":"0","fmcomment":"0","username":"zp","type":"2"},{"userphone":"15244615472","fmhot":"0","fmID":"10","upicurl":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","fmtitle":"违法违法","fmcontent":"而为范围","fmpic":"http://47.92.136.19/uploads/activity/20180719/20180719/11e73ef1b5e4809649c225afe7eb2501.jpg","fmtime":"","fmlook":"0","fmcomment":"0","username":"zp","type":"1"}],"video":[{"vname":"看视频上芒果TV","vurl":"www.aiqiyi.com","img":"http://47.92.136.19/uploads/videos/20180723/20180723/518fb806764967b97ee7f59a7d2d2795.jpg"},{"vname":"看视频来爱奇艺","vurl":"www.aiqiyi.com","img":"http://47.92.136.19/uploads/videos/20180723/20180723/603dc9e676722de754ed9f5dcf3029ac.jpg"},{"vname":"看视频来优酷","vurl":"www.baidu.com","img":"http://47.92.136.19/uploads/videos/20180723/20180723/ec202c25a5914855399f018f9d50752c.jpg"}]}
     */

    private int code;
    private String message;
    private ObjBean obj;

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

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        private List<InfoBean> info;
        private List<VideoBean> video;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public static class InfoBean {
            /**
             * userphone : 15244615472
             * fmhot : 0
             * fmID : 12
             * upicurl : http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg
             * fmtitle : 不扔给
             * fmcontent : 2访问方法
             * fmpic : http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg
             * fmtime :
             * fmlook : 0
             * fmcomment : 0
             * username : zp
             * type : 1
             */

            private String userphone;
            private String fmhot;
            private String fmID;
            private String upicurl;
            private String fmtitle;
            private String fmcontent;
            private String fmpic;
            private String fmtime;
            private String fmlook;
            private String fmcomment;
            private String username;
            private String type;

            public String getUserphone() {
                return userphone;
            }

            public void setUserphone(String userphone) {
                this.userphone = userphone;
            }

            public String getFmhot() {
                return fmhot;
            }

            public void setFmhot(String fmhot) {
                this.fmhot = fmhot;
            }

            public String getFmID() {
                return fmID;
            }

            public void setFmID(String fmID) {
                this.fmID = fmID;
            }

            public String getUpicurl() {
                return upicurl;
            }

            public void setUpicurl(String upicurl) {
                this.upicurl = upicurl;
            }

            public String getFmtitle() {
                return fmtitle;
            }

            public void setFmtitle(String fmtitle) {
                this.fmtitle = fmtitle;
            }

            public String getFmcontent() {
                return fmcontent;
            }

            public void setFmcontent(String fmcontent) {
                this.fmcontent = fmcontent;
            }

            public String getFmpic() {
                return fmpic;
            }

            public void setFmpic(String fmpic) {
                this.fmpic = fmpic;
            }

            public String getFmtime() {
                return fmtime;
            }

            public void setFmtime(String fmtime) {
                this.fmtime = fmtime;
            }

            public String getFmlook() {
                return fmlook;
            }

            public void setFmlook(String fmlook) {
                this.fmlook = fmlook;
            }

            public String getFmcomment() {
                return fmcomment;
            }

            public void setFmcomment(String fmcomment) {
                this.fmcomment = fmcomment;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class VideoBean {
            /**
             * vname : 看视频上芒果TV
             * vurl : www.aiqiyi.com
             * img : http://47.92.136.19/uploads/videos/20180723/20180723/518fb806764967b97ee7f59a7d2d2795.jpg
             */

            private String vname;
            private String vurl;
            private String img;

            public String getVname() {
                return vname;
            }

            public void setVname(String vname) {
                this.vname = vname;
            }

            public String getVurl() {
                return vurl;
            }

            public void setVurl(String vurl) {
                this.vurl = vurl;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
