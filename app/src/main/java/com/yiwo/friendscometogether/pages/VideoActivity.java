package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;

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
        boolean setUp = videoplayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, title);
        if (setUp) {
            videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(this).load(picUrl).into(videoplayer.thumbImageView);
        }
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
