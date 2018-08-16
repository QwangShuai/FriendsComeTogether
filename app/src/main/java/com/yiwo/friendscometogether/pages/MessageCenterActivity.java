package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.sp.SpImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息中心
 */
public class MessageCenterActivity extends AppCompatActivity {
    @BindView(R.id.activity_message_center_rl_back)
    RelativeLayout backRl;
    @BindView(R.id.headIv)
    ImageView headIv;
    @BindView(R.id.set_headIv)
    ImageView set_headIv;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.contentTv)
    TextView contentTv;
    @BindView(R.id.numTv)
    TextView numTv;
    @BindView(R.id.set_titleTv)
    TextView set_titleTv;
    @BindView(R.id.set_contentTv)
    TextView set_contentTv;
    @BindView(R.id.set_numTv)
    TextView set_numTv;
    @BindView(R.id.hot_message_rl)
    RelativeLayout hotRl;
    @BindView(R.id.set_message_rl)
    RelativeLayout setRl;
    SpImp spImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);
        spImp =new SpImp(MessageCenterActivity.this);
    }

    @OnClick({R.id.hot_message_rl,R.id.set_message_rl,R.id.activity_message_center_rl_back})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.activity_message_center_rl_back:
                finish();
                break;
            case R.id.hot_message_rl:
                numTv.setVisibility(View.INVISIBLE);
                Intent it = new Intent(MessageCenterActivity.this,MessageViewActivity.class);
                it.putExtra("type","0");
                startActivity(it);
                break;
            case R.id.set_message_rl:
                set_numTv.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MessageCenterActivity.this,MessageViewActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
        }
    }
}
