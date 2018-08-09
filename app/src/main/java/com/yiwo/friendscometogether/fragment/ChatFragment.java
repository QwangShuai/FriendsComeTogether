package com.yiwo.friendscometogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.business.session.module.MsgForwardFilter;
import com.netease.nim.uikit.business.session.module.MsgRevokeFilter;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.constant.VerifyType;
import com.netease.nimlib.sdk.friend.model.AddFriendData;
import com.netease.nimlib.sdk.friend.model.AddFriendNotify;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.SystemMessage;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/16.
 */

public class ChatFragment extends BaseFragment{
    View rootView;

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;

    private String account;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chat,null);
        ScreenAdapterTools.getInstance().loadView(rootView);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                account = "yy15244615473";
                token = "c52f69e327c6c8e5cad8635de42662e7";
                LoginInfo info = new LoginInfo(account, token);
                RequestCallback<LoginInfo> callback =
                        new RequestCallback<LoginInfo>() {
                            @Override
                            public void onSuccess(LoginInfo loginInfo) {
                                NimUIKit.loginSuccess(account);
                                toToast(getContext(), "登录成功");
                                NimUIKit.setMsgForwardFilter(new MsgForwardFilter() {
                                    @Override
                                    public boolean shouldIgnore(IMMessage message) {
                                        return false;
                                    }
                                });
                                NimUIKit.setMsgRevokeFilter(new MsgRevokeFilter() {
                                    @Override
                                    public boolean shouldIgnore(IMMessage message) {
                                        return false;
                                    }
                                });
                            }

                            @Override
                            public void onFailed(int i) {
                                toToast(getContext(), "登录失败");
                            }

                            @Override
                            public void onException(Throwable throwable) {
                                toToast(getContext(), "登录出错");
                            }
                            // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                        };
                NIMClient.getService(AuthService.class).login(info)
                        .setCallback(callback);
                break;
            case R.id.btn2:
                liaotian();
                break;
        }
    }

    private void liaotian() {
        String liaotianAccount = "ylyy13945060492";
        NimUIKit.setAccount(account);
        NimUIKit.startP2PSession(getContext(), liaotianAccount);
    }

}
