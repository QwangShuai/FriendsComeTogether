package com.yiwo.friendscometogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.FriendRememberUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.custom.GlideImageLoader;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FriendsRememberFragment extends BaseFragment {
    View rootView;

    @BindView(R.id.fragment_friend_remember_banner)
    Banner banner;
    @BindView(R.id.fragment_friend_remember_rv)
    RecyclerView recyclerView;

    private FriendRememberUpDataAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_friends_remember,null);

        ButterKnife.bind(this, rootView);

        init();
        initList();

        return rootView;
    }

    private void initList() {

        LinearLayoutManager manager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        List<String> data = new ArrayList<>();
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739806900&di=5851898465493d1819030712458cee88&imgtype=0&src=http%3A%2F%2Fwww.5636.com%2Fnetbar%2Fuploads%2Fallimg%2F120620%2F21-120620102101526.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739807163&di=f3875854f37cf9d8f5261998f229bd03&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2Fday_100825%2F10082513558ebc5978899bb24c.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739807163&di=3be9c2032fcb53a8764c5d5a1409c58a&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2F201612%2F12%2F22290422z010jrivvoloid.jpg");
        adapter = new FriendRememberUpDataAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    private void init() {
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
                intent.setClass(getContext(), DetailsOfFriendsActivity.class);
                startActivity(intent);
            }
        });

    }
}
