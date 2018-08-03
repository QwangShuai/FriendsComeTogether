package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息中心详情
 */
public class MessageCenterDetailsActivity extends AppCompatActivity {
    @BindView(R.id.activity_message_center_details_title_tv)
    TextView titleTv;
    @BindView(R.id.activity_message_center_details_pic_iv)
    ImageView picIv;
    @BindView(R.id.activity_message_center_details_content_tv)
    TextView contentTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center_details);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);
    }

    @OnClick({R.id.activity_message_center_details_rl_back})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.activity_message_center_details_rl_back:
                finish();
                break;
        }
    }
}
