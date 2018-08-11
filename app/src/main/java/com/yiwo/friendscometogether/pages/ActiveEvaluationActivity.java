package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MyActiveCommentAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.ActiveCommentModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.SoftKeyBoardListener;
import com.yiwo.friendscometogether.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActiveEvaluationActivity extends BaseActivity {
    @BindView(R.id.activity__active_evaluation_rv)
    RecyclerView rv;
    @BindView(R.id.activity_active_evaluation_et_comment)
    EditText et;
    private SpImp spImp;
    MyActiveCommentAdapter adapter;
    /**
     * popupwindow相关
     */
    private Button btn_submit;
    private ImageView btn_back;
    private PopupWindow popupWindowhf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_evaluation);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(this);
//注册软键盘的监听
        SoftKeyBoardListener.setListener(this,
                new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
                    @Override
                    public void keyBoardShow(int height) {
//                        Toast.makeText(TieziXqActivity.this,
//                                "键盘显示 高度" + height, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void keyBoardHide(int height) {
//                        Toast.makeText(TieziXqActivity.this,
//                                "键盘隐藏 高度" + height, Toast.LENGTH_SHORT).show();
                        if (popupWindowhf != null) {
                            popupWindowhf.dismiss();
                        }
                    }
                });

        initData();
    }

    private void initData(){
        ViseHttp.POST(NetConfig.activeEvaluationListUrl)
                .addParam("app_key",getToken(NetConfig.BaseUrl+NetConfig.activeEvaluationListUrl))
                .addParam("page","1")
                .addParam("userID",spImp.getUID())
                .addParam("pfID",getIntent().getStringExtra("pfID"))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("1010101",data);
                        ActiveCommentModel model = new Gson().fromJson(data,ActiveCommentModel.class);
                        if (model.getCode()==200){
                            initList(model.getObj());
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }
    private void initList(List<ActiveCommentModel.ObjBean> data) {

        LinearLayoutManager manager = new LinearLayoutManager(ActiveEvaluationActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        adapter = new MyActiveCommentAdapter(data);
        rv.setAdapter(adapter);

    }
    @OnClick({R.id.activity_active_evaluation_tv_comment,R.id.activity_active_evaluation_rl_back})
    public void OnClick(View v) {
        switch (v.getId()){
            case R.id.activity_active_evaluation_rl_back:
                finish();
                break;
            case R.id.activity_active_evaluation_tv_comment:
                if (StringUtils.isEmpty(et.getText().toString())) {
                    toToast(ActiveEvaluationActivity.this, "请输入评论内容");
                } else {//回复

                }
                break;
        }

    }
}
