package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/11.
 */

public class OtherInformationModel {

    /**
     * code : 200
     * message : 获取成功
     * obj : {"info":{"userpic":"http://47.92.136.19/uploads/header/2018/08/14/e046ce4cc65dee1e7fb83a564981889a15342152253.jpg","username":"吃了","wy_accid":"yy15244615473","wy_token":"c52f69e327c6c8e5cad8635de42662e7","usergrade":"0","userautograph":"恶魔","age":"25岁","address":"内蒙古","constellation":"摩羯座","userlike":"0","userbelike":"0","uid":"7","GiveCount":2},"ListPicNews":[{"fmID":"85","fmtitle":"泰山七日游","fmlook":"182","fmgood":"0","fmcomment":"0","fmfavorite":"5","fmpic":"http://47.92.136.19/uploads/article/20180810/20180810/a5fba7bdbdef8beb653129c024d56cb8.jpeg"}],"ListActiviy":[{"pfID":"102","pftitle":"几哈","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/0fa03a6c61ac405be84cbe2755400c10.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"104","pftitle":"哦嗖嗖嗖","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/d95ecae3fe60a4f5b9354d298b91490a.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"116","pftitle":"噜啦啦噜啦啦","pfpic":"http://47.92.136.19/uploads/xingcheng/20180810/20180810/19389386e012033588d35742d0849b64.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"119","pftitle":"测试","pfpic":"http://47.92.136.19/uploads/xingcheng/20180813/20180813/6f979b90e86d550e2e79657e6dd926d0.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"120","pftitle":"测试一下","pfpic":"http://47.92.136.19/uploads/xingcheng/20180813/20180813/3aa3f4376c5b7f623303f812f0b7b875.jpeg","pflook":"1","pfcomment":"0","pffavorite":"0"},{"pfID":"126","pftitle":"测试成功","pfpic":"http://47.92.136.19/uploads/xingcheng/20180813/20180813/61cde24c8f29c05fa072142ffc56fd3e.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"}]}
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
         * info : {"userpic":"http://47.92.136.19/uploads/header/2018/08/14/e046ce4cc65dee1e7fb83a564981889a15342152253.jpg","username":"吃了","wy_accid":"yy15244615473","wy_token":"c52f69e327c6c8e5cad8635de42662e7","usergrade":"0","userautograph":"恶魔","age":"25岁","address":"内蒙古","constellation":"摩羯座","userlike":"0","userbelike":"0","uid":"7","GiveCount":2}
         * ListPicNews : [{"fmID":"85","fmtitle":"泰山七日游","fmlook":"182","fmgood":"0","fmcomment":"0","fmfavorite":"5","fmpic":"http://47.92.136.19/uploads/article/20180810/20180810/a5fba7bdbdef8beb653129c024d56cb8.jpeg"}]
         * ListActiviy : [{"pfID":"102","pftitle":"几哈","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/0fa03a6c61ac405be84cbe2755400c10.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"104","pftitle":"哦嗖嗖嗖","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/d95ecae3fe60a4f5b9354d298b91490a.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"116","pftitle":"噜啦啦噜啦啦","pfpic":"http://47.92.136.19/uploads/xingcheng/20180810/20180810/19389386e012033588d35742d0849b64.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"119","pftitle":"测试","pfpic":"http://47.92.136.19/uploads/xingcheng/20180813/20180813/6f979b90e86d550e2e79657e6dd926d0.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"120","pftitle":"测试一下","pfpic":"http://47.92.136.19/uploads/xingcheng/20180813/20180813/3aa3f4376c5b7f623303f812f0b7b875.jpeg","pflook":"1","pfcomment":"0","pffavorite":"0"},{"pfID":"126","pftitle":"测试成功","pfpic":"http://47.92.136.19/uploads/xingcheng/20180813/20180813/61cde24c8f29c05fa072142ffc56fd3e.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"}]
         */

        private InfoBean info;
        private List<ListPicNewsBean> ListPicNews;
        private List<ListActiviyBean> ListActiviy;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ListPicNewsBean> getListPicNews() {
            return ListPicNews;
        }

        public void setListPicNews(List<ListPicNewsBean> ListPicNews) {
            this.ListPicNews = ListPicNews;
        }

        public List<ListActiviyBean> getListActiviy() {
            return ListActiviy;
        }

        public void setListActiviy(List<ListActiviyBean> ListActiviy) {
            this.ListActiviy = ListActiviy;
        }

        public static class InfoBean {
            /**
             * userpic : http://47.92.136.19/uploads/header/2018/08/14/e046ce4cc65dee1e7fb83a564981889a15342152253.jpg
             * username : 吃了
             * wy_accid : yy15244615473
             * wy_token : c52f69e327c6c8e5cad8635de42662e7
             * usergrade : 0
             * userautograph : 恶魔
             * age : 25岁
             * address : 内蒙古
             * constellation : 摩羯座
             * userlike : 0
             * userbelike : 0
             * uid : 7
             * GiveCount : 2
             */

            private String userpic;
            private String username;
            private String wy_accid;
            private String wy_token;
            private String usergrade;
            private String userautograph;
            private String age;
            private String address;
            private String constellation;
            private String userlike;
            private String userbelike;
            private String uid;
            private String GiveCount;

            public String getUserpic() {
                return userpic;
            }

            public void setUserpic(String userpic) {
                this.userpic = userpic;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getWy_accid() {
                return wy_accid;
            }

            public void setWy_accid(String wy_accid) {
                this.wy_accid = wy_accid;
            }

            public String getWy_token() {
                return wy_token;
            }

            public void setWy_token(String wy_token) {
                this.wy_token = wy_token;
            }

            public String getUsergrade() {
                return usergrade;
            }

            public void setUsergrade(String usergrade) {
                this.usergrade = usergrade;
            }

            public String getUserautograph() {
                return userautograph;
            }

            public void setUserautograph(String userautograph) {
                this.userautograph = userautograph;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getConstellation() {
                return constellation;
            }

            public void setConstellation(String constellation) {
                this.constellation = constellation;
            }

            public String getUserlike() {
                return userlike;
            }

            public void setUserlike(String userlike) {
                this.userlike = userlike;
            }

            public String getUserbelike() {
                return userbelike;
            }

            public void setUserbelike(String userbelike) {
                this.userbelike = userbelike;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getGiveCount() {
                return GiveCount;
            }

            public void setGiveCount(String GiveCount) {
                this.GiveCount = GiveCount;
            }
        }

        public static class ListPicNewsBean {
            /**
             * fmID : 85
             * fmtitle : 泰山七日游
             * fmlook : 182
             * fmgood : 0
             * fmcomment : 0
             * fmfavorite : 5
             * fmpic : http://47.92.136.19/uploads/article/20180810/20180810/a5fba7bdbdef8beb653129c024d56cb8.jpeg
             */

            private String fmID;
            private String fmtitle;
            private String fmlook;
            private String fmgood;
            private String fmcomment;
            private String fmfavorite;
            private String fmpic;

            public String getFmID() {
                return fmID;
            }

            public void setFmID(String fmID) {
                this.fmID = fmID;
            }

            public String getFmtitle() {
                return fmtitle;
            }

            public void setFmtitle(String fmtitle) {
                this.fmtitle = fmtitle;
            }

            public String getFmlook() {
                return fmlook;
            }

            public void setFmlook(String fmlook) {
                this.fmlook = fmlook;
            }

            public String getFmgood() {
                return fmgood;
            }

            public void setFmgood(String fmgood) {
                this.fmgood = fmgood;
            }

            public String getFmcomment() {
                return fmcomment;
            }

            public void setFmcomment(String fmcomment) {
                this.fmcomment = fmcomment;
            }

            public String getFmfavorite() {
                return fmfavorite;
            }

            public void setFmfavorite(String fmfavorite) {
                this.fmfavorite = fmfavorite;
            }

            public String getFmpic() {
                return fmpic;
            }

            public void setFmpic(String fmpic) {
                this.fmpic = fmpic;
            }
        }

        public static class ListActiviyBean {
            /**
             * pfID : 102
             * pftitle : 几哈
             * pfpic : http://47.92.136.19/uploads/xingcheng/20180802/20180802/0fa03a6c61ac405be84cbe2755400c10.jpeg
             * pflook : 0
             * pfcomment : 0
             * pffavorite : 0
             */

            private String pfID;
            private String pftitle;
            private String pfpic;
            private String pflook;
            private String pfcomment;
            private String pffavorite;

            public String getPfID() {
                return pfID;
            }

            public void setPfID(String pfID) {
                this.pfID = pfID;
            }

            public String getPftitle() {
                return pftitle;
            }

            public void setPftitle(String pftitle) {
                this.pftitle = pftitle;
            }

            public String getPfpic() {
                return pfpic;
            }

            public void setPfpic(String pfpic) {
                this.pfpic = pfpic;
            }

            public String getPflook() {
                return pflook;
            }

            public void setPflook(String pflook) {
                this.pflook = pflook;
            }

            public String getPfcomment() {
                return pfcomment;
            }

            public void setPfcomment(String pfcomment) {
                this.pfcomment = pfcomment;
            }

            public String getPffavorite() {
                return pffavorite;
            }

            public void setPffavorite(String pffavorite) {
                this.pffavorite = pffavorite;
            }
        }
    }
}
