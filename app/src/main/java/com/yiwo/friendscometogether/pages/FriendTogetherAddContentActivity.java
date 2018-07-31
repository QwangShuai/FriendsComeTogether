package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.UserIntercalationPicModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendTogetherAddContentActivity extends BaseActivity {
    @BindView(R.id.activity_add_content_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_add_content_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_add_content_rl_complete)
    RelativeLayout rlComplete;
    @BindView(R.id.activity_add_content_et_title)
    EditText etTitle;
    @BindView(R.id.activity_add_content_et_content)
    EditText etContent;
    @BindView(R.id.activity_add_content_tv_text_num)
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
        setContentView(R.layout.activity_friend_together_add_content);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(FriendTogetherAddContentActivity.this);

        initData();
    }
    private void initData() {

        Intent intent = getIntent();
        id = intent.getStringExtra("pfID");

        uid = spImp.getUID();
        mList = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(FriendTogetherAddContentActivity.this, 3);
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
                        .start(FriendTogetherAddContentActivity.this, REQUEST_CODE); // 打开相册
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
            tvContentNum.setText(temp.length() + "/2000");
            if (temp.length() >= 2000) {
                toToast(FriendTogetherAddContentActivity.this, "您输入的字数已经超过了限制");
            }
        }
    };
    @OnClick({R.id.activity_add_content_rl_back, R.id.activity_add_content_rl_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_add_content_rl_back:
                onBackPressed();
                break;
            case R.id.activity_add_content_rl_complete:
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

        File imgFile = new File(mList.get(0).getPic());
        Log.i("123321",mList.get(0).getPic());
        Map<String,File> map = new HashMap<>();
        map.put("activity_files[]",imgFile);
        Log.i("3333",imgFile.toString());
        ViseHttp.UPLOAD(NetConfig.addContentFriendTogetherUrl)
                .addHeader("Content-Type","multipart/form-data")
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.addContentFriendTogetherUrl))
                .addParam("title", etTitle.getText().toString())
                .addParam("content", etContent.getText().toString())
                .addParam("activity_id", id)
                .addParam("user_id", uid)
//                .addParam("describe", "都是你的南沙")
                .addFiles(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
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
            List<String> pic = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            for (int i = 0; i < pic.size(); i++) {
                Log.i("333",pic.get(i));
                mList.add(new UserIntercalationPicModel(pic.get(i), ""));
            }
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
