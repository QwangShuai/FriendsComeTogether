package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
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
import com.yiwo.friendscometogether.adapter.DetailsOfFriendsTogetherAdapter;
import com.yiwo.friendscometogether.adapter.FriendTogetherUpDataAdapter;
import com.yiwo.friendscometogether.adapter.ParticipantsItemAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.FriendsTogetherDetailsModel;
import com.yiwo.friendscometogether.model.FriendsTogethermodel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.ShareUtils;
import com.yiwo.friendscometogether.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetailsOfFriendTogetherActivity extends BaseActivity {
    @BindView(R.id.activity_details_of_friends_together_iv_title)
    ImageView titleIv;
    @BindView(R.id.headIv)
    ImageView headIv;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.viewsTv)
    TextView viewsTv;
    @BindView(R.id.levelTv)
    TextView levelTv;
    @BindView(R.id.time_start_tv)
    TextView time_start_tv;
    @BindView(R.id.time_end_tv)
    TextView time_end_tv;
    @BindView(R.id.focus_onTv)
    TextView focus_onTv;
    @BindView(R.id.city_tv)
    TextView city_tv;
    @BindView(R.id.priceTv)
    TextView priceTv;
    @BindView(R.id.womanTv)
    TextView womanTv;
    @BindView(R.id.manTv)
    TextView manTv;
    @BindView(R.id.participantsTv)
    TextView participantsTv;
    @BindView(R.id.item_levelBg)
    RelativeLayout item_levelBg;
    @BindView(R.id.details_friend_together_rv)
    RecyclerView recyclerViewP;
    @BindView(R.id.details_content_friend_together_rv)
    RecyclerView contentRv;
    @BindView(R.id.details_applyTv)
    TextView details_applyTv;
    @BindView(R.id.activity_details_of_friends_together_ll_share)
    LinearLayout details_shareLl;
    @BindView(R.id.activity_details_of_friends_together_rl_back)
    RelativeLayout activity_details_of_friends_together_rl_back;
    private Unbinder unbinder;
    private ParticipantsItemAdapter adapter;
    private DetailsOfFriendsTogetherAdapter detailsOfFriendsTogetherAdapter;
    SpImp spImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_friend_together);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        unbinder = ButterKnife.bind(this);
        spImp = new SpImp(DetailsOfFriendTogetherActivity.this);
        initData();
    }

    public void initData(){
        Intent intent = getIntent();
        String pfID = intent.getStringExtra("pfID");
        String userID = spImp.getUID();
        String token = getToken(NetConfig.BaseUrl+NetConfig.friendsTogetherDetailsUrl);
        ViseHttp.POST(NetConfig.friendsTogetherDetailsUrl)
                .addParam("app_key",token)
                .addParam("pfID",pfID)
                .addParam("userID",userID)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                        FriendsTogetherDetailsModel model = new Gson().fromJson(data,FriendsTogetherDetailsModel.class);
                        initView(model.getObj());
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }
    public void initView(FriendsTogetherDetailsModel.ObjBean model){
        if(!StringUtils.isEmpty(model.getShow_pic())){
            Picasso.with(DetailsOfFriendTogetherActivity.this).load(model.getShow_pic()).into(titleIv);
        }
        titleTv.setText(model.getTitle());
        viewsTv.setText(model.getLook());
        focus_onTv.setText(model.getPffavorite());
        if(!model.getCaptain().equals("0")){
            if(!StringUtils.isEmpty(model.getCapttain_pic())){
                Picasso.with(DetailsOfFriendTogetherActivity.this).load(model.getCapttain_pic()).into(headIv);
                levelTv.setText(model.getIf_sign().equals("1")?"签约领队":"普通领队");
            }
        }

        time_start_tv.setText("开始时间："+model.getBegin_time());
        time_end_tv.setText("结束时间："+model.getEnd_time());
        city_tv.setText("活动地点："+model.getCity());
        priceTv.setText("人均消费："+model.getPrice());
        womanTv.setText("女生人数："+model.getWoman());
        manTv.setText("男生人数："+model.getMan());
        participantsTv.setText("参加人员（"+model.getHave_num()+"/"+model.getPerson_num()+")");
        initPerson(model.getUser_list());
        initList(model.getInfo_list());
    }
    private void initPerson(List<FriendsTogetherDetailsModel.ObjBean.UserListBean> data) {

        LinearLayoutManager manager = new LinearLayoutManager(DetailsOfFriendTogetherActivity.this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewP.setLayoutManager(manager);
        adapter = new ParticipantsItemAdapter(data);
        recyclerViewP.setAdapter(adapter);

    }
    private void initList(List<FriendsTogetherDetailsModel.ObjBean.InfoListBean> data){
        LinearLayoutManager manager = new LinearLayoutManager(DetailsOfFriendTogetherActivity.this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        contentRv.setLayoutManager(manager);
        detailsOfFriendsTogetherAdapter = new DetailsOfFriendsTogetherAdapter(data);
        contentRv.setAdapter(detailsOfFriendsTogetherAdapter);
    }
    @OnClick({R.id.details_applyTv,R.id.activity_details_of_friends_together_rl_back,R.id.activity_details_of_friends_together_ll_share})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.activity_details_of_friends_together_rl_back:
                finish();
                break;
            case R.id.details_applyTv:
                startActivity(new Intent(DetailsOfFriendTogetherActivity.this,ApplyActivity.class));
                break;
            case R.id.activity_details_of_friends_together_ll_share:
                new ShareAction(this).setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                ShareUtils.shareWeb(DetailsOfFriendTogetherActivity.this,"http://www.baidu.com","不快乐",
                                        "就是不快乐","",share_media);
                            }
                        }).open();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
