package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/10.
 */

public class ActiveCommentModel {

    /**
     * code : 200
     * message : 操作成功!
     * obj : [{"pfID":"54","pic":"http://47.92.136.19/uploads/activity/20180719/20180719/11e73ef1b5e4809649c225afe7eb2501.jpg","title":"泰山七日游","time":"2018.07.27-2018.09.08","list":[{"userID":"1","buserID":"2","username":"zp","busername":"heihei","content":"哈哈哈哈"},{"userID":"2","buserID":"1","username":"heihei","busername":"zp","content":"呵呵呵呵"}]}]
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
         * pfID : 54
         * pic : http://47.92.136.19/uploads/activity/20180719/20180719/11e73ef1b5e4809649c225afe7eb2501.jpg
         * title : 泰山七日游
         * time : 2018.07.27-2018.09.08
         * list : [{"userID":"1","buserID":"2","username":"zp","busername":"heihei","content":"哈哈哈哈"},{"userID":"2","buserID":"1","username":"heihei","busername":"zp","content":"呵呵呵呵"}]
         */

        private String pfID;
        private String pic;
        private String title;
        private String time;
        private List<ListBean> list;

        public String getPfID() {
            return pfID;
        }

        public void setPfID(String pfID) {
            this.pfID = pfID;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * userID : 1
             * buserID : 2
             * username : zp
             * busername : heihei
             * content : 哈哈哈哈
             */

            private String userID;
            private String buserID;
            private String username;
            private String busername;
            private String content;

            public String getUserID() {
                return userID;
            }

            public void setUserID(String userID) {
                this.userID = userID;
            }

            public String getBuserID() {
                return buserID;
            }

            public void setBuserID(String buserID) {
                this.buserID = buserID;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getBusername() {
                return busername;
            }

            public void setBusername(String busername) {
                this.busername = busername;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
