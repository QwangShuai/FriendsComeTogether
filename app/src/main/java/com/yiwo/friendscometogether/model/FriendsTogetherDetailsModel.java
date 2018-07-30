package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/19.
 */

public class FriendsTogetherDetailsModel {

    /**
     * code : 200
     * message : 操作成功!
     * obj : {"captain":"","capttain_name":"","capttain_pic":"","level":"0","if_sign":"0","title":"泰山三日游","look":"0","pffavorite":"0","begin_time":"1970-01-01","end_time":"1970-01-01","city":"北京","price":"123.00","woman":"0","man":"0","person_num":"0","have_num":"1","show_pic":"","text_info":"12额2","info_list":[{"title":"新添加的哈哈","content":"<p>来吧 新添加的 &nbsp;<strong><em><span style=\"color: rgb(112, 48, 160);\">炒鸡好玩<\/span><\/em><\/strong><\/p>","pfpID":"275","image_list":[{"pic":"","text_info":null},{"pic":"","text_info":null}]},{"title":"探讨探讨","content":"<p>萨达阿萨德<\/p>","pfpID":"278","image_list":[{"pic":"","text_info":null}]},{"title":"去旅游啦","content":"<p><span style=\"color: rgb(0, 176, 240);\">去旅游啦去旅游啦去旅游啦啊<\/span><\/p>","pfpID":"162","image_list":[{"pic":"","text_info":"去旅游啦去旅游啦去旅游啦啊"},{"pic":"","text_info":"去旅游啦去旅游啦去旅游啦啊a"}]},{"title":"去哪网","content":"去哪网去哪网去哪网","pfpID":"203","image_list":[{"pic":"","text_info":null},{"pic":"","text_info":null},{"pic":"","text_info":null}]},{"title":"驱蚊器","content":"<p>生栋覆屋<\/p>","pfpID":"207","image_list":[{"pic":"","text_info":null}]},{"title":"给对方嘎辅导费","content":"<p><span style=\"color: rgb(0, 112, 192);\"><em><strong>胜多负少的<\/strong><\/em><\/span><\/p>","pfpID":"209","image_list":[{"pic":"","text_info":null}]}],"user_list":[{"username":"zp","userpic":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","num":"1"}]}
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
         * captain :
         * capttain_name :
         * capttain_pic :
         * level : 0
         * if_sign : 0
         * title : 泰山三日游
         * look : 0
         * pffavorite : 0
         * begin_time : 1970-01-01
         * end_time : 1970-01-01
         * city : 北京
         * price : 123.00
         * woman : 0
         * man : 0
         * person_num : 0
         * have_num : 1
         * show_pic :
         * text_info : 12额2
         * info_list : [{"title":"新添加的哈哈","content":"<p>来吧 新添加的 &nbsp;<strong><em><span style=\"color: rgb(112, 48, 160);\">炒鸡好玩<\/span><\/em><\/strong><\/p>","pfpID":"275","image_list":[{"pic":"","text_info":null},{"pic":"","text_info":null}]},{"title":"探讨探讨","content":"<p>萨达阿萨德<\/p>","pfpID":"278","image_list":[{"pic":"","text_info":null}]},{"title":"去旅游啦","content":"<p><span style=\"color: rgb(0, 176, 240);\">去旅游啦去旅游啦去旅游啦啊<\/span><\/p>","pfpID":"162","image_list":[{"pic":"","text_info":"去旅游啦去旅游啦去旅游啦啊"},{"pic":"","text_info":"去旅游啦去旅游啦去旅游啦啊a"}]},{"title":"去哪网","content":"去哪网去哪网去哪网","pfpID":"203","image_list":[{"pic":"","text_info":null},{"pic":"","text_info":null},{"pic":"","text_info":null}]},{"title":"驱蚊器","content":"<p>生栋覆屋<\/p>","pfpID":"207","image_list":[{"pic":"","text_info":null}]},{"title":"给对方嘎辅导费","content":"<p><span style=\"color: rgb(0, 112, 192);\"><em><strong>胜多负少的<\/strong><\/em><\/span><\/p>","pfpID":"209","image_list":[{"pic":"","text_info":null}]}]
         * user_list : [{"username":"zp","userpic":"http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg","num":"1"}]
         */

        private String captain;
        private String capttain_name;
        private String capttain_pic;
        private String level;
        private String if_sign;
        private String title;
        private String look;
        private String pffavorite;
        private String begin_time;
        private String end_time;
        private String city;
        private String price;
        private String woman;
        private String man;
        private String person_num;
        private String have_num;
        private String show_pic;
        private String text_info;
        private String if_pay;
        private String pfID;
        private String attention;
        private List<InfoListBean> info_list;
        private List<UserListBean> user_list;

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public String getPfID() {
            return pfID;
        }

        public void setPfID(String pfID) {
            this.pfID = pfID;
        }

        public String getIf_pay() {
            return if_pay;
        }

        public void setIf_pay(String if_pay) {
            this.if_pay = if_pay;
        }

        public String getCaptain() {
            return captain;
        }

        public void setCaptain(String captain) {
            this.captain = captain;
        }

        public String getCapttain_name() {
            return capttain_name;
        }

        public void setCapttain_name(String capttain_name) {
            this.capttain_name = capttain_name;
        }

        public String getCapttain_pic() {
            return capttain_pic;
        }

        public void setCapttain_pic(String capttain_pic) {
            this.capttain_pic = capttain_pic;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getIf_sign() {
            return if_sign;
        }

        public void setIf_sign(String if_sign) {
            this.if_sign = if_sign;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLook() {
            return look;
        }

        public void setLook(String look) {
            this.look = look;
        }

        public String getPffavorite() {
            return pffavorite;
        }

        public void setPffavorite(String pffavorite) {
            this.pffavorite = pffavorite;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getWoman() {
            return woman;
        }

        public void setWoman(String woman) {
            this.woman = woman;
        }

        public String getMan() {
            return man;
        }

        public void setMan(String man) {
            this.man = man;
        }

        public String getPerson_num() {
            return person_num;
        }

        public void setPerson_num(String person_num) {
            this.person_num = person_num;
        }

        public String getHave_num() {
            return have_num;
        }

        public void setHave_num(String have_num) {
            this.have_num = have_num;
        }

        public String getShow_pic() {
            return show_pic;
        }

        public void setShow_pic(String show_pic) {
            this.show_pic = show_pic;
        }

        public String getText_info() {
            return text_info;
        }

        public void setText_info(String text_info) {
            this.text_info = text_info;
        }

        public List<InfoListBean> getInfo_list() {
            return info_list;
        }

        public void setInfo_list(List<InfoListBean> info_list) {
            this.info_list = info_list;
        }

        public List<UserListBean> getUser_list() {
            return user_list;
        }

        public void setUser_list(List<UserListBean> user_list) {
            this.user_list = user_list;
        }

        public static class InfoListBean {
            /**
             * title : 新添加的哈哈
             * content : <p>来吧 新添加的 &nbsp;<strong><em><span style="color: rgb(112, 48, 160);">炒鸡好玩</span></em></strong></p>
             * pfpID : 275
             * image_list : [{"pic":"","text_info":null},{"pic":"","text_info":null}]
             */

            private String title;
            private String content;
            private String pfpID;
            private List<ImageListBean> image_list;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPfpID() {
                return pfpID;
            }

            public void setPfpID(String pfpID) {
                this.pfpID = pfpID;
            }

            public List<ImageListBean> getImage_list() {
                return image_list;
            }

            public void setImage_list(List<ImageListBean> image_list) {
                this.image_list = image_list;
            }

            public static class ImageListBean {
                /**
                 * pic :
                 * text_info : null
                 */

                private String pic;
                private String text_info;

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getText_info() {
                    return text_info;
                }

                public void setText_info(String text_info) {
                    this.text_info = text_info;
                }
            }
        }

        public static class UserListBean {
            /**
             * username : zp
             * userpic : http://47.92.136.19/uploads/activity/20180529/20180529/a51b266af3785bd0bd0a1794dbf234cf.jpg
             * num : 1
             */

            private String username;
            private String userpic;
            private String num;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUserpic() {
                return userpic;
            }

            public void setUserpic(String userpic) {
                this.userpic = userpic;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
