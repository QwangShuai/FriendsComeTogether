package com.yiwo.friendscometogether.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseFragment;

/**
 * Created by Administrator on 2018/7/16.
 */

public class HomeFragment extends BaseFragment {
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home,null);
        return rootView;
    }
}
