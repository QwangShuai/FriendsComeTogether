package com.yiwo.friendscometogether.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.yiwo.friendscometogether.utils.StringUtils;

/**
 * Created by Administrator on 2018/7/16.
 */

public class BaseFragment extends Fragment {

    public void toToast(Context c, String content){
        Toast.makeText(c,content,Toast.LENGTH_SHORT).show();
    }
    public String getToken(String url){
        String token = StringUtils.stringToMD5(url);
        String tokens = StringUtils.stringToMD5(token);
        return tokens;
    }
}
