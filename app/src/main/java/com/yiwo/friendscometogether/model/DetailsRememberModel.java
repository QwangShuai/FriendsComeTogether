package com.yiwo.friendscometogether.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/31.
 */

public class DetailsRememberModel {

    /**
     * code : 200
     * message : 获取成功
     * obj : {"content":{"fmID":"60","fmtitle":"北京","fmpic":"uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","fmlook":"0","fmfavorite":"0","fmgotime":"2018-07-31","fmendtime":"2018-07-31","percapitacost":"2.00","fmaddress":"北京市-北京市-东城区","usergrade":"0","username":"baiducom","userpic":"uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg","follow":0},"activityInfo":{"pfcontent":"登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！","pftime":"2018-07-24 14:04:30","pflook":"12","pfcomment":"12","pfpic":"http://127.0.0.1/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg"},"Renew":[{"ffID":"60","fftitle":"吃了","ffcontect":"嗯","pic":[{"ffptitle":"吧","ffpurl":"http://47.92.136.19/uploads/header/2018/07/31/42e2febc149d7d233867d754877d85e41533007490998.jpg","userID":"7","username":"baiducom","userpic":"http://127.0.0.1/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg"},{"ffptitle":"","ffpurl":"http://47.92.136.19/uploads/header/2018/07/31/42e2febc149d7d233867d754877d85e41533007490110.jpg","userID":"7","username":"baiducom","userpic":"http://127.0.0.1/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg"}]}],"InserList":[{"ffID":"59","fftitle":"吃了","ffcontect":"出来了","pic":[]}]}
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
         * content : {"fmID":"60","fmtitle":"北京","fmpic":"uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg","fmlook":"0","fmfavorite":"0","fmgotime":"2018-07-31","fmendtime":"2018-07-31","percapitacost":"2.00","fmaddress":"北京市-北京市-东城区","usergrade":"0","username":"baiducom","userpic":"uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg","follow":0}
         * activityInfo : {"pfcontent":"登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！","pftime":"2018-07-24 14:04:30","pflook":"12","pfcomment":"12","pfpic":"http://127.0.0.1/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg"}
         * Renew : [{"ffID":"60","fftitle":"吃了","ffcontect":"嗯","pic":[{"ffptitle":"吧","ffpurl":"http://47.92.136.19/uploads/header/2018/07/31/42e2febc149d7d233867d754877d85e41533007490998.jpg","userID":"7","username":"baiducom","userpic":"http://127.0.0.1/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg"},{"ffptitle":"","ffpurl":"http://47.92.136.19/uploads/header/2018/07/31/42e2febc149d7d233867d754877d85e41533007490110.jpg","userID":"7","username":"baiducom","userpic":"http://127.0.0.1/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg"}]}]
         * InserList : [{"ffID":"59","fftitle":"吃了","ffcontect":"出来了","pic":[]}]
         */

        private ContentBean content;
        private ActivityInfoBean activityInfo;
        private List<RenewBean> Renew;
        private List<InserListBean> InserList;

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public ActivityInfoBean getActivityInfo() {
            return activityInfo;
        }

        public void setActivityInfo(ActivityInfoBean activityInfo) {
            this.activityInfo = activityInfo;
        }

        public List<RenewBean> getRenew() {
            return Renew;
        }

        public void setRenew(List<RenewBean> Renew) {
            this.Renew = Renew;
        }

        public List<InserListBean> getInserList() {
            return InserList;
        }

        public void setInserList(List<InserListBean> InserList) {
            this.InserList = InserList;
        }

        public static class ContentBean {
            /**
             * fmID : 60
             * fmtitle : 北京
             * fmpic : uploads/article/20180731/20180731/ef5e6d09a5158f3cb93e17bb0937c18a.jpg
             * fmlook : 0
             * fmfavorite : 0
             * fmgotime : 2018-07-31
             * fmendtime : 2018-07-31
             * percapitacost : 2.00
             * fmaddress : 北京市-北京市-东城区
             * usergrade : 0
             * username : baiducom
             * userpic : uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg
             * follow : 0
             */

            private String fmID;
            private String fmtitle;
            private String fmpic;
            private String fmlook;
            private String fmfavorite;
            private String fmgotime;
            private String fmendtime;
            private String percapitacost;
            private String fmaddress;
            private String usergrade;
            private String username;
            private String userpic;
            private int follow;

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

            public String getFmaddress() {
                return fmaddress;
            }

            public void setFmaddress(String fmaddress) {
                this.fmaddress = fmaddress;
            }

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

            public String getUserpic() {
                return userpic;
            }

            public void setUserpic(String userpic) {
                this.userpic = userpic;
            }

            public int getFollow() {
                return follow;
            }

            public void setFollow(int follow) {
                this.follow = follow;
            }
        }

        public static class ActivityInfoBean {
            /**
             * pfcontent : 登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！登峨眉山！
             * pftime : 2018-07-24 14:04:30
             * pflook : 12
             * pfcomment : 12
             * pfpic : http://127.0.0.1/uploads/activity/20180720/20180720/73cc839014b7516c8d975ec3aa52e382.jpg
             */

            private String pfcontent;
            private String pftime;
            private String pflook;
            private String pfcomment;
            private String pfpic;

            public String getPfcontent() {
                return pfcontent;
            }

            public void setPfcontent(String pfcontent) {
                this.pfcontent = pfcontent;
            }

            public String getPftime() {
                return pftime;
            }

            public void setPftime(String pftime) {
                this.pftime = pftime;
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

            public String getPfpic() {
                return pfpic;
            }

            public void setPfpic(String pfpic) {
                this.pfpic = pfpic;
            }
        }

        public static class RenewBean {
            /**
             * ffID : 60
             * fftitle : 吃了
             * ffcontect : 嗯
             * pic : [{"ffptitle":"吧","ffpurl":"http://47.92.136.19/uploads/header/2018/07/31/42e2febc149d7d233867d754877d85e41533007490998.jpg","userID":"7","username":"baiducom","userpic":"http://127.0.0.1/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg"},{"ffptitle":"","ffpurl":"http://47.92.136.19/uploads/header/2018/07/31/42e2febc149d7d233867d754877d85e41533007490110.jpg","userID":"7","username":"baiducom","userpic":"http://127.0.0.1/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg"}]
             */

            private String ffID;
            private String fftitle;
            private String ffcontect;
            private List<PicBean> pic;

            public String getFfID() {
                return ffID;
            }

            public void setFfID(String ffID) {
                this.ffID = ffID;
            }

            public String getFftitle() {
                return fftitle;
            }

            public void setFftitle(String fftitle) {
                this.fftitle = fftitle;
            }

            public String getFfcontect() {
                return ffcontect;
            }

            public void setFfcontect(String ffcontect) {
                this.ffcontect = ffcontect;
            }

            public List<PicBean> getPic() {
                return pic;
            }

            public void setPic(List<PicBean> pic) {
                this.pic = pic;
            }

            public static class PicBean {
                /**
                 * ffptitle : 吧
                 * ffpurl : http://47.92.136.19/uploads/header/2018/07/31/42e2febc149d7d233867d754877d85e41533007490998.jpg
                 * userID : 7
                 * username : baiducom
                 * userpic : http://127.0.0.1/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg
                 */

                private String ffptitle;
                private String ffpurl;
                private String userID;
                private String username;
                private String userpic;

                public String getFfptitle() {
                    return ffptitle;
                }

                public void setFfptitle(String ffptitle) {
                    this.ffptitle = ffptitle;
                }

                public String getFfpurl() {
                    return ffpurl;
                }

                public void setFfpurl(String ffpurl) {
                    this.ffpurl = ffpurl;
                }

                public String getUserID() {
                    return userID;
                }

                public void setUserID(String userID) {
                    this.userID = userID;
                }

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
            }
        }

        public static class InserListBean {
            /**
             * ffID : 59
             * fftitle : 吃了
             * ffcontect : 出来了
             * pic : []
             */

            private String ffID;
            private String fftitle;
            private String ffcontect;
            private List<RenewBean.PicBean> pic;

            public String getFfID() {
                return ffID;
            }

            public void setFfID(String ffID) {
                this.ffID = ffID;
            }

            public String getFftitle() {
                return fftitle;
            }

            public void setFftitle(String fftitle) {
                this.fftitle = fftitle;
            }

            public String getFfcontect() {
                return ffcontect;
            }

            public void setFfcontect(String ffcontect) {
                this.ffcontect = ffcontect;
            }

            public List<RenewBean.PicBean> getPic() {
                return pic;
            }

            public void setPic(List<RenewBean.PicBean> pic) {
                this.pic = pic;
            }
        }
    }
}
