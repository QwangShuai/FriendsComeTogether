package com.yiwo.friendscometogether.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.yiwo.friendscometogether.adapter.HomeHotAdapter;
import com.yiwo.friendscometogether.adapter.HomeTogetherAdapter;
import com.yiwo.friendscometogether.adapter.VideoAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.custom.ScalableCardHelper;
import com.yiwo.friendscometogether.model.AllBannerModel;
import com.yiwo.friendscometogether.model.CityModel;
import com.yiwo.friendscometogether.model.FocusOnLeaderModel;
import com.yiwo.friendscometogether.model.FriendsTogethermodel;
import com.yiwo.friendscometogether.model.GoogleCityModel;
import com.yiwo.friendscometogether.model.HomeHotFriendsRememberModel;
import com.yiwo.friendscometogether.model.HomeTogetherModel;
import com.yiwo.friendscometogether.network.ActivityConfig;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.CityActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendTogetherActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.yiwo.friendscometogether.pages.LoginActivity;
import com.yiwo.friendscometogether.pages.MessageCenterActivity;
import com.yiwo.friendscometogether.pages.SearchActivity;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;
import com.yiwo.friendscometogether.utils.UserUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

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

public class HomeFragment extends BaseFragment {
    View rootView;
    @BindView(R.id.fragment_home_banner)
    Banner banner;
    @BindView(R.id.messageIv)
    ImageView messageIv;
    @BindView(R.id.home_hotRv)
    RecyclerView home_hotRv;
    @BindView(R.id.home_hotVideoRv)
    RecyclerView home_hotVideoRv;
    @BindView(R.id.home_hotTogetherRv)
    RecyclerView home_hotTogetherRv;
    @BindView(R.id.locationRl)
    RelativeLayout locationRl;
    @BindView(R.id.cityTv)
    TextView cityTv;
    @BindView(R.id.home_numTv)
    TextView home_numTv;
    @BindView(R.id.searchLl)
    LinearLayout searchLl;
    @BindView(R.id.tv_tuijian)
    TextView tvTuijian;
    @BindView(R.id.tv_remen)
    TextView tvRemen;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;

    private LocationManager locationManager;
    private double latitude = 0.0;
    private double longitude = 0.0;
    public static int GET_LOCATION = 1;
    String latLongString = "";
    private HomeHotAdapter adapter;
    private VideoAdapter videoAdapter;
    private HomeTogetherAdapter togetherAdapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    getCity();
                    break;
                case 2:
                    cityTv.setText(latLongString);
                    break;
            }
        }

    };

    private int page = 1;
    private List<HomeTogetherModel.ObjBean> mList;

    private List<HomeHotFriendsRememberModel.ObjBean.InfoBean> mList1;

    @BindView(R.id.fragment_home_refreshLayout)
    RefreshLayout refreshLayout;

    private SpImp spImp;
    private String uid = "";

    private String cityId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (rootView==null){
        rootView = inflater.inflate(R.layout.fragment_home, null);
//        }
//        ViewGroup parent = (ViewGroup) rootView.getParent();
//        if(parent!=null){
//            parent.removeView(rootView);
//        }

        ButterKnife.bind(this, rootView);
        ScreenAdapterTools.getInstance().loadView(rootView);
        spImp = new SpImp(getContext());
        getLocation();
        initData();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        uid = spImp.getUID();
    }

    public void initData() {

        ViseHttp.POST(NetConfig.allBannerUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.allBannerUrl))
                .addParam("type", "1")
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
                                        if (bannerModel.getObj().get(position).getFirst_type().equals("0")) {
                                            Intent intent = new Intent(getContext(), DetailsOfFriendTogetherActivity.class);
                                            intent.putExtra("pfID", bannerModel.getObj().get(position).getLeftid());
                                            startActivity(intent);
                                        } else if (bannerModel.getObj().get(position).getFirst_type().equals("1")) {
                                            Intent intent = new Intent(getContext(), DetailsOfFriendsActivity.class);
                                            intent.putExtra("fmid", bannerModel.getObj().get(position).getLeftid());
                                            startActivity(intent);
                                        }
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

        uid = spImp.getUID();

        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                String tokens = getToken(NetConfig.BaseUrl + NetConfig.homeTogetherListUrl);
                ViseHttp.POST(NetConfig.homeTogetherListUrl)
                        .addParam("app_key", tokens)
                        .addParam("page", "1")
                        .addParam("uid", uid)
                        .addParam("city_id", cityId)
                        .request(new ACallback<String>() {
                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshlayout.finishRefresh(1000);
                            }

                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getInt("code") == 200) {
                                        HomeTogetherModel model = new Gson().fromJson(data, HomeTogetherModel.class);
                                        page = 2;
                                        mList.clear();
                                        mList.addAll(model.getObj());
                                        togetherAdapter.notifyDataSetChanged();
                                    }
                                    refreshlayout.finishRefresh(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                String tokens = getToken(NetConfig.BaseUrl + NetConfig.homeTogetherListUrl);
                ViseHttp.POST(NetConfig.homeTogetherListUrl)
                        .addParam("app_key", tokens)
                        .addParam("page", page + "")
                        .addParam("uid", uid)
                        .addParam("city_id", cityId)
                        .request(new ACallback<String>() {
                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshlayout.finishLoadMore(1000);
                            }

                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getInt("code") == 200) {
                                        HomeTogetherModel model = new Gson().fromJson(data, HomeTogetherModel.class);
                                        page = page + 1;
                                        mList.addAll(model.getObj());
                                        togetherAdapter.notifyDataSetChanged();
                                    }
                                    refreshlayout.finishLoadMore(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });

        String token = getToken(NetConfig.BaseUrl + NetConfig.homeHotFriendsRememberUrl);
        ViseHttp.POST(NetConfig.homeHotFriendsRememberUrl)
                .addParam("app_key", token)
                .addParam("uid", uid)
                .addParam("type", "1")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Log.e("222", data);
                                HomeHotFriendsRememberModel model = new Gson().fromJson(data, HomeHotFriendsRememberModel.class);
                                initList(model.getObj().getInfo());
                                initVideoList(model.getObj().getVideo());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        String tokens = getToken(NetConfig.BaseUrl + NetConfig.homeTogetherListUrl);
        ViseHttp.POST(NetConfig.homeTogetherListUrl)
                .addParam("app_key", tokens)
                .addParam("page", "1")
                .addParam("uid", uid)
                .addParam("city_id", cityId)
                .request(new ACallback<String>() {
                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }

                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                HomeTogetherModel model = new Gson().fromJson(data, HomeTogetherModel.class);
                                page = 2;
                                initTogetherList(model.getObj());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void initList(List<HomeHotFriendsRememberModel.ObjBean.InfoBean> data) {
        mList1 = data;
        LinearLayoutManager manager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        home_hotRv.setLayoutManager(manager);
        adapter = new HomeHotAdapter(mList1);
        home_hotRv.setAdapter(adapter);
        adapter.setOnFocusListener(new HomeHotAdapter.OnFocusListener() {
            @Override
            public void onFocus(final int position) {
                if (mList1.get(position).getFollow().equals("0")) {
                    ViseHttp.POST(NetConfig.userFocusUrl)
                            .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userFocusUrl))
                            .addParam("uid", uid)
                            .addParam("likeId", mList1.get(position).getUserID())
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.e("222", "123123");
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if (jsonObject.getInt("code") == 200) {
                                            mList1.get(position).setFollow("1");
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
        });
    }

    public void initVideoList(final List<HomeHotFriendsRememberModel.ObjBean.VideoBean> data) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_hotVideoRv.setLayoutManager(manager);
        videoAdapter = new VideoAdapter(data);
        home_hotVideoRv.setAdapter(videoAdapter);
        ScalableCardHelper cardHelper = new ScalableCardHelper(new ScalableCardHelper.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
//                toToast(getContext(),data.get(position).getVurl());
            }
        });
        cardHelper.attachToRecyclerView(home_hotVideoRv);
    }

    public void initTogetherList(final List<HomeTogetherModel.ObjBean> data) {
        mList = data;
        LinearLayoutManager manager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        home_hotTogetherRv.setLayoutManager(manager);
//        home_hotTogetherRv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        togetherAdapter = new HomeTogetherAdapter(mList);
        home_hotTogetherRv.setAdapter(togetherAdapter);
        togetherAdapter.setOnFocusListener(new HomeTogetherAdapter.OnFocusListener() {
            @Override
            public void onFocus(final int position) {
                if (!StringUtils.isEmpty(data.get(position).getCaptain()) && !data.get(position).getCaptain().equals("0")) {
                    ViseHttp.POST(NetConfig.focusOnLeaderUrl)
                            .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.focusOnLeaderUrl))
                            .addParam("userID", spImp.getUID())
                            .addParam("attention_userID", data.get(position).getCaptain())
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    FocusOnLeaderModel model = new Gson().fromJson(data, FocusOnLeaderModel.class);
                                    if (model.getCode() == 200) {
                                        if (model.getObj().getAttention().equals("0")) {
                                            mList.get(position).setFollow("0");
                                            togetherAdapter.notifyDataSetChanged();
                                        } else {
                                            mList.get(position).setFollow("1");
                                            togetherAdapter.notifyDataSetChanged();
                                        }
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {

                                }
                            });
                } else {
                    toToast(getContext(), "暂无领队");
                }
            }
        });
    }

    @OnClick({R.id.locationRl, R.id.searchLl, R.id.messageIv, R.id.tv_tuijian, R.id.tv_remen, R.id.tv_guanzhu})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.locationRl:
                Intent it = new Intent(getActivity(), CityActivity.class);
                CityModel model = new CityModel();
                model.setName("哈尔滨");
                model.setId("-1");
                UserUtils.saveCity(getActivity(), model);
                it.putExtra(ActivityConfig.ACTIVITY, "home");
//                it.putExtra("model",model);
                startActivityForResult(it, 1);
                break;
            case R.id.searchLl:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.messageIv:
//                home_numTv.setVisibility(View.INVISIBLE);
                getActivity().startActivity(new Intent(getActivity(), MessageCenterActivity.class));
                break;
            case R.id.tv_tuijian:
                tvTuijian.setTextColor(Color.parseColor("#ff9d00"));
                tvRemen.setTextColor(Color.parseColor("#333333"));
                tvGuanzhu.setTextColor(Color.parseColor("#333333"));
                ViseHttp.POST(NetConfig.homeHotFriendsRememberUrl)
                        .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.homeHotFriendsRememberUrl))
                        .addParam("uid", uid)
                        .addParam("type", "1")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getInt("code") == 200) {
                                        Log.e("222", data);
                                        HomeHotFriendsRememberModel model = new Gson().fromJson(data, HomeHotFriendsRememberModel.class);
                                        mList1.clear();
                                        mList1.addAll(model.getObj().getInfo());
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
            case R.id.tv_remen:
                tvTuijian.setTextColor(Color.parseColor("#333333"));
                tvRemen.setTextColor(Color.parseColor("#ff9d00"));
                tvGuanzhu.setTextColor(Color.parseColor("#333333"));
                ViseHttp.POST(NetConfig.homeHotFriendsRememberUrl)
                        .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.homeHotFriendsRememberUrl))
                        .addParam("uid", uid)
                        .addParam("type", "2")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getInt("code") == 200) {
                                        Log.e("222", data);
                                        HomeHotFriendsRememberModel model = new Gson().fromJson(data, HomeHotFriendsRememberModel.class);
                                        mList1.clear();
                                        mList1.addAll(model.getObj().getInfo());
                                        adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
            case R.id.tv_guanzhu:
                if (TextUtils.isEmpty(uid) || uid.equals("0")) {
                    Intent intent1 = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent1);
                } else {
                    tvTuijian.setTextColor(Color.parseColor("#333333"));
                    tvRemen.setTextColor(Color.parseColor("#333333"));
                    tvGuanzhu.setTextColor(Color.parseColor("#ff9d00"));
                    ViseHttp.POST(NetConfig.homeHotFriendsRememberUrl)
                            .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.homeHotFriendsRememberUrl))
                            .addParam("uid", uid)
                            .addParam("type", "3")
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.e("222", data);
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if (jsonObject.getInt("code") == 200) {
                                            HomeHotFriendsRememberModel model = new Gson().fromJson(data, HomeHotFriendsRememberModel.class);
                                            mList1.clear();
                                            mList1.addAll(model.getObj().getInfo());
                                            adapter.notifyDataSetChanged();
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
                break;
        }
    }

    public void getLocation() {
        Log.i("查找城市", "哈尔滨");
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        new Thread() {
            @Override
            public void run() {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Log.i("请求权限", "请求了");
                    return;
                }
                @SuppressLint("MissingPermission") Location location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude(); // 经度
                    longitude = location.getLongitude(); // 纬度
                    handler.sendEmptyMessage(1);
                }
            }
        }.start();
    }

    public void getCity() {
        try {
            // 去谷歌的地理位置获取中去解析经纬度对应的地理位置
            String url = "http://maps.google.cn/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=true&language=zh-CN";

//            String url = "http://maps.google.cn/maps/api/geocode/json?latlng=" + "46.2122837132" + "," + "128.4605692798" + "&sensor=true&language=zh-CN";
            ViseHttp.GET(url)
                    .addHeader("Accept-Language", "zh-CN")
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                JSONArray resultArray = jsonObject
                                        .getJSONArray("results");
//                                if (resultArray.length() > 0) {
//                                    JSONObject subObject = resultArray.getJSONObject(1);
//                                    String address = subObject
//                                            .getString("formatted_address");
//                                    latLongString = address;
//                                    Log.i("所在城市", latLongString);
//                                    handler.sendEmptyMessage(2);
//                                }
                                Gson gson = new Gson();
                                GoogleCityModel model = gson.fromJson(data, GoogleCityModel.class);
                                latLongString = model.getResults().get(0).getAddress_components().get(3).getLong_name();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
//            OkHttpUtils.get()
//                    .tag(this)
//                    .url(url)
//                    .addHeader("Accept-Language", "zh-CN")
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Request request, Exception e) {
//
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                JSONArray resultArray = jsonObject
//                                        .getJSONArray("results");
//                                if (resultArray.length() > 0) {
//                                    JSONObject subObject = resultArray.getJSONObject(1);
//                                    String address = subObject
//                                            .getString("formatted_address");
//                                    latLongString = address;
//                                    Log.i("所在城市", latLongString);
//                                    handler.sendEmptyMessage(2);
//                                }
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null && resultCode == 1) {
            CityModel model = (CityModel) data.getSerializableExtra(ActivityConfig.CITY);
            cityTv.setText(model.getName());
            cityId = model.getId();
            String tokens = getToken(NetConfig.BaseUrl + NetConfig.homeTogetherListUrl);
            ViseHttp.POST(NetConfig.homeTogetherListUrl)
                    .addParam("app_key", tokens)
                    .addParam("page", "1")
                    .addParam("uid", uid)
                    .addParam("city_id", cityId)
                    .request(new ACallback<String>() {
                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }

                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.getInt("code") == 200) {
                                    HomeTogetherModel model = new Gson().fromJson(data, HomeTogetherModel.class);
                                    page = 2;
                                    initTogetherList(model.getObj());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else if (requestCode == 1 && resultCode == 2) {
            cityId = "";
            cityTv.setText(latLongString);
            String tokens = getToken(NetConfig.BaseUrl + NetConfig.homeTogetherListUrl);
            ViseHttp.POST(NetConfig.homeTogetherListUrl)
                    .addParam("app_key", tokens)
                    .addParam("page", "1")
                    .addParam("uid", uid)
                    .addParam("city_id", cityId)
                    .request(new ACallback<String>() {
                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }

                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.getInt("code") == 200) {
                                    HomeTogetherModel model = new Gson().fromJson(data, HomeTogetherModel.class);
                                    page = 2;
                                    initTogetherList(model.getObj());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }
}
