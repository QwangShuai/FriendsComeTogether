package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.custom.CImageView;
import com.yiwo.friendscometogether.sp.SpImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 */
public class SetActivity extends BaseActivity {
    SpImp spImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);
        spImp =new SpImp(SetActivity.this);
    }

    @OnClick({R.id.activity_set_rl_back,R.id.activity_set_rl_upload,R.id.activity_set_rl_feedback,
            R.id.activity_set_rl_clear_cache,R.id.activity_set_rl_user_agreement,R.id.activity_set_exit_login_bt})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.activity_set_rl_back:
                finish();
                break;
            case R.id.activity_set_rl_upload:
                toToast(this,"检查更新");
                break;
            case R.id.activity_set_rl_feedback:
                toToast(this,"意见反馈");
                break;
            case R.id.activity_set_rl_clear_cache:
                toToast(this,"清除缓存");
                break;
            case R.id.activity_set_rl_user_agreement:
                toToast(this,"用户协议");
                break;
            case R.id.activity_set_exit_login_bt:
                toToast(this,"退出登录");
                break;
        }
    }
}
