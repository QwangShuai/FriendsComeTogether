package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MessageViewAdapter;
import com.yiwo.friendscometogether.model.MessageViewModel;
import com.yiwo.friendscometogether.sp.SpImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息中心查看
 */
public class MessageViewActivity extends AppCompatActivity {
    @BindView(R.id.activity_message_view_rl_back)
    RelativeLayout backRl;
    @BindView(R.id.activity_message_view_rv)
    RecyclerView messageRv;

    private SpImp spImp;
    private MessageViewAdapter adapter;
    List<MessageViewModel.ObjBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);
        spImp = new SpImp(MessageViewActivity.this);
    }

    public void initData(){
        String type = getIntent().getStringExtra("type");
        
    }

    public void initList(List<MessageViewModel.ObjBean> data) {
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        messageRv.setLayoutManager(manager);
        adapter = new MessageViewAdapter(data);
        messageRv.setAdapter(adapter);
    }
}
