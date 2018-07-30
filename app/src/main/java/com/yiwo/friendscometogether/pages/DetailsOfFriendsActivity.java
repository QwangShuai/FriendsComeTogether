package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.graphics.Color;
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
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.DetailsOfFriendsUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.utils.ShareUtils;

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

    private boolean isFocus = false;
    private boolean isPraise = false;
    private boolean isStar = false;

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

    @OnClick({R.id.activity_details_of_friends_rl_back, R.id.activity_details_of_friends_ll_intercalation, R.id.activity_details_of_friends_ll_comment,
            R.id.activity_details_of_friends_ll_share, R.id.activity_details_of_friends_ll_focus, R.id.activity_details_of_friends_ll_praise, R.id.activity_details_of_friends_ll_star})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.activity_details_of_friends_rl_back:
                onBackPressed();
                break;
            case R.id.activity_details_of_friends_ll_intercalation:
                intent.setClass(DetailsOfFriendsActivity.this, InsertIntercalationActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_details_of_friends_ll_comment:
                intent.setClass(DetailsOfFriendsActivity.this, MyCommentActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_details_of_friends_ll_share:
                new ShareAction(this).setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                ShareUtils.shareWeb(DetailsOfFriendsActivity.this,"http://www.baidu.com","不快乐",
                                        "就是不快乐","",share_media);
                            }
                        }).open();
                break;
            case R.id.activity_details_of_friends_ll_focus:
                if(!isFocus){
                    Picasso.with(DetailsOfFriendsActivity.this).load(R.mipmap.focus_on_y).into(ivFocus);
                    tvFocus.setTextColor(Color.parseColor("#FF9D00"));
                    isFocus = !isFocus;
                }else {
                    Picasso.with(DetailsOfFriendsActivity.this).load(R.mipmap.details_focus_on_b).into(ivFocus);
                    tvFocus.setTextColor(Color.parseColor("#333333"));
                    isFocus = !isFocus;
                }
                break;
            case R.id.activity_details_of_friends_ll_praise:
                if(!isPraise){
                    Picasso.with(DetailsOfFriendsActivity.this).load(R.mipmap.praise_y).into(ivPraise);
                    tvPraise.setTextColor(Color.parseColor("#FF9D00"));
                    isPraise = !isPraise;
                }else {
                    Picasso.with(DetailsOfFriendsActivity.this).load(R.mipmap.details_praise_b).into(ivPraise);
                    tvPraise.setTextColor(Color.parseColor("#333333"));
                    isPraise = !isPraise;
                }
                break;
            case R.id.activity_details_of_friends_ll_star:
                if(!isStar){
                    Picasso.with(DetailsOfFriendsActivity.this).load(R.mipmap.star_y).into(ivStar);
                    tvStar.setTextColor(Color.parseColor("#FF9D00"));
                    isStar = !isStar;
                }else {
                    Picasso.with(DetailsOfFriendsActivity.this).load(R.mipmap.details_star_b).into(ivStar);
                    tvStar.setTextColor(Color.parseColor("#333333"));
                    isStar = !isStar;
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DetailsOfFriendsActivity.this.finish();
    }
}
