package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/8/11.
 */

public class OtherInformationModel {

    /**
     * code : 200
     * message : 获取成功
     * obj : {"info":{"userpic":"http://47.92.136.19/uploads/header/2018/08/01/8071aac5eae40520f0e103fc4e40c18115330957024.jpg","username":"吃了","usergrade":"0","userautograph":"恶魔","age":25,"address":"内蒙古","constellation":"摩羯座","userlike":"0","userbelike":"0","uid":"7","GiveCount":2},"ListPicNews":[{"fmID":"85","fmtitle":"泰山七日游","fmlook":"123","fmgood":"0","fmcomment":"0","fmfavorite":"5","fmpic":"http://47.92.136.19/uploads/article/20180810/20180810/a5fba7bdbdef8beb653129c024d56cb8.jpeg"},{"fmID":"95","fmtitle":"嗯","fmlook":"0","fmgood":"0","fmcomment":"0","fmfavorite":"0","fmpic":"http://47.92.136.19/uploads/article/20180811/20180811/48a8cbb4459cb1166e4954bf54e6ac05.jpeg"}],"ListActiviy":[{"pfID":"98","pftitle":"smidgen","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/90c0bcbf93779696040e689822529859.jpg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"99","pftitle":"明厅","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/48b3edf3d1d30ce91084c2aedd128995.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"100","pftitle":"你mins","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/4f1fb134796ac3c84432111965188cd0.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"101","pftitle":"爹给你","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/d2d8c805b4ba842c593fa5bfd4130e75.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"102","pftitle":"几哈","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/0fa03a6c61ac405be84cbe2755400c10.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"103","pftitle":"susjshsjsjjzz","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/ebf599b3447695bfdb68f230c324195b.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"104","pftitle":"哦嗖嗖嗖","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/d95ecae3fe60a4f5b9354d298b91490a.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"106","pftitle":"提莫","pfpic":"http://47.92.136.19/uploads/xingcheng/20180806/20180806/61f7d9d9231780ce764b512b7ca57738.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"108","pftitle":"DOC文件","pfpic":"http://47.92.136.19/uploads/xingcheng/20180806/20180806/7f460ec44b760cd422226fb0c7efd21e.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"116","pftitle":"噜啦啦噜啦啦","pfpic":"http://47.92.136.19/uploads/xingcheng/20180810/20180810/19389386e012033588d35742d0849b64.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"}]}
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
         * info : {"userpic":"http://47.92.136.19/uploads/header/2018/08/01/8071aac5eae40520f0e103fc4e40c18115330957024.jpg","username":"吃了","usergrade":"0","userautograph":"恶魔","age":25,"address":"内蒙古","constellation":"摩羯座","userlike":"0","userbelike":"0","uid":"7","GiveCount":2}
         * ListPicNews : [{"fmID":"85","fmtitle":"泰山七日游","fmlook":"123","fmgood":"0","fmcomment":"0","fmfavorite":"5","fmpic":"http://47.92.136.19/uploads/article/20180810/20180810/a5fba7bdbdef8beb653129c024d56cb8.jpeg"},{"fmID":"95","fmtitle":"嗯","fmlook":"0","fmgood":"0","fmcomment":"0","fmfavorite":"0","fmpic":"http://47.92.136.19/uploads/article/20180811/20180811/48a8cbb4459cb1166e4954bf54e6ac05.jpeg"}]
         * ListActiviy : [{"pfID":"98","pftitle":"smidgen","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/90c0bcbf93779696040e689822529859.jpg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"99","pftitle":"明厅","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/48b3edf3d1d30ce91084c2aedd128995.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"100","pftitle":"你mins","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/4f1fb134796ac3c84432111965188cd0.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"101","pftitle":"爹给你","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/d2d8c805b4ba842c593fa5bfd4130e75.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"102","pftitle":"几哈","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/0fa03a6c61ac405be84cbe2755400c10.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"103","pftitle":"susjshsjsjjzz","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/ebf599b3447695bfdb68f230c324195b.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"104","pftitle":"哦嗖嗖嗖","pfpic":"http://47.92.136.19/uploads/xingcheng/20180802/20180802/d95ecae3fe60a4f5b9354d298b91490a.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"106","pftitle":"提莫","pfpic":"http://47.92.136.19/uploads/xingcheng/20180806/20180806/61f7d9d9231780ce764b512b7ca57738.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"108","pftitle":"DOC文件","pfpic":"http://47.92.136.19/uploads/xingcheng/20180806/20180806/7f460ec44b760cd422226fb0c7efd21e.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"},{"pfID":"116","pftitle":"噜啦啦噜啦啦","pfpic":"http://47.92.136.19/uploads/xingcheng/20180810/20180810/19389386e012033588d35742d0849b64.jpeg","pflook":"0","pfcomment":"0","pffavorite":"0"}]
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
             * userpic : http://47.92.136.19/uploads/header/2018/08/01/8071aac5eae40520f0e103fc4e40c18115330957024.jpg
             * username : 吃了
             * usergrade : 0
             * userautograph : 恶魔
             * age : 25
             * address : 内蒙古
             * constellation : 摩羯座
             * userlike : 0
             * userbelike : 0
             * uid : 7
             * GiveCount : 2
             */

            private String userpic;
            private String username;
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
             * fmlook : 123
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
             * pfID : 98
             * pftitle : smidgen
             * pfpic : http://47.92.136.19/uploads/xingcheng/20180802/20180802/90c0bcbf93779696040e689822529859.jpg
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
