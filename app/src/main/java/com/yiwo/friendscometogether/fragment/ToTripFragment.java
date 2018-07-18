package com.yiwo.friendscometogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/18.
 */

public class ToTripFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_trip, null);
        ScreenAdapterTools.getInstance().loadView(view);

        ButterKnife.bind(this, view);

        initData();

        return view;
    }

    private void initData() {



    }
}
