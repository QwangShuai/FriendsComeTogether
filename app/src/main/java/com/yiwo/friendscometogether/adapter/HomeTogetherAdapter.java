package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.custom.CImageView;
import com.yiwo.friendscometogether.model.HomeTogetherModel;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/2.
 */

public class HomeTogetherAdapter extends RecyclerView.Adapter<HomeTogetherAdapter.ViewHolder>{
    private Context context;
    private List<HomeTogetherModel.ObjBean> data;
    SpImp spImp;
    //    private List<HomeHotFriendsRememberModel.ObjBean.VideoBean> list;
    public HomeTogetherAdapter(List<HomeTogetherModel.ObjBean> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home_together, parent, false);
        this.context = parent.getContext();
        spImp = new SpImp(parent.getContext());
        ScreenAdapterTools.getInstance().loadView(view);
        HomeTogetherAdapter.ViewHolder holder = new HomeTogetherAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(!StringUtils.isEmpty(data.get(position).getPfpic()))
            Picasso.with(context).load(data.get(position).getPfpic()).into(holder.picIv);
        if(!StringUtils.isEmpty(data.get(position).getUpicurl()))
            Picasso.with(context).load(data.get(position).getUpicurl()).into(holder.headIv);
        holder.bgRl.setBackgroundResource(data.get(position).getCaptain().equals("1") ? R.mipmap.level_golden_yellow : R.mipmap.level_red);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picIv;
        TextView titleTv;
        TextView levelTv;
        RelativeLayout bgRl;
        CImageView headIv;
        public ViewHolder(View itemView) {
            super(itemView);
            bgRl = (itemView).findViewById(R.id.item_levelBg);
            headIv = (itemView).findViewById(R.id.headIv);
            levelTv = (itemView).findViewById(R.id.levelTv);
            picIv = (itemView).findViewById(R.id.home_together_pic_iv);
            titleTv = (itemView).findViewById(R.id.home_together_title_tv);
        }
    }
}
