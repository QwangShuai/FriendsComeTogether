package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.ActivityVideoAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.VideoActiveModel;
import com.yiwo.friendscometogether.network.NetConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.activity_video_rv)
    RecyclerView recyclerView;

    private ActivityVideoAdapter adapter;
    private List<VideoActiveModel.ObjBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {

        String url = getIntent().getStringExtra("videoUrl");
        String picUrl = getIntent().getStringExtra("picUrl");
        String title = getIntent().getStringExtra("title");
        String vid = getIntent().getStringExtra("vid");
        boolean setUp = videoplayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, title);
        if (setUp) {
            videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this).load(picUrl).into(videoplayer.thumbImageView);
        }

        ViseHttp.POST(NetConfig.videoActiveUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.videoActiveUrl))
                .addParam("vID", vid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                VideoActiveModel model = gson.fromJson(data, VideoActiveModel.class);
                                LinearLayoutManager manager = new LinearLayoutManager(VideoActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                mList = model.getObj();
                                adapter = new ActivityVideoAdapter(mList);
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

    @OnClick({R.id.rl_set_return})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.rl_set_return:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
