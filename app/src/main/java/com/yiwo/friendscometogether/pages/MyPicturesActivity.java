package com.yiwo.friendscometogether.pages;

import android.os.Bundle;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;

import butterknife.ButterKnife;

public class MyPicturesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pictures);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyPicturesActivity.this.finish();
    }
}
