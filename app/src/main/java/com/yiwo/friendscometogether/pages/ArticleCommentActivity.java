package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.ArticleCommentAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.ArticleCommentListModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleCommentActivity extends BaseActivity {

    @BindView(R.id.activity_article_comment_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_article_comment_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_article_comment_et_comment)
    EditText etComment;
    @BindView(R.id.activity_article_comment_tv_comment)
    TextView tvComment;

    private ArticleCommentAdapter adapter;
    private List<ArticleCommentListModel.ObjBean> mList;

    private String fmID = "";

    private SpImp spImp;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_comment);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(ArticleCommentActivity.this);

        initData();

    }

    private void initData() {

        uid = spImp.getUID();

        Intent intent = getIntent();
        fmID = intent.getStringExtra("id");

        LinearLayoutManager manager = new LinearLayoutManager(ArticleCommentActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        ViseHttp.POST(NetConfig.articleCommentListUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.articleCommentListUrl))
                .addParam("fmID", fmID)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                ArticleCommentListModel model = gson.fromJson(data, ArticleCommentListModel.class);
                                mList = model.getObj();
                                adapter = new ArticleCommentAdapter(mList);
                                recyclerView.setAdapter(adapter);
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

    @OnClick({R.id.activity_article_comment_rl_back, R.id.activity_article_comment_tv_comment})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_article_comment_rl_back:
                onBackPressed();
                break;
            case R.id.activity_article_comment_tv_comment:
                toComment();
                break;
        }
    }

    /**
     * 提交评论
     */
    private void toComment() {

        ViseHttp.POST(NetConfig.articleCommentUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.articleCommentUrl))
                .addParam("id", fmID)
                .addParam("fctitle", etComment.getText().toString())
                .addParam("uid", uid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                toToast(ArticleCommentActivity.this, "评论成功");
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
        ArticleCommentActivity.this.finish();
    }
}
