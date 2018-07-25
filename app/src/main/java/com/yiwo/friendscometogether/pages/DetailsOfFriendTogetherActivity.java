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
                                ShareUtils.shareWeb(DetailsOfFriendTogetherActivity.this,"www.baidu.com","不快乐",
                                        "就是不快乐","data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAEsASwDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD3+iiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAr541rxf4gPiK9kj1S5i2zuqJG5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",share_media);
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
