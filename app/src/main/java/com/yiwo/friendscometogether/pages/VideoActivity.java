package com.yiwo.friendscometogether.pages;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {
//    @BindView(R.id.videoplayer)
//    JCVideoPlayerStandard videoplayer
    @BindView(R.id.videoplayer)
    VideoView videoplayer;
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
//        boolean setUp = videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4", JCVideoPlayer.SCREEN_LAYOUT_LIST, title);
//        if (setUp) {
//            videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(this).load(picUrl).into(videoplayer.thumbImageView);
//
//        }
        Uri uri = Uri.parse( "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4" );
        //设置视频控制器
        videoplayer.setMediaController(new MediaController(this));

        //播放完成回调
//        videoplayer.setOnCompletionListener( new PlayerOnCompletionListener());

        //设置视频路径
        videoplayer.setVideoURI(uri);

        //开始播放视频
        videoplayer.start();
    }
    @OnClick({R.id.rl_set_return})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.rl_set_return:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
