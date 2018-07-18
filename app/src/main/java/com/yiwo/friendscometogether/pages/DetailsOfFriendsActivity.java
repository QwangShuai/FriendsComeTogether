package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.DetailsOfFriendsUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.network.NetConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsOfFriendsActivity extends BaseActivity {

    @BindView(R.id.activity_details_of_friends_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_details_of_friends_iv_title)
    ImageView ivTitle;
    @BindView(R.id.activity_details_of_friends_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_details_of_friends_ll_intercalation)
    LinearLayout llIntercalation;
    @BindView(R.id.activity_details_of_friends_ll_comment)
    LinearLayout llComment;
    @BindView(R.id.activity_details_of_friends_ll_share)
    LinearLayout llShare;
    @BindView(R.id.activity_details_of_friends_ll_focus)
    LinearLayout llFocus;
    @BindView(R.id.activity_details_of_friends_iv_focus)
    ImageView ivFocus;
    @BindView(R.id.activity_details_of_friends_tv_focus)
    TextView tvFocus;
    @BindView(R.id.activity_details_of_friends_ll_praise)
    LinearLayout llPraise;
    @BindView(R.id.activity_details_of_friends_iv_praise)
    ImageView ivPraise;
    @BindView(R.id.activity_details_of_friends_tv_praise)
    TextView tvPraise;
    @BindView(R.id.activity_details_of_friends_ll_star)
    LinearLayout llStar;
    @BindView(R.id.activity_details_of_friends_iv_star)
    ImageView ivStar;
    @BindView(R.id.activity_details_of_friends_tv_star)
    TextView tvStar;

    private DetailsOfFriendsUpDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_friends);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        String fmid = intent.getStringExtra("fmid");

        ViseHttp.POST(NetConfig.articleContentUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.articleContentUrl))
                .addParam("id", fmid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        Picasso.with(DetailsOfFriendsActivity.this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531739806900&di=5851898465493d1819030712458cee88&imgtype=0&src=http%3A%2F%2Fwww.5636.com%2Fnetbar%2Fuploads%2Fallimg%2F120620%2F21-120620102101526.jpg").into(ivTitle);

        LinearLayoutManager manager = new LinearLayoutManager(DetailsOfFriendsActivity.this) {
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

    @OnClick({R.id.activity_details_of_friends_rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_details_of_friends_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DetailsOfFriendsActivity.this.finish();
    }
}
