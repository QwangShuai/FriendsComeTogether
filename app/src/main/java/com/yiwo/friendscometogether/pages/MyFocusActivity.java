package com.yiwo.friendscometogether.pages;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MyFocusAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.UserFocusModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFocusActivity extends BaseActivity {

    @BindView(R.id.activity_my_focus_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_my_focus_rv)
    RecyclerView recyclerView;

    private MyFocusAdapter adapter;
    private List<UserFocusModel.ObjBean> mList;

    private SpImp spImp;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_focus);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(MyFocusActivity.this);

        initData();

    }

    private void initData() {

        uid = spImp.getUID();
        LinearLayoutManager manager = new LinearLayoutManager(MyFocusActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        ViseHttp.POST(NetConfig.userFocus)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.userFocus))
                .addParam("page", "1")
                .addParam("userID", uid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                UserFocusModel userFocusModel = gson.fromJson(data, UserFocusModel.class);
                                mList = userFocusModel.getObj();
                                adapter = new MyFocusAdapter(mList);
                                recyclerView.setAdapter(adapter);
                                adapter.setOnFocusCancelListener(new MyFocusAdapter.OnFocusCancelListener() {
                                    @Override
                                    public void onCancel(final int position) {
                                        toDialog(MyFocusActivity.this, "提示", "是否取消关注", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                ViseHttp.POST(NetConfig.userCancelFocusUrl)
                                                        .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.userCancelFocusUrl))
                                                        .addParam("listId", mList.get(position).getLID())
                                                        .request(new ACallback<String>() {
                                                            @Override
                                                            public void onSuccess(String data) {
                                                                try {
                                                                    JSONObject jsonObject1 = new JSONObject(data);
                                                                    if(jsonObject1.getInt("code") == 200){
                                                                        toToast(MyFocusActivity.this, "取消关注成功");
                                                                        mList.remove(position);
                                                                        adapter.notifyDataSetChanged();
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
                                        }, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                                    }
                                });
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

    @OnClick({R.id.activity_my_focus_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_focus_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyFocusActivity.this.finish();
    }
}
