package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherInformationActivity extends BaseActivity {

    @BindView(R.id.activity_other_information_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_other_information_tv_send_message)
    TextView tvSendMessage;
    @BindView(R.id.activity_other_information_iv_add_friend)
    ImageView ivAddFriend;
    @BindView(R.id.activity_other_information_iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.activity_other_information_tv_nickname)
    TextView tvNickname;
    @BindView(R.id.activity_other_information_tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.activity_other_information_ll_mypics)
    LinearLayout llMyPic;
    @BindView(R.id.activity_other_information_tv_sign)
    TextView tvSign;
    @BindView(R.id.activity_other_information_tv_age)
    TextView tvAge;
    @BindView(R.id.activity_other_information_tv_city)
    TextView tvCity;
    @BindView(R.id.activity_other_information_tv_constellation)
    TextView tvConstellation;
    @BindView(R.id.activity_other_information_tv_focus_num)
    TextView tvFocusNum;
    @BindView(R.id.activity_other_information_tv_fans_num)
    TextView tvFansNum;
    @BindView(R.id.activity_other_information_tv_praise_num)
    TextView tvPraiseNum;
    @BindView(R.id.activity_other_information_tv_works)
    TextView tvWorks;
    @BindView(R.id.activity_other_information_tv_active)
    TextView tvActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_information);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.activity_other_information_rl_back, R.id.activity_other_information_tv_works, R.id.activity_other_information_tv_active, R.id.activity_other_information_ll_mypics})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.activity_other_information_rl_back:
                onBackPressed();
                break;
            case R.id.activity_other_information_tv_works:
                tvWorks.setBackgroundColor(Color.parseColor("#FF9D00"));
                tvActive.setBackgroundColor(Color.parseColor("#000000"));
                break;
            case R.id.activity_other_information_tv_active:
                tvWorks.setBackgroundColor(Color.parseColor("#000000"));
                tvActive.setBackgroundColor(Color.parseColor("#FF9D00"));
                break;
            case R.id.activity_other_information_ll_mypics:
                intent.setClass(OtherInformationActivity.this, MyPicturesActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OtherInformationActivity.this.finish();
    }
}
