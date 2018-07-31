package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MyPicturesAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.MyPicListModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPicturesActivity extends BaseActivity {

    @BindView(R.id.activity_my_pictures_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_my_pictures_rv)
    RecyclerView recyclerView;

    private MyPicturesAdapter adapter;
    private List<MyPicListModel.ObjBean> mList;

    private static final int REQUEST_CODE = 0x00000011;

    private SpImp spImp;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pictures);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(MyPicturesActivity.this);

        initData();

    }

    private void initData() {

        uid = spImp.getUID();

        GridLayoutManager manager = new GridLayoutManager(MyPicturesActivity.this, 2);
        recyclerView.setLayoutManager(manager);

        ViseHttp.POST(NetConfig.myPictureListUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.myPictureListUrl))
                .addParam("uid", uid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                MyPicListModel model = gson.fromJson(data, MyPicListModel.class);
                                mList = model.getObj();
                                adapter = new MyPicturesAdapter(mList);
                                recyclerView.setAdapter(adapter);
                                adapter.setOnItemClickListener(new MyPicturesAdapter.onItemClickListener() {
                                    @Override
                                    public void onClick(int type, int position) {
                                        switch (type) {
                                            case 1:
                                                //限数量的多选(比喻最多9张)
                                                ImageSelector.builder()
                                                        .useCamera(true) // 设置是否使用拍照
                                                        .setSingle(false)  //设置是否单选
                                                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected(selected) // 把已选的图片传入默认选中。
                                                        .start(MyPicturesActivity.this, REQUEST_CODE); // 打开相册
                                                break;
                                            case 2:
                                                mList.remove(position - 1);
                                                adapter.notifyDataSetChanged();
                                                break;
                                        }
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
//            mList.addAll(data.getStringArrayListExtra(ImageSelector.SELECT_RESULT));
//            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.activity_my_pictures_rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_my_pictures_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyPicturesActivity.this.finish();
    }
}
