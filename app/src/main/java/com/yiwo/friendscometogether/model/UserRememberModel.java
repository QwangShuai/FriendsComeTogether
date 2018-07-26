package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public class UserRememberModel {

    /**
     * code : 200
     * message : 获取成功
     * obj : [{"fmID":"14","fmtitle":"就下去","fmpic":"http://47.92.136.19/https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739806900&di=5851898465493d1819030712458cee88&im","fmgotime":"2018-07-25","fmendtime":"2018-07-31","percapitacost":"125.00","fmlook":"0","fmfavorite":"0"},{"fmID":"13","fmtitle":"123123","fmpic":"http://47.92.136.19/https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739806900&di=5851898465493d1819030712458cee88&im","fmgotime":"2018-1-11","fmendtime":"2018-12-12","percapitacost":"120.00","fmlook":"0","fmfavorite":"0"}]
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
         * fmID : 14
         * fmtitle : 就下去
         * fmpic : http://47.92.136.19/https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739806900&di=5851898465493d1819030712458cee88&im
         * fmgotime : 2018-07-25
         * fmendtime : 2018-07-31
         * percapitacost : 125.00
         * fmlook : 0
         * fmfavorite : 0
         */

        private String fmID;
        private String fmtitle;
        private String fmpic;
        private String fmgotime;
        private String fmendtime;
        private String percapitacost;
        private String fmlook;
        private String fmfavorite;

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

        public String getFmpic() {
            return fmpic;
        }

        public void setFmpic(String fmpic) {
            this.fmpic = fmpic;
        }

        public String getFmgotime() {
            return fmgotime;
        }

        public void setFmgotime(String fmgotime) {
            this.fmgotime = fmgotime;
        }

        public String getFmendtime() {
            return fmendtime;
        }

        public void setFmendtime(String fmendtime) {
            this.fmendtime = fmendtime;
        }

        public String getPercapitacost() {
            return percapitacost;
        }

        public void setPercapitacost(String percapitacost) {
            this.percapitacost = percapitacost;
        }

        public String getFmlook() {
            return fmlook;
        }

        public void setFmlook(String fmlook) {
            this.fmlook = fmlook;
        }

        public String getFmfavorite() {
            return fmfavorite;
        }

        public void setFmfavorite(String fmfavorite) {
            this.fmfavorite = fmfavorite;
        }
    }
}
