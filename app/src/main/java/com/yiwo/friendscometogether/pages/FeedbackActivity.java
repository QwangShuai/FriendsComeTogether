package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.sp.SpImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity {
    SpImp spImp;
    @BindView(R.id.activity_feedback_et_content)
    EditText contentEt;
    @BindView(R.id.activity_feedback_bt_submit)
    Button submitBt;
    @BindView(R.id.activity_feedback_rv)
    RecyclerView feedbackRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);
        spImp =new SpImp(this);
    }

    @OnClick({R.id.activity_feedback_rl_back})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.activity_feedback_rl_back:
                finish();
                break;
            case R.id.activity_feedback_bt_submit:
                toToast(this,"提交");
                break;
        }
    }
}
