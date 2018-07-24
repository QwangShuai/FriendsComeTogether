package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MyPicturesAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPicturesActivity extends BaseActivity {

    @BindView(R.id.activity_my_pictures_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_my_pictures_rv)
    RecyclerView recyclerView;

    private MyPicturesAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pictures);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {

        data = new ArrayList<>();

        GridLayoutManager manager = new GridLayoutManager(MyPicturesActivity.this, 2);
        recyclerView.setLayoutManager(manager);

        adapter = new MyPicturesAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.activity_my_pictures_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_pictures_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyPicturesActivity.this.finish();
    }
}
