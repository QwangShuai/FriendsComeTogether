package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.DetailsOfFriendsUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsOfFriendsActivity extends BaseActivity {

    @BindView(R.id.activity_details_of_friends_iv_title)
    ImageView ivTitle;
    @BindView(R.id.activity_details_of_friends_rv)
    RecyclerView recyclerView;

    private DetailsOfFriendsUpDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_friends);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        init();

    }

    private void init() {

        Picasso.with(DetailsOfFriendsActivity.this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739806900&di=5851898465493d1819030712458cee88&imgtype=0&src=http%3A%2F%2Fwww.5636.com%2Fnetbar%2Fuploads%2Fallimg%2F120620%2F21-120620102101526.jpg").into(ivTitle);

        LinearLayoutManager manager = new LinearLayoutManager(DetailsOfFriendsActivity.this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        List<String> data = new ArrayList<>();
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739806900&di=5851898465493d1819030712458cee88&imgtype=0&src=http%3A%2F%2Fwww.5636.com%2Fnetbar%2Fuploads%2Fallimg%2F120620%2F21-120620102101526.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739807163&di=f3875854f37cf9d8f5261998f229bd03&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2Fday_100825%2F10082513558ebc5978899bb24c.jpg");
        data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739807163&di=3be9c2032fcb53a8764c5d5a1409c58a&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2F201612%2F12%2F22290422z010jrivvoloid.jpg");
        adapter = new DetailsOfFriendsUpDataAdapter(data);
        recyclerView.setAdapter(adapter);

    }
}
