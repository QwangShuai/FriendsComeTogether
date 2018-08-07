package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/7.
 */

public class JoinActivemodel {

    /**
     * code : 200
     * message : 操作成功!
     * obj : [{"captain":"ylyy15244615472","ujID":"34","pfID":"56","pfpic":"http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg","pftitle":"登峨眉山！","pfgotime":"2018-07-30","pfendtime":"2018-08-01","pfspend":"120.00","pflook":"13","focusOn":"12","join_num":"9"},{"captain":"ylyy15244615472","ujID":"33","pfID":"55","pfpic":"http://47.92.136.19/uploads/xingcheng/20180723/20180723/6c2644a18caacdf5e463a600c11a6916.jpg","pftitle":"洱海10天游","pfgotime":"2018-09-01","pfendtime":"2018-09-04","pfspend":"123.00","pflook":"123","focusOn":"123","join_num":"1"}]
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
         * captain : ylyy15244615472
         * ujID : 34
         * pfID : 56
         * pfpic : http://47.92.136.19/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg
         * pftitle : 登峨眉山！
         * pfgotime : 2018-07-30
         * pfendtime : 2018-08-01
         * pfspend : 120.00
         * pflook : 13
         * focusOn : 12
         * join_num : 9
         */

        private String captain;
        private String ujID;
        private String pfID;
        private String pfpic;
        private String pftitle;
        private String pfgotime;
        private String pfendtime;
        private String pfspend;
        private String pflook;
        private String focusOn;
        private String join_num;

        public String getCaptain() {
            return captain;
        }

        public void setCaptain(String captain) {
            this.captain = captain;
        }

        public String getUjID() {
            return ujID;
        }

        public void setUjID(String ujID) {
            this.ujID = ujID;
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
