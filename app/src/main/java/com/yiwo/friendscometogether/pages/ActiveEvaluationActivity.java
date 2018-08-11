package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MyActiveCommentAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.ActiveCommentModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveEvaluationActivity extends BaseActivity {
    @BindView(R.id.activity__active_evaluation_rv)
    RecyclerView rv;
    private SpImp spImp;
    MyActiveCommentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_evaluation);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(this);

        initData();
    }

    private void initData(){
        ViseHttp.POST(NetConfig.activeEvaluationListUrl)
                .addParam("app_key",getToken(NetConfig.BaseUrl+NetConfig.activeEvaluationListUrl))
                .addParam("page","1")
                .addParam("userID",spImp.getUID())
                .addParam("pfID",getIntent().getStringExtra("pfID"))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("1010101",data);
                        ActiveCommentModel model = new Gson().fromJson(data,ActiveCommentModel.class);
                        if (model.getCode()==200){
                            initList(model.getObj());
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }
    private void initList(List<ActiveCommentModel.ObjBean> data) {

        LinearLayoutManager manager = new LinearLayoutManager(ActiveEvaluationActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        adapter = new MyActiveCommentAdapter(data);
        rv.setAdapter(adapter);

    }
}
