package com.yiwo.friendscometogether.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.FriendRememberUpDataAdapter;
import com.yiwo.friendscometogether.adapter.FriendTogetherUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.model.AllBannerModel;
import com.yiwo.friendscometogether.model.CityModel;
import com.yiwo.friendscometogether.model.FriendsRememberModel;
import com.yiwo.friendscometogether.model.FriendsTogethermodel;
import com.yiwo.friendscometogether.model.UserFocusModel;
import com.yiwo.friendscometogether.model.UserLabelModel;
import com.yiwo.friendscometogether.network.ActivityConfig;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.CityActivity;
import com.yiwo.friendscometogether.pages.CreateFriendRememberActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendTogetherActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.yiwo.friendscometogether.pages.LoginActivity;
import com.yiwo.friendscometogether.pages.MyFocusActivity;
import com.yiwo.friendscometogether.pages.SearchActivity;
import com.yiwo.friendscometogether.sp.SpImp;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

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

public class FriendsTogetherFragment extends BaseFragment {
    View rootView;
    @BindView(R.id.fragment_friend_together_banner)
    Banner banner;
    @BindView(R.id.fragment_friend_together_rv)
    RecyclerView recyclerView;
    @BindView(R.id.select_city)
    LinearLayout cityLl;
    @BindView(R.id.select_lable)
    LinearLayout lableLl;
    @BindView(R.id.search_leader)
    LinearLayout searchLl;
    @BindView(R.id.fragment_friends_together_city_tv)
    TextView cityTv;
    @BindView(R.id.fragment_friends_together_lable_tv)
    TextView lableTv;
    @BindView(R.id.fragment_friend_together_refreshLayout)
    RefreshLayout refreshLayout;

    private FriendTogetherUpDataAdapter adapter;
    private String[] itemId;
    private String[] itemName;
    private String yourChoiceId = "";
    private String yourChoiceName = "";
    SpImp spImp;

    private int page = 1;
    private List<FriendsTogethermodel.ObjBean> mList;

    private String cityId = "";

    private String sign = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_friends_together, null);
        ScreenAdapterTools.getInstance().loadView(rootView);

        ButterKnife.bind(this, rootView);

        spImp = new SpImp(getContext());
        initData();
        return rootView;
    }

    private void initData() {

        ViseHttp.POST(NetConfig.allBannerUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.allBannerUrl))
                .addParam("type", "2")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson = new Gson();
                                final AllBannerModel bannerModel = gson.fromJson(data, AllBannerModel.class);
                                List<String> list = new ArrayList<>();
                                for (int i = 0; i < bannerModel.getObj().size(); i++) {
                                    list.add(bannerModel.getObj().get(i).getPic());
                                }
                                init(banner, list);
                                banner.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
                                        Intent intent = new Intent(getContext(), DetailsOfFriendTogetherActivity.class);
                                        intent.putExtra("pfID", bannerModel.getObj().get(position).getLeftid());
                                        startActivity(intent);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherUrl);
                ViseHttp.POST(NetConfig.friendsTogetherUrl)
                        .addParam("app_key", token)
                        .addParam("page", "1")
                        .addParam("userID", spImp.getUID())
                        .addParam("city_id", cityId)
                        .addParam("sign", sign)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getInt("code") == 200) {
                                        Log.e("222", data);
                                        FriendsTogethermodel model = new Gson().fromJson(data, FriendsTogethermodel.class);
                                        page = 2;
                                        mList.clear();
                                        mList.addAll(model.getObj());
                                        adapter.notifyDataSetChanged();
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
                String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherUrl);
                ViseHttp.POST(NetConfig.friendsTogetherUrl)
                        .addParam("app_key", token)
                        .addParam("page", page + "")
                        .addParam("userID", spImp.getUID())
                        .addParam("city_id", cityId)
                        .addParam("sign", sign)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getInt("code") == 200) {
                                        Log.e("222", data);
                                        FriendsTogethermodel model = new Gson().fromJson(data, FriendsTogethermodel.class);
                                        page = page + 1;
                                        mList.addAll(model.getObj());
                                        adapter.notifyDataSetChanged();
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

        getLable();

        String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherUrl);
        ViseHttp.POST(NetConfig.friendsTogetherUrl)
                .addParam("app_key", token)
                .addParam("page", "1")
                .addParam("userID", spImp.getUID())
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Log.e("222", data);
                                FriendsTogethermodel model = new Gson().fromJson(data, FriendsTogethermodel.class);
                                page = 2;
                                initList(model.getObj());
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

    private void initList(List<FriendsTogethermodel.ObjBean> data) {

        mList = data;

        LinearLayoutManager manager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new FriendTogetherUpDataAdapter(mList);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.select_city, R.id.select_lable, R.id.search_leader})
    public void OnClick(View v) {
        if (spImp.getUID().equals("0")) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else {
            Log.i("110120", spImp.getUID());
            switch (v.getId()) {
                case R.id.select_city:
                    Intent it = new Intent(getActivity(), CityActivity.class);
                    it.putExtra(ActivityConfig.ACTIVITY, "home");
                    startActivityForResult(it, 1);
                    break;
                case R.id.select_lable:
                    AlertDialog.Builder singleChoiceDialog =
                            new AlertDialog.Builder(getContext());
                    singleChoiceDialog.setTitle("请选择标签");
                    // 第二个参数是默认选项，此处设置为0
                    singleChoiceDialog.setSingleChoiceItems(itemName, 0,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    yourChoiceName = itemName[which];
                                    yourChoiceId = itemId[which];
                                }
                            });
                    singleChoiceDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (TextUtils.isEmpty(yourChoiceName)) {
                                        lableTv.setText(itemName[0]);
                                        yourChoiceId = itemId[0];
                                        sign = yourChoiceId;
                                        String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherUrl);
                                        ViseHttp.POST(NetConfig.friendsTogetherUrl)
                                                .addParam("app_key", token)
                                                .addParam("page", "1")
                                                .addParam("userID", spImp.getUID())
                                                .addParam("city_id", cityId)
                                                .addParam("sign", sign)
                                                .request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String data) {
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(data);
                                                            if (jsonObject.getInt("code") == 200) {
                                                                Log.e("222", data);
                                                                FriendsTogethermodel model = new Gson().fromJson(data, FriendsTogethermodel.class);
                                                                page = 2;
                                                                initList(model.getObj());
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFail(int errCode, String errMsg) {

                                                    }
                                                });
                                    } else {
                                        lableTv.setText(yourChoiceName);
                                        yourChoiceName = "";
                                        sign = yourChoiceId;
                                        String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherUrl);
                                        ViseHttp.POST(NetConfig.friendsTogetherUrl)
                                                .addParam("app_key", token)
                                                .addParam("page", "1")
                                                .addParam("userID", spImp.getUID())
                                                .addParam("city_id", cityId)
                                                .addParam("sign", sign)
                                                .request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String data) {
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(data);
                                                            if (jsonObject.getInt("code") == 200) {
                                                                Log.e("222", data);
                                                                FriendsTogethermodel model = new Gson().fromJson(data, FriendsTogethermodel.class);
                                                                page = 2;
                                                                initList(model.getObj());
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
                            });
                    singleChoiceDialog.setNegativeButton("重置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sign = "";
                            lableTv.setText("选择标签");
                            String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherUrl);
                            ViseHttp.POST(NetConfig.friendsTogetherUrl)
                                    .addParam("app_key", token)
                                    .addParam("page", "1")
                                    .addParam("userID", spImp.getUID())
                                    .addParam("city_id", cityId)
                                    .addParam("sign", sign)
                                    .request(new ACallback<String>() {
                                        @Override
                                        public void onSuccess(String data) {
                                            try {
                                                JSONObject jsonObject = new JSONObject(data);
                                                if (jsonObject.getInt("code") == 200) {
                                                    Log.e("222", data);
                                                    FriendsTogethermodel model = new Gson().fromJson(data, FriendsTogethermodel.class);
                                                    page = 2;
                                                    initList(model.getObj());
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
                    });
                    singleChoiceDialog.show();
                    break;
                case R.id.search_leader:
                    Intent intent = new Intent(getContext(), SearchActivity.class);
                    intent.putExtra("type", "1");
                    startActivity(intent);
                    break;
            }
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && resultCode == 1) {
            CityModel model = (CityModel) data.getSerializableExtra(ActivityConfig.CITY);
            cityTv.setText(model.getName());
            cityId = model.getId();
            String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherUrl);
            ViseHttp.POST(NetConfig.friendsTogetherUrl)
                    .addParam("app_key", token)
                    .addParam("page", "1")
                    .addParam("userID", spImp.getUID())
                    .addParam("city_id", cityId)
                    .addParam("sign", sign)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.getInt("code") == 200) {
                                    Log.e("222", data);
                                    FriendsTogethermodel model = new Gson().fromJson(data, FriendsTogethermodel.class);
                                    page = 2;
                                    initList(model.getObj());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }else if(requestCode == 1 && resultCode == 2){
            cityId = "";
            cityTv.setText("选择城市");
            String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherUrl);
            ViseHttp.POST(NetConfig.friendsTogetherUrl)
                    .addParam("app_key", token)
                    .addParam("page", "1")
                    .addParam("userID", spImp.getUID())
                    .addParam("city_id", cityId)
                    .addParam("sign", sign)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.getInt("code") == 200) {
                                    Log.e("222", data);
                                    FriendsTogethermodel model = new Gson().fromJson(data, FriendsTogethermodel.class);
                                    page = 2;
                                    initList(model.getObj());
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

    public void getLable() {
        ViseHttp.POST(NetConfig.userLabel)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userLabel))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("222", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson = new Gson();
                                UserLabelModel userLabelModel = gson.fromJson(data, UserLabelModel.class);
                                itemId = new String[userLabelModel.getObj().size()];
                                itemName = new String[userLabelModel.getObj().size()];
                                for (int i = 0; i < userLabelModel.getObj().size(); i++) {
                                    itemId[i] = userLabelModel.getObj().get(i).getLID();
                                    itemName[i] = userLabelModel.getObj().get(i).getLname();
                                }
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
