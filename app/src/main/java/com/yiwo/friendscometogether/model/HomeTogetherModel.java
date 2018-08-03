package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/2.
 */

public class HomeTogetherModel {

    /**
     * code : 200
     * message : 操作成功!
     * obj : [{"sign":"0","username":"","usergrade":"0","captain":"0","pftitle":"哦嗖嗖嗖","pfcontent":"哦嗖嗖嗖","pfID":"104","pfpic":"http://localhost/www/uploads/xingcheng/20180802/20180802/d95ecae3fe60a4f5b9354d298b91490a.jpeg","upicurl":""}]
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
         * sign : 0
         * username :
         * usergrade : 0
         * captain : 0
         * pftitle : 哦嗖嗖嗖
         * pfcontent : 哦嗖嗖嗖
         * pfID : 104
         * pfpic : http://localhost/www/uploads/xingcheng/20180802/20180802/d95ecae3fe60a4f5b9354d298b91490a.jpeg
         * upicurl :
         */

        private String sign;
        private String username;
        private String usergrade;
        private String captain;
        private String pftitle;
        private String pfcontent;
        private String pfID;
        private String pfpic;
        private String upicurl;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsergrade() {
            return usergrade;
        }

        public void setUsergrade(String usergrade) {
            this.usergrade = usergrade;
        }

        public String getCaptain() {
            return captain;
        }

        public void setCaptain(String captain) {
            this.captain = captain;
        }

        public String getPftitle() {
            return pftitle;
        }

        public void setPftitle(String pftitle) {
            this.pftitle = pftitle;
        }

        public String getPfcontent() {
            return pfcontent;
        }

        public void setPfcontent(String pfcontent) {
            this.pfcontent = pfcontent;
        }

        public String getPfID() {
            return pfID;
        }

        public void setPfID(String pfID) {
            this.pfID = pfID;
        }

        public String getPfpic() {
            return pfpic;
        }

        public void setPfpic(String pfpic) {
            this.pfpic = pfpic;
        }

        public String getUpicurl() {
            return upicurl;
        }

        public void setUpicurl(String upicurl) {
            this.upicurl = upicurl;
        }
    }
}
