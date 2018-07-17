package com.yiwo.friendscometogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.FriendRememberUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.custom.GlideImageLoader;
import com.yiwo.friendscometogether.model.CityModel;
import com.yiwo.friendscometogether.model.FriendsRememberModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.CityActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        ScreenAdapterTools.getInstance().loadView(rootView);

        ButterKnife.bind(this, rootView);

        init(banner,DetailsOfFriendsActivity.class);
        initData();

        return rootView;
    }

    private void initData() {

        OkHttpUtils.post()
                .tag(this)
                .url(NetConfig.friendsRememberUrl)
                .addParams("page",1+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        String result = new String(Base64.decode(response.getBytes(),Base64.DEFAULT));
                        try {
                            JSONObject jsonObject =new JSONObject(result);
                            int code = jsonObject.optInt("code");
                            if(code==200){
                                Gson gson = new Gson();
                                FriendsRememberModel friendsRememberModel = gson.fromJson(result, FriendsRememberModel.class);
                                initList(friendsRememberModel.getObj());
                            } else {
                                toToast(getContext(),jsonObject.optString("message").toString());
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    private void initList(List<FriendsRememberModel.ObjBean> data) {

        LinearLayoutManager manager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new FriendRememberUpDataAdapter(data);
        recyclerView.setAdapter(adapter);

    }

}
