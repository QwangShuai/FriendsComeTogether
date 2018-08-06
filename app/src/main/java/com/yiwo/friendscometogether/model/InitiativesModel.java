package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/6.
 */

public class InitiativesModel {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"pfexamine":"0","pfID":"107","pfpic":"http://47.92.136.19/uploads/xingcheng/20180806/20180806/9d8b27edae5bb5077423abe82b881c0e.jpeg","pftitle":"明年","pfgotime":"2018-08-06","pfendtime":"2018-08-06","pfspend":"222.00","pflook":"0","focusOn":"0","join_num":"0"}]
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
         * pfexamine : 0
         * pfID : 107
         * pfpic : http://47.92.136.19/uploads/xingcheng/20180806/20180806/9d8b27edae5bb5077423abe82b881c0e.jpeg
         * pftitle : 明年
         * pfgotime : 2018-08-06
         * pfendtime : 2018-08-06
         * pfspend : 222.00
         * pflook : 0
         * focusOn : 0
         * join_num : 0
         */

        private String pfexamine;
        private String pfID;
        private String pfpic;
        private String pftitle;
        private String pfgotime;
        private String pfendtime;
        private String pfspend;
        private String pflook;
        private String focusOn;
        private String join_num;

        public String getPfexamine() {
            return pfexamine;
        }

        public void setPfexamine(String pfexamine) {
            this.pfexamine = pfexamine;
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

        public String getPftitle() {
            return pftitle;
        }

        public void setPftitle(String pftitle) {
            this.pftitle = pftitle;
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

        public String getPfspend() {
            return pfspend;
        }

        public void setPfspend(String pfspend) {
            this.pfspend = pfspend;
        }

        public String getPflook() {
            return pflook;
        }

        public void setPflook(String pflook) {
            this.pflook = pflook;
        }

        public String getFocusOn() {
            return focusOn;
        }

        public void setFocusOn(String focusOn) {
            this.focusOn = focusOn;
        }

        public String getJoin_num() {
            return join_num;
        }

        public void setJoin_num(String join_num) {
            this.join_num = join_num;
        }
    }
}
