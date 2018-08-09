package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);

        initData();
    }
    private void initData() {
        String url = getIntent().getStringExtra("videoUrl");
        String picUrl = getIntent().getStringExtra("picUrl");
        String title = getIntent().getStringExtra("title");
        boolean setUp = videoplayer.setUp("https://www.iqiyi.com/v_19rqyb2oho.html", JCVideoPlayer.SCREEN_LAYOUT_LIST, title);
        if (setUp) {
            videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(picUrl).into(videoplayer.thumbImageView);

        }
    }
}
