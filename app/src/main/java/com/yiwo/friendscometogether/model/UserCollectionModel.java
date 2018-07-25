package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/25.
 */

public class UserCollectionModel {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"fID":"1","ftitle":"泰山七日游","fpic":"uploads/activity/20180719/20180719/11e73ef1b5e4809649c225afe7eb2501.jpg","ftime":"123"},{"fID":"2","ftitle":"洱海10天游","fpic":"uploads/xingcheng/20180723/20180723/6c2644a18caacdf5e463a600c11a6916.jpg","ftime":"123123"}]
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
         * fID : 1
         * ftitle : 泰山七日游
         * fpic : uploads/activity/20180719/20180719/11e73ef1b5e4809649c225afe7eb2501.jpg
         * ftime : 123
         */

        private String fID;
        private String ftitle;
        private String fpic;
        private String ftime;

        public String getFID() {
            return fID;
        }

        public void setFID(String fID) {
            this.fID = fID;
        }

        public String getFtitle() {
            return ftitle;
        }

        public void setFtitle(String ftitle) {
            this.ftitle = ftitle;
        }

        public String getFpic() {
            return fpic;
        }

        public void setFpic(String fpic) {
            this.fpic = fpic;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }
    }
}
