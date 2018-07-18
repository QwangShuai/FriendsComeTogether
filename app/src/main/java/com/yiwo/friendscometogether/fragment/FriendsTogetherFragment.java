package com.yiwo.friendscometogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.FriendRememberUpDataAdapter;
import com.yiwo.friendscometogether.adapter.FriendTogetherUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.model.FriendsRememberModel;
import com.yiwo.friendscometogether.model.FriendsTogethermodel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FriendsTogetherFragment extends BaseFragment{
    View rootView;
    @BindView(R.id.fragment_friend_together_banner)
    Banner banner;
    @BindView(R.id.fragment_friend_together_rv)
    RecyclerView recyclerView;

    private FriendTogetherUpDataAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_friends_together,null);
        ScreenAdapterTools.getInstance().loadView(rootView);

        ButterKnife.bind(this, rootView);

        init(banner,DetailsOfFriendsActivity.class);
        return rootView;
    }

    private void initData() {

        ViseHttp.POST(NetConfig.friendsTogetherUrl)
                .addParam("page", "1")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    private void initList(List<FriendsTogethermodel.ObjBean> data) {

        LinearLayoutManager manager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new FriendTogetherUpDataAdapter(data);
        recyclerView.setAdapter(adapter);

    }
}
