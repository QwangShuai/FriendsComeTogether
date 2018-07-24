package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameActivity extends BaseActivity {

    @BindView(R.id.activity_real_name_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_real_name_rl_id_1)
    RelativeLayout rlId1;
    @BindView(R.id.activity_real_name_iv_id_1)
    ImageView ivId1;
    @BindView(R.id.activity_real_name_rl_id_2)
    RelativeLayout rlId2;
    @BindView(R.id.activity_real_name_iv_id_2)
    ImageView ivId2;

    private static final int REQUEST_CODE = 0x00000011;
    private static final int REQUEST_CODE1 = 0x00000012;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {


    }

    @OnClick({R.id.activity_real_name_rl_back, R.id.activity_real_name_rl_id_1, R.id.activity_real_name_rl_id_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_real_name_rl_back:
                onBackPressed();
                break;
            case R.id.activity_real_name_rl_id_1:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(true)  //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected(selected) // 把已选的图片传入默认选中。
                        .start(RealNameActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.activity_real_name_rl_id_2:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(true)  //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected(selected) // 把已选的图片传入默认选中。
                        .start(RealNameActivity.this, REQUEST_CODE1); // 打开相册
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            List<String> scList = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Log.e("222", scList.get(0));
            Picasso.with(RealNameActivity.this).load("file://" + scList.get(0)).into(ivId1);
            ivId1.setVisibility(View.VISIBLE);
        }
        if (requestCode == REQUEST_CODE1 && data != null) {
            //获取选择器返回的数据
            List<String> scList = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Log.e("222", scList.get(0));
            Picasso.with(RealNameActivity.this).load("file://" + scList.get(0)).into(ivId2);
            ivId2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RealNameActivity.this.finish();
    }
}
