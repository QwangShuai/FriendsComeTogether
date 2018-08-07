package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.JoinActiveAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.JoinActivemodel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinActiveActivity extends BaseActivity {

    @BindView(R.id.activity_join_active_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_join_active_rv)
    RecyclerView recyclerView;

    private JoinActiveAdapter adapter;
    private SpImp spImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_active);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(this);
        initData();

    }
    private void initData(){
        ViseHttp.POST(NetConfig.activitiesAttendedUrl)
                .addParam("app_key",getToken(NetConfig.BaseUrl+NetConfig.activitiesAttendedUrl))
                .addParam("page","1")
                .addParam("user_id",spImp.getUID())
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.i("147852",data);
                        JoinActivemodel model = new Gson().fromJson(data,JoinActivemodel.class);
                        if(model.getCode()==200){
                            initList(model.getObj());
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
    }
    private void initList(List<JoinActivemodel.ObjBean> data) {

        LinearLayoutManager manager = new LinearLayoutManager(JoinActiveActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new JoinActiveAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.activity_join_active_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_join_active_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        JoinActiveActivity.this.finish();
    }
}
