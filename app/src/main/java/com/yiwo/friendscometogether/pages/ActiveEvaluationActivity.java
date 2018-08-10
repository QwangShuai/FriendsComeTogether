package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vise.xsnow.http.ViseHttp;
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
    }

    private void initData(){
//        ViseHttp.POST(NetConfig.activeEvaluationListUrl)
//                .addParam("app_key",getToken(NetConfig.BaseUrl+NetConfig.activeEvaluationListUrl))
//                .addParam("page","1")
//                .addParam()
    }
}
