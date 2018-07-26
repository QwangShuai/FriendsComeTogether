package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MyFriendRememberAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.UserRememberModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFriendRememberActivity extends BaseActivity {

    @BindView(R.id.activity_my_friend_remember_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_my_friend_remember_rv)
    RecyclerView recyclerView;

    private MyFriendRememberAdapter adapter;

    private SpImp spImp;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend_remember);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(MyFriendRememberActivity.this);

        initData();

    }

    private void initData() {

        uid = spImp.getUID();
        LinearLayoutManager manager = new LinearLayoutManager(MyFriendRememberActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        ViseHttp.POST(NetConfig.userRemember)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.userRemember))
                .addParam("uid", uid)
                .addParam("type", "0")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                UserRememberModel userRememberModel = gson.fromJson(data, UserRememberModel.class);
                                adapter = new MyFriendRememberAdapter(userRememberModel.getObj());
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

    @OnClick({R.id.activity_my_friend_remember_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_friend_remember_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyFriendRememberActivity.this.finish();
    }
}
