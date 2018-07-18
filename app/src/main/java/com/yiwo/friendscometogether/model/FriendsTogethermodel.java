package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 */

public class FriendsTogethermodel {

    /**
     * code : 200
     * message : 操作成功!
     * obj : [{"usergrade":"1","username":"小微","pfID":"8","captain":"0","pftitle":"飞","pfcontent":"123123","pfpic":"http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","upicurl":"","all_u_pic":[]},{"pfID":"1","captain":"1","pftitle":"飘","pfcontent":"123123","pfpeople":"10","have_num":"2","focusOn":"0","pfpic":"http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","upicurl":"http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","all_u_pic":["http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg"]}]
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
         * usergrade : 1
         * username : 小微
         * pfID : 8
         * captain : 0
         * pftitle : 飞
         * pfcontent : 123123
         * pfpic : http://localhost/www/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg
         * upicurl :
         * all_u_pic : []
         * pfpeople : 10
         * have_num : 2
         * focusOn : 0
         */

        private String usergrade;
        private String username;
        private String pfID;
        private String captain;
        private String pftitle;
        private String pfcontent;
        private String pfpic;
        private String upicurl;
        private String pfpeople;
        private String have_num;
        private String focusOn;
        private List<String> all_u_pic;

        public String getUsergrade() {
            return usergrade;
        }

        public void setUsergrade(String usergrade) {
            this.usergrade = usergrade;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPfID() {
            return pfID;
        }

        public void setPfID(String pfID) {
            this.pfID = pfID;
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

        public String getPfpeople() {
            return pfpeople;
        }

        public void setPfpeople(String pfpeople) {
            this.pfpeople = pfpeople;
        }

        public String getHave_num() {
            return have_num;
        }

        public void setHave_num(String have_num) {
            this.have_num = have_num;
        }

        public String getFocusOn() {
            return focusOn;
        }

        public void setFocusOn(String focusOn) {
            this.focusOn = focusOn;
        }

        public List<String> getAll_u_pic() {
            return all_u_pic;
        }

        public void setAll_u_pic(List<String> all_u_pic) {
            this.all_u_pic = all_u_pic;
        }
    }
}
