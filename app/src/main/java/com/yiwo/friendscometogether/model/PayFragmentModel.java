package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */

public class PayFragmentModel {

    /**
     * code : 200
     * message : 获取成功
     * obj : [{"oID":"53","title":"登峨眉山！","info":"登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！","pfgotime":"1532930685","pfendtime":"1533881090","join_num":"1","pf_pic":"http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg","opaymoney":"1.00","opaytype":"1","opayout":"0","time_info":"2018.07.30-2018.08.10","status":"待支付"},{"oID":"52","title":"登峨眉山！","info":"登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！","pfgotime":"1532930685","pfendtime":"1533881090","join_num":"1","pf_pic":"http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg","opaymoney":"1.00","opaytype":"1","opayout":"0","time_info":"2018.07.30-2018.08.10","status":"待支付"},{"oID":"51","title":"登峨眉山！","info":"登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！","pfgotime":"1532930685","pfendtime":"1533881090","join_num":"1","pf_pic":"http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg","opaymoney":"1.00","opaytype":"1","opayout":"0","time_info":"2018.07.30-2018.08.10","status":"待支付"},{"oID":"50","title":"登峨眉山！","info":"登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！","pfgotime":"1532930685","pfendtime":"1533881090","join_num":"1","pf_pic":"http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg","opaymoney":"1.00","opaytype":"1","opayout":"0","time_info":"2018.07.30-2018.08.10","status":"待支付"}]
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
         * oID : 53
         * title : 登峨眉山！
         * info : 登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！
         * pfgotime : 1532930685
         * pfendtime : 1533881090
         * join_num : 1
         * pf_pic : http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg
         * opaymoney : 1.00
         * opaytype : 1
         * opayout : 0
         * time_info : 2018.07.30-2018.08.10
         * status : 待支付
         */

        private String oID;
        private String title;
        private String info;
        private String pfgotime;
        private String pfendtime;
        private String join_num;
        private String pf_pic;
        private String opaymoney;
        private String opaytype;
        private String opayout;
        private String time_info;
        private String status;

        public String getOID() {
            return oID;
        }

        public void setOID(String oID) {
            this.oID = oID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPfgotime() {
            return pfgotime;
        }

        public void setPfgotime(String pfgotime) {
            this.pfgotime = pfgotime;
        }

        public String getPfendtime() {
            return pfendtime;
        }

        public void setPfendtime(String pfendtime) {
            this.pfendtime = pfendtime;
        }

        public String getJoin_num() {
            return join_num;
        }

        public void setJoin_num(String join_num) {
            this.join_num = join_num;
        }

        public String getPf_pic() {
            return pf_pic;
        }

        public void setPf_pic(String pf_pic) {
            this.pf_pic = pf_pic;
        }

        public String getOpaymoney() {
            return opaymoney;
        }

        public void setOpaymoney(String opaymoney) {
            this.opaymoney = opaymoney;
        }

        public String getOpaytype() {
            return opaytype;
        }

        public void setOpaytype(String opaytype) {
            this.opaytype = opaytype;
        }

        public String getOpayout() {
            return opayout;
        }

        public void setOpayout(String opayout) {
            this.opayout = opayout;
        }

        public String getTime_info() {
            return time_info;
        }

        public void setTime_info(String time_info) {
            this.time_info = time_info;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
