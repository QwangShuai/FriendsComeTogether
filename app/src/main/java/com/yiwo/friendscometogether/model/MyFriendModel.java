package com.yiwo.friendscometogether.model;

import java.util.List;

import me.zhouzhuo.zzletterssidebar.anotation.Letter;
import me.zhouzhuo.zzletterssidebar.entity.SortModel;

/**
 * Created by Administrator on 2018/9/4 0004.
 */

public class MyFriendModel {

    /**
     * code : 200
     * message : 获取成功!
     * obj : [{"username":"我们旅游吧","id":"31","userpic":"http://47.92.136.19/uploads/header/2018/08/16/e833291d6bf838e3be831414c4346960153439944612.jpg","wy_accid":"yy18346038613"},{"username":"龙哥","id":"34","userpic":"http://47.92.136.19/uploads/header/2018/08/31/78c8ad28039f0d14cd04beba19223ea915356897983.jpg","wy_accid":"yy13804583880"},{"username":"崔薇","id":"39","userpic":"http://47.92.136.19/01786557e4a6fa0000018c1bf080ca.png","wy_accid":"yy15145161164"}]
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

    public static class ObjBean extends SortModel {
        /**
         * username : 我们旅游吧
         * id : 31
         * userpic : http://47.92.136.19/uploads/header/2018/08/16/e833291d6bf838e3be831414c4346960153439944612.jpg
         * wy_accid : yy18346038613
         */

        @Letter(isSortField = true)
        private String username;
        private String id;
        private String userpic;
        private String wy_accid;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserpic() {
            return userpic;
        }

        public void setUserpic(String userpic) {
            this.userpic = userpic;
        }

        public String getWy_accid() {
            return wy_accid;
        }

        public void setWy_accid(String wy_accid) {
            this.wy_accid = wy_accid;
        }
    }
}
