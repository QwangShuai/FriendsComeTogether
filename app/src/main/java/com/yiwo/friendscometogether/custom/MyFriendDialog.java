package com.yiwo.friendscometogether.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;

/**
 * Created by Administrator on 2018/9/4 0004.
 */

public class MyFriendDialog extends Dialog {

    private Context context;
    private TextView tvDelete;
    private TextView tvBlack;
    private OnMyFriendListener listener;

    public MyFriendDialog(@NonNull Context context, OnMyFriendListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_my_friend, null);
        setContentView(view);
        tvDelete = view.findViewById(R.id.tv_delete_friend);
        tvBlack = view.findViewById(R.id.tv_add_black);
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onReturn(0);
                dismiss();
            }
        });
        tvBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onReturn(1);
                dismiss();
            }
        });

    }

    public interface OnMyFriendListener{
        void onReturn(int type);
    }

}
