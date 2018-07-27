package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.IntercalationAdapter;
import com.yiwo.friendscometogether.adapter.MyPicturesAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.UserIntercalationPicModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateIntercalationActivity extends BaseActivity {

    @BindView(R.id.activity_create_intercalation_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_create_intercalation_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_create_intercalation_rl_complete)
    RelativeLayout rlComplete;
    @BindView(R.id.activity_create_intercalation_et_title)
    EditText etTitle;
    @BindView(R.id.activity_create_intercalation_et_content)
    EditText etContent;
    @BindView(R.id.activity_create_intercalation_tv_text_num)
    TextView tvContentNum;

    private IntercalationAdapter adapter;
    private List<UserIntercalationPicModel> mList;

    private static final int REQUEST_CODE = 0x00000011;

    private SpImp spImp;
    private String uid = "";
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_intercalation);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(CreateIntercalationActivity.this);

        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        uid = spImp.getUID();
        mList = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(CreateIntercalationActivity.this, 3);
        recyclerView.setLayoutManager(manager);
        adapter = new IntercalationAdapter(mList);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new IntercalationAdapter.OnAddImgListener() {
            @Override
            public void onAddImg() {
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected(selected) // 把已选的图片传入默认选中。
                        .start(CreateIntercalationActivity.this, REQUEST_CODE); // 打开相册
            }
        }, new IntercalationAdapter.OnDeleteImgListener() {
            @Override
            public void onDeleteImg(int i) {
                mList.remove(i);
                adapter.notifyDataSetChanged();
            }
        }, new IntercalationAdapter.OnAddDescribeListener() {
            @Override
            public void onAddDescribe(int i, String s) {
                mList.get(i).setDescribe(s);
                adapter.notifyDataSetChanged();
            }
        });

        etContent.addTextChangedListener(textContentWatcher);

    }

    TextWatcher textContentWatcher = new TextWatcher() {

        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tvContentNum.setText(temp.length()+"/2000");
            if(temp.length()>=2000){
                toToast(CreateIntercalationActivity.this, "您输入的字数已经超过了限制");
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            List<String> pic = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            for (int i = 0; i < pic.size(); i++) {
                mList.add(new UserIntercalationPicModel(pic.get(i), ""));
            }
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.activity_create_intercalation_rl_back, R.id.activity_create_intercalation_rl_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_create_intercalation_rl_back:
                onBackPressed();
                break;
            case R.id.activity_create_intercalation_rl_complete:
                complete();
                break;
        }
    }

    /**
     * 发布
     */
    private void complete() {

        String images = "";
        for (int i = 0; i < mList.size(); i++) {
            if (i < mList.size() - 1) {
                images = "http://47.92.136.19/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg" + "-" + mList.get(i).getDescribe() + "|";
            } else {
                images = "http://47.92.136.19/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg" + "-" + mList.get(i).getDescribe();
            }
        }

        ViseHttp.POST(NetConfig.userRenewTheArticle)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userRenewTheArticle))
                .addParam("title", etTitle.getText().toString())
                .addParam("content", etContent.getText().toString())
                .addParam("id", id)
                .addParam("images", images)
                .addParam("uid", uid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                toToast(CreateIntercalationActivity.this, jsonObject.getString("message"));
                                CreateIntercalationActivity.this.finish();
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
    public void onBackPressed() {
        super.onBackPressed();
        CreateIntercalationActivity.this.finish();
    }
}
