package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.JoinActiveAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinActiveActivity extends BaseActivity {

    @BindView(R.id.activity_join_active_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_join_active_rv)
    RecyclerView recyclerView;

    private JoinActiveAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_active);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {

        LinearLayoutManager manager = new LinearLayoutManager(JoinActiveActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        adapter = new JoinActiveAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.activity_join_active_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_join_active_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        JoinActiveActivity.this.finish();
    }
}
