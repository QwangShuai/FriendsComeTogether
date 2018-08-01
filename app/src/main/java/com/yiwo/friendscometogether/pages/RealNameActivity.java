package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.activity_real_name_rl_complete)
    RelativeLayout rlComplete;
    @BindView(R.id.activity_real_name_et_name)
    EditText etName;
    @BindView(R.id.activity_real_name_et_phonenum)
    EditText etPhoneNum;
    @BindView(R.id.activity_real_name_et_idnum)
    EditText etIdNum;

    private static final int REQUEST_CODE = 0x00000011;
    private static final int REQUEST_CODE1 = 0x00000012;

    private String userImg = "";
    private String userImgBack = "";

    private SpImp spImp;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(RealNameActivity.this);

        initData();

    }

    private void initData() {

        uid = spImp.getUID();

    }

    @OnClick({R.id.activity_real_name_rl_back, R.id.activity_real_name_rl_id_1, R.id.activity_real_name_rl_id_2, R.id.activity_real_name_rl_complete})
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
            case R.id.activity_real_name_rl_complete:
                onComplete();
                break;
        }
    }

    private void onComplete() {

        if(TextUtils.isEmpty(etName.getText().toString())||TextUtils.isEmpty(etPhoneNum.getText().toString())||TextUtils.isEmpty(etIdNum.getText().toString())||
                TextUtils.isEmpty(userImg)||TextUtils.isEmpty(userImgBack)){
            toToast(RealNameActivity.this, "请完善信息");
        }else {
            Observable<List<File>> observable = Observable.create(new ObservableOnSubscribe<List<File>>() {
                @Override
                public void subscribe(ObservableEmitter<List<File>> e) throws Exception {
                    List<File> list = new ArrayList<>();
                    list.add(new File(userImg));
                    list.add(new File(userImgBack));
                    e.onNext(list);
                }
            });
            Observer<List<File>> observer = new Observer<List<File>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<File> value) {
                    ViseHttp.UPLOAD(NetConfig.realNameUrl)
                            .addHeader("Content-Type","multipart/form-data")
                            .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.realNameUrl))
                            .addParam("uid", uid)
                            .addParam("name", etName.getText().toString())
                            .addParam("tel", etPhoneNum.getText().toString())
                            .addParam("code", etIdNum.getText().toString())
                            .addFile("usercode", value.get(0))
                            .addFile("usercodeback", value.get(1))
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.getInt("code") == 200){
                                            toToast(RealNameActivity.this, "已提交审核");
                                            onBackPressed();
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
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            };
            observable.observeOn(Schedulers.newThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            List<String> scList = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Picasso.with(RealNameActivity.this).load("file://" + scList.get(0)).into(ivId1);
            ivId1.setVisibility(View.VISIBLE);
            userImg = scList.get(0);
        }
        if (requestCode == REQUEST_CODE1 && data != null) {
            //获取选择器返回的数据
            List<String> scList = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Log.e("222", scList.get(0));
            Picasso.with(RealNameActivity.this).load("file://" + scList.get(0)).into(ivId2);
            ivId2.setVisibility(View.VISIBLE);
            userImgBack = scList.get(0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RealNameActivity.this.finish();
    }
}
