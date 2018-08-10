package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import butterknife.ButterKnife;

public class ActiveEvaluationActivity extends BaseActivity {
    private SpImp spImp;
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
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }
}
