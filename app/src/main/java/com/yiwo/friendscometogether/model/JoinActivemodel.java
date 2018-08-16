package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/7.
 */

public class JoinActivemodel {

    /**
     * code : 200
     * message : 操作成功!
     * obj : [{"captain":"ylyy15244615473","ujID":"2","pfID":"2","pfpic":"http://47.92.136.19/uploads/xingcheng/20180816/20180816/3b70de05b4b083956ef12555ec24d1cc.jpeg","pftitle":"泰山30日游","pfgotime":"2018-08-02","pfendtime":"2018-09-29","pfspend":"1.00","pflook":"14","focusOn":"0","join_num":"1","wy_accid":"yy15244615473"}]
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
         * captain : ylyy15244615473
         * ujID : 2
         * pfID : 2
         * pfpic : http://47.92.136.19/uploads/xingcheng/20180816/20180816/3b70de05b4b083956ef12555ec24d1cc.jpeg
         * pftitle : 泰山30日游
         * pfgotime : 2018-08-02
         * pfendtime : 2018-09-29
         * pfspend : 1.00
         * pflook : 14
         * focusOn : 0
         * join_num : 1
         * wy_accid : yy15244615473
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
        private String wy_accid;

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

        public String getWy_accid() {
            return wy_accid;
        }

        public void setWy_accid(String wy_accid) {
            this.wy_accid = wy_accid;
        }
    }
}
