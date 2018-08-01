package com.yiwo.friendscometogether.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/8/1.
 */

public class Paymodel {

    /**
     * code : 200
     * message : 填加成功!
     * obj : {"appid":"wx1c9e70bb94b3fd85","mch_id":"1286274601","prepayid":"wx0111113806191404c1585ed41433636648","package":"Sign=WXPay","noncestr":"rylbdiyil6zy0a2jqor2948q76r3vieu","timestamp":1533093098,"sign":"C2420BECF3626B83B59BD2EF3BBE6E71"}
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
         * appid : wx1c9e70bb94b3fd85
         * mch_id : 1286274601
         * prepayid : wx0111113806191404c1585ed41433636648
         * package : Sign=WXPay
         * noncestr : rylbdiyil6zy0a2jqor2948q76r3vieu
         * timestamp : 1533093098
         * sign : C2420BECF3626B83B59BD2EF3BBE6E71
         */

        private String appid;
        private String mch_id;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
