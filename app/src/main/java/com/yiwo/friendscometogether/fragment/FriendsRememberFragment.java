package com.yiwo.friendscometogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.okhttp.Request;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.FriendRememberUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.custom.GlideImageLoader;
import com.yiwo.friendscometogether.model.CityModel;
import com.yiwo.friendscometogether.model.FriendsRememberModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.CityActivity;
import com.yiwo.friendscometogether.pages.CreateIntercalationActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.yiwo.friendscometogether.pages.LoginActivity;
import com.yiwo.friendscometogether.pages.SearchActivity;
import com.yiwo.friendscometogether.sp.SpImp;
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
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FriendsRememberFragment extends BaseFragment {
    View rootView;

    @BindView(R.id.fragment_friend_remember_banner)
    Banner banner;
    @BindView(R.id.fragment_friend_remember_rv)
    RecyclerView recyclerView;
    @BindView(R.id.searchLl)
    LinearLayout llSearch;
    @BindView(R.id.fragment_friend_remember_refreshLayout)
    RefreshLayout refreshLayout;

    private FriendRememberUpDataAdapter adapter;
    private List<FriendsRememberModel.ObjBean> mList;

    private SpImp spImp;
    private String uid = "";

    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_friends_remember, null);
        ScreenAdapterTools.getInstance().loadView(rootView);

        ButterKnife.bind(this, rootView);
        spImp = new SpImp(getContext());

        init(banner, DetailsOfFriendsActivity.class);
        initData();

        return rootView;
    }

    private void initData() {

        uid = spImp.getUID();

        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                ViseHttp.POST(NetConfig.friendsRememberUrl)
                        .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.friendsRememberUrl))
                        .addParam("page", "1")
                        .addParam("userID", uid)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    int code = jsonObject.getInt("code");
                                    if (code == 200) {
                                        Gson gson = new Gson();
                                        FriendsRememberModel friendsRememberModel = gson.fromJson(data, FriendsRememberModel.class);
                                        mList.clear();
                                        mList.addAll(friendsRememberModel.getObj());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                    } else {
                                        toToast(getContext(), jsonObject.getString("message"));
                                    }
                                    refreshlayout.finishRefresh();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshlayout.finishRefresh();
                            }
                        });
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                ViseHttp.POST(NetConfig.friendsRememberUrl)
                        .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.friendsRememberUrl))
                        .addParam("page", page + "")
                        .addParam("userID", uid)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    int code = jsonObject.getInt("code");
                                    if (code == 200) {
                                        Gson gson = new Gson();
                                        FriendsRememberModel friendsRememberModel = gson.fromJson(data, FriendsRememberModel.class);
                                        mList.addAll(friendsRememberModel.getObj());
                                        adapter.notifyDataSetChanged();
                                        page = page + 1;
                                    } else {
                                        toToast(getContext(), jsonObject.getString("message"));
                                    }
                                    refreshlayout.finishLoadMore();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshlayout.finishLoadMore();
                            }
                        });
            }
        });

        ViseHttp.POST(NetConfig.friendsRememberUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.friendsRememberUrl))
                .addParam("page", "1")
                .addParam("userID", uid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                Gson gson = new Gson();
                                FriendsRememberModel friendsRememberModel = gson.fromJson(data, FriendsRememberModel.class);
                                page = page + 1;
                                initList(friendsRememberModel.getObj());
                            } else {
                                toToast(getContext(), jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    private void initList(final List<FriendsRememberModel.ObjBean> data) {

        mList = data;
        LinearLayoutManager manager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new FriendRememberUpDataAdapter(mList);
        recyclerView.setAdapter(adapter);
        adapter.setOnFocusListener(new FriendRememberUpDataAdapter.OnFocusListener() {
            @Override
            public void onFocus(final int position) {
                if(TextUtils.isEmpty(uid)||uid.equals("0")){
                    Intent intent = new Intent();
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    if(mList.get(position).getLook().equals("0")){
                        ViseHttp.POST(NetConfig.userFocusUrl)
                                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.userFocusUrl))
                                .addParam("uid", uid)
                                .addParam("likeId", data.get(position).getUserID())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.getInt("code") == 200){
                                                mList.get(position).setLook("1");
                                                adapter.notifyDataSetChanged();
                                                toToast(getContext(), "关注成功");
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                    }
                }
            }
        });

    }

    @OnClick({R.id.searchLl})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.searchLl:
                intent.setClass(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

}
