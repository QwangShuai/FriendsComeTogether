package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/9.
 */

public class UserActiveListModel {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"pfID":"56","pftitle":"登峨眉山！"}]
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
         * pfID : 56
         * pftitle : 登峨眉山！
         */

        private String pfID;
        private String pftitle;

        public String getPfID() {
            return pfID;
        }

        public void setPfID(String pfID) {
            this.pfID = pfID;
        }

        public String getPftitle() {
            return pftitle;
        }

        public void setPftitle(String pftitle) {
            this.pftitle = pftitle;
        }
    }
}
