package com.yiwo.friendscometogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseFragment;

/**
 * Created by Administrator on 2018/7/18.
 */

public class AllOrderFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_order, null);
        return view;
    }
}
