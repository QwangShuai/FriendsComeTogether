package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/2.
 */

public class HomeTogetherModel {

    /**
     * code : 200
     * message : 操作成功!
     * obj : [{"captain":"0","pftitle":"飞","pfcontent":"123123","pfID":"8","pfpic":"http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","upicurl":""},{"captain":"1","pftitle":"飘","pfcontent":"123123","pfID":"1","pfpic":"http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","upicurl":""}]
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
         * captain : 0
         * pftitle : 飞
         * pfcontent : 123123
         * pfID : 8
         * pfpic : http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg
         * upicurl :
         */

        private String captain;
        private String pftitle;
        private String pfcontent;
        private String pfID;
        private String pfpic;
        private String upicurl;

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
