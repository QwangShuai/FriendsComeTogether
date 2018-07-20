package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/20.
 */

public class HomeHotFriendsRememberModel {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"type":3,"userphone":"","fmhot":"","fmID":"","fmtitle":"","fmcontent":"","upicurl":"","fmpic":"","fmtime":"","fmlook":"","fmcomment":"","username":"","video":[{"vname":"哈哈哈","vurl":"www.youku.com","img":""}]}]
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
         * type : 3
         * userphone :
         * fmhot :
         * fmID :
         * fmtitle :
         * fmcontent :
         * upicurl :
         * fmpic :
         * fmtime :
         * fmlook :
         * fmcomment :
         * username :
         * video : [{"vname":"哈哈哈","vurl":"www.youku.com","img":""}]
         */

        private int type;
        private String userphone;
        private String fmhot;
        private String fmID;
        private String fmtitle;
        private String fmcontent;
        private String upicurl;
        private String fmpic;
        private String fmtime;
        private String fmlook;
        private String fmcomment;
        private String username;
        private List<VideoBean> video;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

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

        public String getUpicurl() {
            return upicurl;
        }

        public void setUpicurl(String upicurl) {
            this.upicurl = upicurl;
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

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public static class VideoBean {
            /**
             * vname : 哈哈哈
             * vurl : www.youku.com
             * img :
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
