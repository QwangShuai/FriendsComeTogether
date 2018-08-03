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
import android.widget.Toast;

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
import com.yiwo.friendscometogether.model.FocusOnLeaderModel;
import com.yiwo.friendscometogether.model.FocusOnToFriendTogetherModel;
import com.yiwo.friendscometogether.model.FriendsTogetherDetailsModel;
import com.yiwo.friendscometogether.model.FriendsTogethermodel;
import com.yiwo.friendscometogether.model.IsRealNameModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.ShareUtils;
import com.yiwo.friendscometogether.utils.StringUtils;
import com.yiwo.friendscometogether.utils.TokenUtils;

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
    @BindView(R.id.activity_details_of_friends_together_ll_focus_on)
    LinearLayout focusOnLl;
    @BindView(R.id.activity_details_of_friends_together_iv_focus_on)
    ImageView focusOnIv;
    @BindView(R.id.activity_details_of_friends_together_rl_back)
    RelativeLayout activity_details_of_friends_together_rl_back;
    @BindView(R.id.activity_details_of_friends_together_ll_top_focus)
    LinearLayout focusOnLeaderLl;
    @BindView(R.id.activity_details_of_friends_together_iv_focus)
    ImageView focusOnLeaderIv;
    private Unbinder unbinder;
    private ParticipantsItemAdapter adapter;
    private DetailsOfFriendsTogetherAdapter detailsOfFriendsTogetherAdapter;
    SpImp spImp;
    FriendsTogetherDetailsModel model;
    String pfID;
    String leaderID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_friend_together);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        unbinder = ButterKnife.bind(this);
        spImp = new SpImp(DetailsOfFriendTogetherActivity.this);
        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        pfID = intent.getStringExtra("pfID");
        String userID = spImp.getUID();
        final String token = getToken(NetConfig.BaseUrl + NetConfig.friendsTogetherDetailsUrl);
        ViseHttp.POST(NetConfig.friendsTogetherDetailsUrl)
                .addParam("app_key", token)
                .addParam("pfID", pfID)
                .addParam("userID", userID)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                        model = new Gson().fromJson(data, FriendsTogetherDetailsModel.class);
                        if(model.getCode()==200){
                            initView(model.getObj());
                        } else {
                            toToast(DetailsOfFriendTogetherActivity.this,model.getMessage());
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    public void initView(FriendsTogetherDetailsModel.ObjBean model) {
        if(!StringUtils.isEmpty(model.getCaptain()))
            leaderID = model.getCaptain();
        if (!StringUtils.isEmpty(model.getShow_pic())) {
            Picasso.with(DetailsOfFriendTogetherActivity.this).load(model.getShow_pic()).into(titleIv);
        }
        titleTv.setText(model.getTitle());
        viewsTv.setText(model.getLook());
        focus_onTv.setText(model.getPffavorite());
        if (!model.getCaptain().equals("0")) {
            if (!StringUtils.isEmpty(model.getCapttain_pic())) {
                Picasso.with(DetailsOfFriendTogetherActivity.this).load(model.getCapttain_pic()).into(headIv);
                levelTv.setText(model.getIf_sign().equals("1") ? "签约领队" : "普通领队");
            }
        }
        Log.i("qwe", model.getAttention());
        focusOnIv.setImageResource(model.getAttention().equals("0") ? R.mipmap.focus_on_empty_y : R.mipmap.focus_on_y);
        time_start_tv.setText("开始时间：" + model.getBegin_time());
        time_end_tv.setText("结束时间：" + model.getEnd_time());
        city_tv.setText("活动地点：" + model.getCity());
        priceTv.setText("人均消费：" + model.getPrice());
        womanTv.setText("女生人数：" + model.getWoman());
        manTv.setText("男生人数：" + model.getMan());
        participantsTv.setText("参加人员（" + model.getHave_num() + "/" + model.getPerson_num() + ")");
        focusOnLeaderIv.setImageResource(model.getAttention_captain().equals("0")?R.mipmap.focus_on_empty_y : R.mipmap.focus_on_y);
        if(!StringUtils.isEmpty(leaderID)&&!leaderID.equals("0")){
            levelTv.setText(model.getIf_sign().equals("0")?"普通领队":"签约领队");
        } else {
            levelTv.setText("暂无领队");
        }

        initPerson(model.getUser_list());
        initList(model.getInfo_list());
    }

    private void initPerson(List<FriendsTogetherDetailsModel.ObjBean.UserListBean> data) {

        LinearLayoutManager manager = new LinearLayoutManager(DetailsOfFriendTogetherActivity.this) {
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

    private void initList(List<FriendsTogetherDetailsModel.ObjBean.InfoListBean> data) {
        LinearLayoutManager manager = new LinearLayoutManager(DetailsOfFriendTogetherActivity.this) {
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

    @OnClick({R.id.details_applyTv, R.id.activity_details_of_friends_together_rl_back, R.id.activity_details_of_friends_together_ll_share,
            R.id.activity_details_of_friends_together_ll_focus_on,R.id.activity_details_of_friends_together_ll_top_focus})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.activity_details_of_friends_together_rl_back:
                finish();
                break;
            case R.id.details_applyTv:
                ViseHttp.POST(NetConfig.isRealNameUrl)
                        .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.isRealNameUrl))
                        .addParam("userID", spImp.getUID())
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                Log.i("123321", data);
                                IsRealNameModel models = new Gson().fromJson(data, IsRealNameModel.class);
                                if (models.getCode() == 200) {
                                    if (models.getObj().getOk().equals("2")) {
                                        Intent it = new Intent(DetailsOfFriendTogetherActivity.this, ApplyActivity.class);
                                        it.putExtra("if_pay", model.getObj().getIf_pay());
                                        it.putExtra("title", model.getObj().getTitle());
                                        it.putExtra("begin_time", model.getObj().getBegin_time());
                                        it.putExtra("price", model.getObj().getPrice());
                                        it.putExtra("pfID", model.getObj().getPfID());
                                        it.putExtra("sex", "2");
                                        startActivity(it);
                                    } else if (models.getObj().getOk().equals("1")) {
                                        toToast(DetailsOfFriendTogetherActivity.this, "请于身份审核通过后报名");
                                    } else {
                                        startActivity(new Intent(DetailsOfFriendTogetherActivity.this, RealNameActivity.class));
                                    }
                                } else {
                                    toToast(DetailsOfFriendTogetherActivity.this, model.getMessage());
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
            case R.id.activity_details_of_friends_together_ll_share:
                new ShareAction(this).setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                ShareUtils.shareWeb(DetailsOfFriendTogetherActivity.this, "http://www.baidu.com", "不快乐",
                                        "就是不快乐", "", share_media);
                            }
                        }).open();
                break;
            case R.id.activity_details_of_friends_together_ll_focus_on:
                String userID = spImp.getUID();
                ViseHttp.POST(NetConfig.focusOnToFriendTogetherUrl)
                        .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.focusOnToFriendTogetherUrl))
                        .addParam("userID", userID)
                        .addParam("pfID", pfID)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                FocusOnToFriendTogetherModel model = new Gson().fromJson(result, FocusOnToFriendTogetherModel.class);
                                if (model.getCode() == 200) {
                                    if (model.getObj().equals("1")) {
                                        focusOnIv.setImageResource(R.mipmap.focus_on_y);
//                                        toToast(DetailsOfFriendTogetherActivity.this, "关注成功");
                                    } else {
                                        focusOnIv.setImageResource(R.mipmap.focus_on_empty_y);
//                                        toToast(DetailsOfFriendTogetherActivity.this, "取消成功");
                                    }
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
            case R.id.activity_details_of_friends_together_ll_top_focus:
                if (!StringUtils.isEmpty(leaderID)&&!leaderID.equals("0")){
                    ViseHttp.POST(NetConfig.focusOnLeaderUrl)
                            .addParam("app_key",getToken(NetConfig.BaseUrl+NetConfig.focusOnLeaderUrl))
                            .addParam("userID",spImp.getUID())
                            .addParam("attention_userID",leaderID)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    FocusOnLeaderModel model = new Gson().fromJson(data,FocusOnLeaderModel.class);
                                    if(model.getCode()==200){
                                        if(model.getObj().getAttention().equals("0")){
                                            focusOnLeaderIv.setImageResource(R.mipmap.focus_on_empty_y);
                                        } else {
                                            focusOnLeaderIv.setImageResource(R.mipmap.focus_on_y);
                                        }
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {

                                }
                            });
                } else {
                    toToast(DetailsOfFriendTogetherActivity.this,"暂无领队");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
