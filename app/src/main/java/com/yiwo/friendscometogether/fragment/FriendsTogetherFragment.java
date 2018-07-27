package com.yiwo.friendscometogether.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.FriendRememberUpDataAdapter;
import com.yiwo.friendscometogether.adapter.FriendTogetherUpDataAdapter;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.model.CityModel;
import com.yiwo.friendscometogether.model.FriendsRememberModel;
import com.yiwo.friendscometogether.model.FriendsTogethermodel;
import com.yiwo.friendscometogether.model.UserLabelModel;
import com.yiwo.friendscometogether.network.ActivityConfig;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.CityActivity;
import com.yiwo.friendscometogether.pages.CreateFriendRememberActivity;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;
import com.yiwo.friendscometogether.pages.SearchActivity;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FriendsTogetherFragment extends BaseFragment{
    View rootView;
    @BindView(R.id.fragment_friend_together_banner)
    Banner banner;
    @BindView(R.id.fragment_friend_together_rv)
    RecyclerView recyclerView;
    @BindView(R.id.select_city)
    LinearLayout cityLl;
    @BindView(R.id.select_lable)
    LinearLayout lableLl;
    @BindView(R.id.search_leader)
    LinearLayout searchLl;
    @BindView(R.id.fragment_friends_together_city_tv)
    TextView cityTv;
    @BindView(R.id.fragment_friends_together_lable_tv)
    TextView lableTv;

    private FriendTogetherUpDataAdapter adapter;
    private String[] itemId;
    private String[] itemName;
    private String yourChoiceId = "";
    private String yourChoiceName = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_friends_together,null);
        ScreenAdapterTools.getInstance().loadView(rootView);

        ButterKnife.bind(this, rootView);

        init(banner,DetailsOfFriendsActivity.class);
        initData();
        return rootView;
    }

    private void initData() {
        getLable();
        String token = getToken(NetConfig.BaseUrl+NetConfig.friendsTogetherUrl);
        ViseHttp.POST(NetConfig.friendsTogetherUrl)
                .addParam("app_key",token)
                .addParam("page", "1")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                        FriendsTogethermodel model = new Gson().fromJson(data,FriendsTogethermodel.class);
                        initList(model.getObj());
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    private void initList(List<FriendsTogethermodel.ObjBean> data) {

        LinearLayoutManager manager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new FriendTogetherUpDataAdapter(data);
        recyclerView.setAdapter(adapter);
    }
    @OnClick({R.id.select_city,R.id.select_lable,R.id.search_leader})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.select_city:
                Intent it = new Intent(getActivity(), CityActivity.class);
                it.putExtra(ActivityConfig.ACTIVITY,"home");
                startActivityForResult(it,1);
                break;
            case R.id.select_lable:
                AlertDialog.Builder singleChoiceDialog =
                        new AlertDialog.Builder(getContext());
                singleChoiceDialog.setTitle("请选择标签");
                // 第二个参数是默认选项，此处设置为0
                singleChoiceDialog.setSingleChoiceItems(itemName, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                yourChoiceName = itemName[which];
                                yourChoiceId = itemId[which];
                            }
                        });
                singleChoiceDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (TextUtils.isEmpty(yourChoiceName)) {
                                    lableTv.setText(itemName[0]);
                                    yourChoiceId = itemId[0];
                                } else {
                                    lableTv.setText(yourChoiceName);
                                    yourChoiceName = "";
                                }
                            }
                        });
                singleChoiceDialog.show();
                break;
            case R.id.search_leader:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&data!=null){
            CityModel model = (CityModel) data.getSerializableExtra(ActivityConfig.CITY);
            cityTv.setText(model.getName());
        }
    }
    public void getLable(){
        ViseHttp.POST(NetConfig.userLabel)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userLabel))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("222", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson = new Gson();
                                UserLabelModel userLabelModel = gson.fromJson(data, UserLabelModel.class);
                                itemId = new String[userLabelModel.getObj().size()];
                                itemName = new String[userLabelModel.getObj().size()];
                                for (int i = 0; i < userLabelModel.getObj().size(); i++) {
                                    itemId[i] = userLabelModel.getObj().get(i).getLID();
                                    itemName[i] = userLabelModel.getObj().get(i).getLname();
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
    }
}
