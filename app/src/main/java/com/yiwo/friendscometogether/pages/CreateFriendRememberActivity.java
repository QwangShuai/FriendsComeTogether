package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateFriendRememberActivity extends BaseActivity {

    @BindView(R.id.activity_create_friend_remember_rl_back)
    RelativeLayout rlBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_friend_remember);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        init();

    }

    private void init() {



    }

    @OnClick({R.id.activity_create_friend_remember_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_create_friend_remember_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CreateFriendRememberActivity.this.finish();
    }
}
