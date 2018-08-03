package com.yiwo.friendscometogether.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.FriendRememberUpDataAdapter;
import com.yiwo.friendscometogether.adapter.HomeHotAdapter;
import com.yiwo.friendscometogether.adapter.HomeTogetherAdapter;
import com.yiwo.friendscometogether.adapter.VideoAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.custom.GlideImageLoader;
import com.yiwo.friendscometogether.custom.ScalableCardHelper;
import com.yiwo.friendscometogether.model.CityModel;
import com.yiwo.friendscometogether.model.HomeHotFriendsRememberModel;
import com.yiwo.friendscometogether.model.HomeTogetherModel;
import com.yiwo.friendscometogether.network.ActivityConfig;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.ApplyActivity;
import com.yiwo.friendscometogether.pages.CityActivity;
import com.yiwo.friendscometogether.pages.CreateFriendTogetherActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.yiwo.friendscometogether.pages.SearchActivity;
import com.yiwo.friendscometogether.utils.TokenUtils;
import com.yiwo.friendscometogether.utils.UserUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;

/**
 * Created by Administrator on 2018/7/16.
 */

public class HomeFragment extends BaseFragment {
    View rootView;
    @BindView(R.id.fragment_home_banner)
    Banner banner;
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
    @BindView(R.id.searchLl)
    LinearLayout searchLl;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, rootView);
        ScreenAdapterTools.getInstance().loadView(rootView);
        getLocation();
        init(banner, DetailsOfFriendsActivity.class);
        initData();
        return rootView;
    }

    public void initData() {

        String token = getToken(NetConfig.BaseUrl + NetConfig.homeHotFriendsRememberUrl);
        ViseHttp.POST(NetConfig.homeHotFriendsRememberUrl)
                .addParam("app_key", token)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                        HomeHotFriendsRememberModel model = new Gson().fromJson(data, HomeHotFriendsRememberModel.class);
                        initList(model.getObj().getInfo());
                        initVideoList(model.getObj().getVideo());
                    }


                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        String tokens = getToken(NetConfig.BaseUrl + NetConfig.homeTogetherListUrl);
        ViseHttp.POST(NetConfig.homeTogetherListUrl)
                .addParam("app_key",tokens)
                .addParam("page","1")
                .request(new ACallback<String>() {
                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }

                    @Override
                    public void onSuccess(String data) {
                        HomeTogetherModel model = new Gson().fromJson(data,HomeTogetherModel.class);
                        initTogetherList(model.getObj());
                    }
                });
    }

    public void initList(List<HomeHotFriendsRememberModel.ObjBean.InfoBean> data) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        home_hotRv.setLayoutManager(manager);
        adapter = new HomeHotAdapter(data);
        home_hotRv.setAdapter(adapter);
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
                toToast(getContext(),data.get(position).getVurl());
            }
        });
        cardHelper.attachToRecyclerView(home_hotVideoRv);
    }

    public void initTogetherList(List<HomeTogetherModel.ObjBean> data){
        LinearLayoutManager manager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        home_hotTogetherRv.setLayoutManager(manager);
//        home_hotTogetherRv.addItemDecoration();
        togetherAdapter = new HomeTogetherAdapter(data);
        home_hotTogetherRv.setAdapter(togetherAdapter);
    }
    @OnClick({R.id.locationRl,R.id.searchLl})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.locationRl:
                Intent it = new Intent(getActivity(), CityActivity.class);
                CityModel model = new CityModel();
                model.setName("哈尔滨");
                model.setId("-1");
                UserUtils.saveCity(getActivity(),model);
                it.putExtra(ActivityConfig.ACTIVITY,"home");
//                it.putExtra("model",model);
                startActivityForResult(it,1);
                break;
            case R.id.searchLl:
                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
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
                    Log.i("请求权限","请求了");
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
            String url = "http://maps.google.cn/maps/api/geocode/json?latlng="+latitude+","+longitude+"&sensor=true&language=zh-CN";
            OkHttpUtils.get()
                    .tag(this)
                    .url(url)
                    .addHeader("Accept-Language", "zh-CN")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray resultArray = jsonObject
                                        .getJSONArray("results");
                                if (resultArray.length() > 0) {
                                    JSONObject subObject = resultArray.getJSONObject(1);
                                    String address = subObject
                                            .getString("formatted_address");
                                    latLongString = address;
                                    Log.i("所在城市",latLongString);
                                    handler.sendEmptyMessage(2);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&data!=null){
            CityModel model = (CityModel) data.getSerializableExtra(ActivityConfig.CITY);
            cityTv.setText(model.getName());
        }
    }
}
