package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/20.
 */

public class HomeHotFriendsRememberModel {

    /**
     * code : 200
     * message : 获取成功!
     * obj : {"info":[{"userID":"7","userphone":"15244615473","upicurl":"http://47.92.136.19/uploads/header/2018/08/14/e046ce4cc65dee1e7fb83a564981889a15342152253.jpg","fmhot":"1","fmID":"85","fmtitle":"泰山七日游","fmcontent":"那里好好玩啊，下次有谁一起去吗？请告诉我那里好好玩啊，下次有谁一起去吗？请告诉我那里好好玩啊，下次有谁一起去吗？请告诉我那里好好玩啊，下次有谁一起去吗？请告诉我那里好好玩啊，下次有谁一起去吗？请告诉我","fmpic":"http://47.92.136.19/uploads/article/20180810/20180810/a5fba7bdbdef8beb653129c024d56cb8.jpeg","fmtime":"2018-08-10","fmlook":"178","fmcomment":"0","username":"吃了","follow":"0","type":"1"}],"video":[{"vname":"看视频上芒果TV","vurl":"www.aiqiyi.com","img":"http://47.92.136.19/uploads/videos/20180723/20180723/518fb806764967b97ee7f59a7d2d2795.jpg"},{"vname":"看视频来爱奇艺","vurl":"www.aiqiyi.com","img":"http://47.92.136.19/uploads/videos/20180723/20180723/603dc9e676722de754ed9f5dcf3029ac.jpg"},{"vname":"看视频来优酷","vurl":"www.baidu.com","img":"http://47.92.136.19/uploads/videos/20180723/20180723/ec202c25a5914855399f018f9d50752c.jpg"}]}
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
             * userID : 7
             * userphone : 15244615473
             * upicurl : http://47.92.136.19/uploads/header/2018/08/14/e046ce4cc65dee1e7fb83a564981889a15342152253.jpg
             * fmhot : 1
             * fmID : 85
             * fmtitle : 泰山七日游
             * fmcontent : 那里好好玩啊，下次有谁一起去吗？请告诉我那里好好玩啊，下次有谁一起去吗？请告诉我那里好好玩啊，下次有谁一起去吗？请告诉我那里好好玩啊，下次有谁一起去吗？请告诉我那里好好玩啊，下次有谁一起去吗？请告诉我
             * fmpic : http://47.92.136.19/uploads/article/20180810/20180810/a5fba7bdbdef8beb653129c024d56cb8.jpeg
             * fmtime : 2018-08-10
             * fmlook : 178
             * fmcomment : 0
             * username : 吃了
             * follow : 0
             * type : 1
             */

            private String usergrade;
            private String userID;
            private String userphone;
            private String upicurl;
            private String fmhot;
            private String fmID;
            private String fmtitle;
            private String fmcontent;
            private String fmpic;
            private String fmtime;
            private String fmlook;
            private String fmcomment;
            private String username;
            private String follow;
            private String type;

            public String getUsergrade() {
                return usergrade;
            }

            public void setUsergrade(String usergrade) {
                this.usergrade = usergrade;
            }

            public String getUserID() {
                return userID;
            }

            public void setUserID(String userID) {
                this.userID = userID;
            }

            public String getUserphone() {
                return userphone;
            }

            public void setUserphone(String userphone) {
                this.userphone = userphone;
            }

            public String getUpicurl() {
                return upicurl;
            }

            public void setUpicurl(String upicurl) {
                this.upicurl = upicurl;
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

            public String getFollow() {
                return follow;
            }

            public void setFollow(String follow) {
                this.follow = follow;
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

            private String vID;
            private String vname;
            private String vurl;
            private String img;

            public String getVID() {
                return vID;
            }

            public void setVID(String vID) {
                this.vID = vID;
            }

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
