package com.yiwo.friendscometogether.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/23.
 */

public class ActivitiesRequireDialog extends Dialog {
    private Context context;
    public ActivitiesRequireDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_activities_require, null);
        setContentView(view);
        ScreenAdapterTools.getInstance().loadView(view);

    }
}
