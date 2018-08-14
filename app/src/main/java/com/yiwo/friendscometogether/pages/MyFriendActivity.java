package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFriendActivity extends AppCompatActivity {

    @BindView(R.id.activity_my_friend_rl_back)
    RelativeLayout rlBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(MyFriendActivity.this);

    }

    @OnClick({R.id.activity_my_friend_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_friend_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyFriendActivity.this.finish();
    }
}
