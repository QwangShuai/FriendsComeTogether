package com.yiwo.friendscometogether.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseFragment;
import com.yiwo.friendscometogether.pages.MyFocusActivity;
import com.yiwo.friendscometogether.pages.MyOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/16.
 */

public class MyFragment extends BaseFragment {
    View rootView;

    @BindView(R.id.fragment_my_ll_look_more)
    LinearLayout llLookMore;
    @BindView(R.id.fragment_my_ll_to_pay)
    LinearLayout llToPay;
    @BindView(R.id.fragment_my_ll_to_trip)
    LinearLayout llToTrip;
    @BindView(R.id.fragment_my_ll_to_comment)
    LinearLayout llComment;
    @BindView(R.id.fragment_my_ll_return_price)
    LinearLayout llReturnPrice;
    @BindView(R.id.fragment_my_rl_focus)
    RelativeLayout rlFocus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my,null);
        ScreenAdapterTools.getInstance().loadView(rootView);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.fragment_my_ll_look_more, R.id.fragment_my_ll_to_pay, R.id.fragment_my_ll_to_trip, R.id.fragment_my_ll_to_comment, R.id.fragment_my_ll_return_price,
    R.id.fragment_my_rl_focus})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.fragment_my_ll_look_more:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 0);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_to_pay:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 1);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_to_trip:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 2);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_to_comment:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 3);
                startActivity(intent);
                break;
            case R.id.fragment_my_ll_return_price:
                intent.setClass(getContext(), MyOrderActivity.class);
                intent.putExtra("position", 4);
                startActivity(intent);
                break;
            case R.id.fragment_my_rl_focus:
                intent.setClass(getContext(), MyFocusActivity.class);
                startActivity(intent);
                break;
        }
    }

}
