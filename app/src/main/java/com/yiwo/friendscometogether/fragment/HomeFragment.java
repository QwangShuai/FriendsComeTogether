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
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.custom.GlideImageLoader;
import com.yiwo.friendscometogether.model.HomeHotFriendsRememberModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.ApplyActivity;
import com.yiwo.friendscometogether.pages.CityActivity;
import com.yiwo.friendscometogether.pages.CreateFriendTogetherActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.yiwo.friendscometogether.pages.SearchActivity;
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

/**
 * Created by Administrator on 2018/7/16.
 */

public class HomeFragment extends BaseFragment {
    View rootView;
    @BindView(R.id.fragment_home_banner)
    Banner banner;
    @BindView(R.id.home_hotRv)
    RecyclerView home_hotRv;
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

    String json = " {\n" +
            "    \"code\": 200,\n" +
            "    \"message\": \"获取成功!\",\n" +
            "    \"obj\": [\n" +
            "        {\n" +
            "            \"type\": 3,\n" +
            "            \"userphone\": \"110\",\n" +
            "            \"fmhot\": \"1\",\n" +
            "            \"fmID\": \"1\",\n" +
            "            \"fmtitle\": \"尼玛\",\n" +
            "            \"fmcontent\": \"jsp吃屎了\",\n" +
            "            \"upicurl\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532086833238&di=0e33145bf798ca42e099fe9f4c04b563&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ffaf2b2119313b07ea96992d900d7912396dd8c0f.jpg\",\n" +
            "            \"fmpic\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532086833238&di=0e33145bf798ca42e099fe9f4c04b563&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ffaf2b2119313b07ea96992d900d7912396dd8c0f.jpg\",\n" +
            "            \"fmtime\": \"2018-05-09\",\n" +
            "            \"fmlook\": \"1234\",\n" +
            "            \"fmcomment\": \"1234\",\n" +
            "            \"username\": \"EMMMMM,,,,\",\n" +
            "            \"video\": [\n" +
            "                {\n" +
            "                    \"vname\": \"哈1\",\n" +
            "                    \"vurl\": \"www.youku.com\",\n" +
            "                    \"img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532086833238&di=0e33145bf798ca42e099fe9f4c04b563&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ffaf2b2119313b07ea96992d900d7912396dd8c0f.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"vname\": \"哈2\",\n" +
            "                    \"vurl\": \"www.youku.com\",\n" +
            "                    \"img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532086833238&di=0e33145bf798ca42e099fe9f4c04b563&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ffaf2b2119313b07ea96992d900d7912396dd8c0f.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"vname\": \"ha3\",\n" +
            "                    \"vurl\": \"www.youku.com\",\n" +
            "                    \"img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532086833238&di=0e33145bf798ca42e099fe9f4c04b563&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ffaf2b2119313b07ea96992d900d7912396dd8c0f.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"vname\": \"ha4\",\n" +
            "                    \"vurl\": \"www.youku.com\",\n" +
            "                    \"img\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532086833238&di=0e33145bf798ca42e099fe9f4c04b563&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ffaf2b2119313b07ea96992d900d7912396dd8c0f.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, rootView);
        ScreenAdapterTools.getInstance().loadView(rootView);
        getLocation();
        init(banner, DetailsOfFriendsActivity.class);
        HomeHotFriendsRememberModel model = new Gson().fromJson(json, HomeHotFriendsRememberModel.class);
        initList(model.getObj());
//        initData();
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
                        initList(model.getObj());
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    public void initList(List<HomeHotFriendsRememberModel.ObjBean> data) {
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

    @OnClick({R.id.locationRl,R.id.searchLl})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.locationRl:
                getActivity().startActivity(new Intent(getActivity(), CityActivity.class));
                break;
            case R.id.searchLl:
//                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
                getActivity().startActivity(new Intent(getActivity(), ApplyActivity.class));
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

        ;
//        List<Address> addList = null;
//        Geocoder ge = new Geocoder(getActivity(), Locale.getDefault());
//        try {
//            Log.i("位置-","纬度："+latitude+"经度："+longitude);
//            addList =ge.getFromLocation(latitude, longitude, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (addList != null && addList.size() > 0) {
//            for (int i = 0; i < addList.size(); i++) {
//                Address ad = addList.get(i);
//                latLongString = ad.getLocality();
//            }
//            Log.i("长度",addList.size()+"???");
//            handler.sendEmptyMessage(2);
//        }
    }
}
