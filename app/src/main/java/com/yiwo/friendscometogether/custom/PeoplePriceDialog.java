package com.yiwo.friendscometogether.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;

/**
 * Created by Administrator on 2018/7/20.
 */

public class PeoplePriceDialog extends Dialog {

    private Context context;

    public PeoplePriceDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_people_price, null);
        setContentView(view);
        ScreenAdapterTools.getInstance().loadView(view);

    }
}