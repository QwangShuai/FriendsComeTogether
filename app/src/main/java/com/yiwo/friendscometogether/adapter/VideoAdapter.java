package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.HomeHotFriendsRememberModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/31.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<HomeHotFriendsRememberModel.ObjBean.VideoBean> data;
    Context context;
    public VideoAdapter(List<HomeHotFriendsRememberModel.ObjBean.VideoBean> data){
        this.data = data;
    }
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_video, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        VideoAdapter.ViewHolder holder = new VideoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getImg()).into(holder.videoIv);
        holder.videoTv.setText(data.get(position).getVname());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView videoIv;
        TextView videoTv;
        public ViewHolder(View itemView) {
            super(itemView);
            videoIv = (itemView).findViewById(R.id.home_hot_video_iv);
            videoTv = (itemView).findViewById(R.id.home_hot_video_tv);
        }
    }
}