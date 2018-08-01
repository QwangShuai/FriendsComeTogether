package com.yiwo.friendscometogether.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.IntercalationAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.IntercalationLocationModel;
import com.yiwo.friendscometogether.model.UserIntercalationPicModel;
import com.yiwo.friendscometogether.model.UserLabelModel;
import com.yiwo.friendscometogether.network.NetConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertIntercalationActivity extends BaseActivity {

    @BindView(R.id.activity_insert_intercalation_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_insert_intercalation_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_insert_intercalation_et_title)
    EditText etTitle;
    @BindView(R.id.activity_insert_intercalation_et_content)
    EditText etContent;
    @BindView(R.id.activity_insert_intercalation_tv_text_num)
    TextView tvContentNum;
    @BindView(R.id.activity_insert_intercalation_rl_complete)
    RelativeLayout rlComplete;
    @BindView(R.id.activity_insert_intercalation_rl_intercalation_location)
    RelativeLayout rlIntercalationLocation;
    @BindView(R.id.activity_insert_intercalation_tv_select)
    TextView tvSelect;

    private IntercalationAdapter adapter;
    private List<UserIntercalationPicModel> mList;

    private static final int REQUEST_CODE = 0x00000011;

    private String[] itemId;
    private String[] itemName;
    private String yourChoiceId = "";
    private String yourChoiceName = "";

    private String fmID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_intercalation);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        fmID = intent.getStringExtra("id");

        mList = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(InsertIntercalationActivity.this, 3);
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
                        .start(InsertIntercalationActivity.this, REQUEST_CODE); // 打开相册
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

        ViseHttp.POST(NetConfig.intercalationLocationUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.intercalationLocationUrl))
                .addParam("id", fmID)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("222", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson = new Gson();
                                IntercalationLocationModel model = gson.fromJson(data, IntercalationLocationModel.class);
                                itemId = new String[model.getObj().size()];
                                itemName = new String[model.getObj().size()];
                                for (int i = 0; i < model.getObj().size(); i++) {
                                    itemId[i] = model.getObj().get(i).getFfID();
                                    itemName[i] = model.getObj().get(i).getFftitle();
                                }
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
                toToast(InsertIntercalationActivity.this, "您输入的字数已经超过了限制");
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

    @OnClick({R.id.activity_insert_intercalation_rl_back, R.id.activity_insert_intercalation_rl_complete, R.id.activity_insert_intercalation_rl_intercalation_location})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_insert_intercalation_rl_back:
                onBackPressed();
                break;
            case R.id.activity_insert_intercalation_rl_complete:
                complete();
                break;
            case R.id.activity_insert_intercalation_rl_intercalation_location:
                AlertDialog.Builder singleChoiceDialog =
                        new AlertDialog.Builder(InsertIntercalationActivity.this);
                singleChoiceDialog.setTitle("请选择标签");
                // 第二个参数是默认选项，此处设置为0
                singleChoiceDialog.setSingleChoiceItems(itemName, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                yourChoiceName = itemName[which];
                                yourChoiceId = itemId[which];
                            }
                        });
                singleChoiceDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (TextUtils.isEmpty(yourChoiceName)) {
                                    tvSelect.setText(itemName[0]);
                                    yourChoiceId = itemId[0];
                                } else {
                                    tvSelect.setText(yourChoiceName);
                                    yourChoiceName = "";
                                }
                            }
                        });
                singleChoiceDialog.show();
                break;
        }
    }

    /**
     * 发布监听
     */
    private void complete() {



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InsertIntercalationActivity.this.finish();
    }
}
