package com.yiwo.friendscometogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.model.UserModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.CreateFriendRememberActivity;
import com.yiwo.friendscometogether.pages.JoinActiveActivity;
import com.yiwo.friendscometogether.pages.LoginActivity;
import com.yiwo.friendscometogether.pages.LookHistoryActivity;
import com.yiwo.friendscometogether.pages.MyCollectionActivity;
import com.yiwo.friendscometogether.pages.MyCommentActivity;
import com.yiwo.friendscometogether.pages.MyDraftActivity;
import com.yiwo.friendscometogether.pages.MyFocusActivity;
import com.yiwo.friendscometogether.pages.MyFriendRememberActivity;
import com.yiwo.friendscometogether.pages.MyInformationActivity;
import com.yiwo.friendscometogether.pages.MyIntercalationActivity;
import com.yiwo.friendscometogether.pages.MyOrderActivity;
import com.yiwo.friendscometogether.pages.MyPicturesActivity;
import com.yiwo.friendscometogether.pages.StartActiveActivity;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/16.
 */

public class MyFragment extends BaseFragment {
    View rootView;

    @BindView(R.id.fragment_my_ll_look_more)
    LinearLayout llLookMore;
    @BindView(R.id.fragment_my_ll_to_pay)
    LinearLayout llToPay;
    @BindView(R.id.fragment_my_ll_to_trip)
    LinearLayout llToTrip;
    @BindView(R.id.fragment_my_ll_to_comment)
    LinearLayout llComment;
    @BindView(R.id.fragment_my_ll_return_price)
    LinearLayout llReturnPrice;
    @BindView(R.id.fragment_my_rl_focus)
    RelativeLayout rlFocus;
    @BindView(R.id.fragment_my_rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.fragment_my_rl_comment)
    RelativeLayout rlComment;
    @BindView(R.id.fragment_my_rl_history)
    RelativeLayout rlHistory;
    @BindView(R.id.fragment_my_ll_draft)
    LinearLayout llDraft;
    @BindView(R.id.fragment_my_ll_create_friend_remember)
    LinearLayout llFriendRemember;
    @BindView(R.id.fragment_my_person_set)
    RelativeLayout rlPersonSet;
    @BindView(R.id.fragment_my_ll_my_friend_remember)
    LinearLayout llMyFriendRemember;
    @BindView(R.id.fragment_my_ll_my_intercalation)
    LinearLayout llMyIntercalation;
    @BindView(R.id.fragment_my_rl_initiating_activities)
    RelativeLayout rlInitiating;
    @BindView(R.id.fragment_my_rl_join_activitys)
    RelativeLayout rlJoin;
    @BindView(R.id.fragment_my_rl_picture)
    RelativeLayout rlPicture;
    @BindView(R.id.fragment_my_tv_not_login)
    TextView tvNotLogin;
    @BindView(R.id.fragment_my_rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.fragment_my_iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.fragment_my_tv_nickname)
    TextView tvNickname;
    @BindView(R.id.fragment_my_iv_sex)
    ImageView ivSex;
    @BindView(R.id.fragment_my_sign_team)
    RelativeLayout rlSignTeam;

    private SpImp spImp;
    private String uid = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my, null);
        ScreenAdapterTools.getInstance().loadView(rootView);

        ButterKnife.bind(this, rootView);
        spImp = new SpImp(getContext());

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        uid = spImp.getUID();
        Log.e("222", uid);
        if (!TextUtils.isEmpty(uid) && !uid.equals("0")) {
            tvNotLogin.setVisibility(View.GONE);
            rlContent.setVisibility(View.VISIBLE);
            ViseHttp.POST(NetConfig.userInformation)
                    .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userInformation))
                    .addParam("uid", uid)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                Log.e("222", data);
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.getInt("code") == 200) {
                                    Gson gson = new Gson();
                                    UserModel userModel = gson.fromJson(data, UserModel.class);
                                    Picasso.with(getContext()).load(userModel.getObj().getHeadeimg()).into(ivAvatar);
                                    tvNickname.setText("昵称: " + userModel.getObj().getUsername());
                                    if(userModel.getObj().getSex().equals("男")){
                                        Picasso.with(getContext()).load(R.mipmap.nan).into(ivSex);
                                    }else {
                                        Picasso.with(getContext()).load(R.mipmap.nv).into(ivSex);
                                    }
                                    if(userModel.getObj().getSign().equals("0")){
                                        rlSignTeam.setVisibility(View.GONE);
                                    }else {
                                        rlSignTeam.setVisibility(View.VISIBLE);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        } else {
            tvNotLogin.setVisibility(View.VISIBLE);
            rlContent.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.fragment_my_ll_look_more, R.id.fragment_my_ll_to_pay, R.id.fragment_my_ll_to_trip, R.id.fragment_my_ll_to_comment, R.id.fragment_my_ll_return_price,
            R.id.fragment_my_rl_focus, R.id.fragment_my_rl_collection, R.id.fragment_my_rl_comment, R.id.fragment_my_rl_history, R.id.fragment_my_ll_draft,
            R.id.fragment_my_ll_create_friend_remember, R.id.fragment_my_person_set, R.id.fragment_my_ll_my_friend_remember, R.id.fragment_my_ll_my_intercalation,
            R.id.fragment_my_rl_initiating_activities, R.id.fragment_my_rl_join_activitys, R.id.fragment_my_rl_picture})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.fragment_my_ll_look_more:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 0);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_to_pay:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 1);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_to_trip:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 2);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_to_comment:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 3);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_return_price:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 4);
                startActivity(intent);
                break;
            case R.id.fragment_my_rl_focus:
                intent.setClass(getContext(), MyFocusActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_rl_collection:
                intent.setClass(getContext(), MyCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_rl_comment:
                intent.setClass(getContext(), MyCommentActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_rl_history:
                intent.setClass(getContext(), LookHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_draft:
                intent.setClass(getContext(), MyDraftActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_create_friend_remember:
                intent.setClass(getContext(), CreateFriendRememberActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_person_set:
                if (!TextUtils.isEmpty(uid) && !uid.equals("0")) {
                    intent.setClass(getContext(), MyInformationActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.fragment_my_ll_my_friend_remember:
                intent.setClass(getContext(), MyFriendRememberActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_my_intercalation:
                intent.setClass(getContext(), MyIntercalationActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_rl_initiating_activities:
                intent.setClass(getContext(), StartActiveActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_rl_join_activitys:
                intent.setClass(getContext(), JoinActiveActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_my_rl_picture:
                intent.setClass(getContext(), MyPicturesActivity.class);
                startActivity(intent);
                break;
        }
    }

}
