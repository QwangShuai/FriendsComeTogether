package com.yiwo.friendscometogether.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.yiwo.friendscometogether.custom.GlideImageLoader;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.yiwo.friendscometogether.utils.StringUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

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
    public void init(Banner banner, final Class activity) {
        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739806900&di=5851898465493d1819030712458cee88&imgtype=0&src=http%3A%2F%2Fwww.5636.com%2Fnetbar%2Fuploads%2Fallimg%2F120620%2F21-120620102101526.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739807163&di=f3875854f37cf9d8f5261998f229bd03&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2Fday_100825%2F10082513558ebc5978899bb24c.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739807163&di=3be9c2032fcb53a8764c5d5a1409c58a&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2F201612%2F12%2F22290422z010jrivvoloid.jpg");
        titles.add("活动介绍活动介绍活动介绍活动介绍");
        titles.add("活动介绍活动介绍活动介绍活动介绍");
        titles.add("活动介绍活动介绍活动介绍活动介绍");

        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getContext(), activity);
                startActivity(intent);
            }
        });

    }
}
