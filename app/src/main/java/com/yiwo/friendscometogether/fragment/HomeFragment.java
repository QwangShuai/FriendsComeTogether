package com.yiwo.friendscometogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
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
import com.yiwo.friendscometogether.pages.CityActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

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
    @BindView(R.id.home_hotRv)
    RecyclerView home_hotRv;
    @BindView(R.id.locationRl)
    RelativeLayout locationRl;
    private HomeHotAdapter adapter;
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
        rootView = inflater.inflate(R.layout.fragment_home,null);
        ButterKnife.bind(this, rootView);
        ScreenAdapterTools.getInstance().loadView(rootView);
        init(banner,DetailsOfFriendsActivity.class);
        HomeHotFriendsRememberModel model = new Gson().fromJson(json,HomeHotFriendsRememberModel.class);
        initList(model.getObj());
//        initData();
        return rootView;
    }
    public void initData(){

        String token = getToken(NetConfig.BaseUrl+NetConfig.homeHotFriendsRememberUrl);
        ViseHttp.POST(NetConfig.homeHotFriendsRememberUrl)
                .addParam("app_key",token)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                        HomeHotFriendsRememberModel model = new Gson().fromJson(data,HomeHotFriendsRememberModel.class);
                        initList(model.getObj());
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    public void initList(List<HomeHotFriendsRememberModel.ObjBean> data){
        LinearLayoutManager manager = new LinearLayoutManager(getContext()){
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
    @OnClick({R.id.locationRl})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.locationRl:
                getActivity().startActivity(new Intent(getActivity(), CityActivity.class));
                break;
        }
    }
}
