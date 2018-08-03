package com.yiwo.friendscometogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.FragmentToPayAdapter;
import com.yiwo.friendscometogether.adapter.FragmentToTripAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.base.OrderBaseFragment;
import com.yiwo.friendscometogether.model.PayFragmentModel;
import com.yiwo.friendscometogether.model.TripFragmentModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.TokenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/18.
 */

public class ToTripFragment extends OrderBaseFragment {

    @BindView(R.id.fragment_to_trip_rv)
    RecyclerView recyclerView;

    private FragmentToTripAdapter adapter;
    private List<TripFragmentModel.ObjBean> mList;

    private SpImp spImp;
    private String uid = "";

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_to_trip, null);
        ScreenAdapterTools.getInstance().loadView(view);

        ButterKnife.bind(this, view);
        spImp = new SpImp(getContext());

        return view;
    }

    @Override
    public void initData() {
        initData1();
    }

    private void initData1() {

        uid = spImp.getUID();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        ViseHttp.POST(NetConfig.myOrderListUrl)
                .addParam("app_key", TokenUtils.getToken(NetConfig.BaseUrl+NetConfig.myOrderListUrl))
                .addParam("page", "1")
                .addParam("userID", uid)
                .addParam("type", "2")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                TripFragmentModel model = gson.fromJson(data, TripFragmentModel.class);
                                mList = model.getObj();
                                adapter = new FragmentToTripAdapter(mList);
                                recyclerView.setAdapter(adapter);
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
