package com.yiwo.friendscometogether.model;

/**
 * Created by Administrator on 2018/8/10.
 */

public class ModifyIntercalationModel {

    /**
     * code : 200
     * message : 获取成功
     * obj : {"fftitle":null,"ffcontect":null,"ffID":null}
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
        /**
         * fftitle : null
         * ffcontect : null
         * ffID : null
         */

        private String fftitle;
        private String ffcontect;
        private String ffID;

        public String getFftitle() {
            return fftitle;
        }

        public void setFftitle(String fftitle) {
            this.fftitle = fftitle;
        }

        public String getFfcontect() {
            return ffcontect;
        }

        public void setFfcontect(String ffcontect) {
            this.ffcontect = ffcontect;
        }

        public String getFfID() {
            return ffID;
        }

        public void setFfID(String ffID) {
            this.ffID = ffID;
        }
    }
}
