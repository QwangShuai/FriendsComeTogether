package com.yiwo.friendscometogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.base.OrderBaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/18.
 */

public class AllOrderFragment extends OrderBaseFragment {

    @Override
    public View initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_all_order, null);
        ScreenAdapterTools.getInstance().loadView(view);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void initData() {

    }
}
