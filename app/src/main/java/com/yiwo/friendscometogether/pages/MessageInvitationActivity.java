package com.yiwo.friendscometogether.pages;

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
import com.yiwo.friendscometogether.adapter.MessageInvitationAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.MessageInvitationListModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageInvitationActivity extends BaseActivity {

    @BindView(R.id.activity_message_invitation_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_message_invitation_rv)
    RecyclerView recyclerView;

    private String uid = "";
    private SpImp spImp;

    private MessageInvitationAdapter adapter;
    private List<MessageInvitationListModel.ObjBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_invitation);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(MessageInvitationActivity.this);
        spImp = new SpImp(MessageInvitationActivity.this);

        initData();

    }

    private void initData() {

        ViseHttp.POST(NetConfig.messageInvitationListUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.messageInvitationListUrl))
                .addParam("uid", spImp.getUID())
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                MessageInvitationListModel model = gson.fromJson(data, MessageInvitationListModel.class);
                                LinearLayoutManager manager = new LinearLayoutManager(MessageInvitationActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                mList = model.getObj();
                                adapter = new MessageInvitationAdapter(mList);
                                recyclerView.setAdapter(adapter);
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

    @OnClick({R.id.activity_message_invitation_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_message_invitation_rl_back:
                MessageInvitationActivity.this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MessageInvitationActivity.this.finish();
    }
}
