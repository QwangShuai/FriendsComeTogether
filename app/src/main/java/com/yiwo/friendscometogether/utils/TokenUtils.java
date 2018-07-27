package com.yiwo.friendscometogether.utils;

/**
 * Created by Administrator on 2018/7/27.
 */

public class TokenUtils {
    public static String getToken(String url){
        String token = StringUtils.stringToMD5(url);
        String tokens = StringUtils.stringToMD5(token);
        return tokens;
    }
}
