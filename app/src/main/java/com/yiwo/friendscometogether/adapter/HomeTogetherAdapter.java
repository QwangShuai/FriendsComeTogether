package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.custom.CImageView;
import com.yiwo.friendscometogether.model.FocusOnLeaderModel;
import com.yiwo.friendscometogether.model.HomeTogetherModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.DetailsOfFriendTogetherActivity;
import com.yiwo.friendscometogether.pages.LoginActivity;
import com.yiwo.friendscometogether.pages.OtherInformationActivity;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;
import com.yiwo.friendscometogether.utils.TokenUtils;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(!StringUtils.isEmpty(data.get(position).getPfpic()))
            Picasso.with(context).load(data.get(position).getPfpic()).into(holder.picIv);
        if(!StringUtils.isEmpty(data.get(position).getUpicurl()))
            Picasso.with(context).load(data.get(position).getUpicurl()).into(holder.headIv);
        holder.bgRl.setBackgroundResource(data.get(position).getSign().equals("1") ? R.mipmap.level_golden_yellow : R.mipmap.level_red);
//        if(!StringUtils.isEmpty(data.get(position).getCaptain())&&!data.get(position).getCaptain().equals("0")){
//            holder.levelTv.setText(data.get(position).getSign().equals("0")?"普通领队":"签约领队");
//        } else {
//            holder.levelTv.setText("暂无领队");
//        }

        holder.titleTv.setText(data.get(position).getPftitle());
        holder.contentTv.setText(data.get(position).getPfcontent());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spImp.getUID().equals("0")){
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                    Intent intent = new Intent();
                    intent.setClass(context, DetailsOfFriendTogetherActivity.class);
                    intent.putExtra("pfID", data.get(position).getPfID());
                    context.startActivity(intent);
                }
            }
        });
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
        TextView contentTv;
        RelativeLayout rl;
        public ViewHolder(View itemView) {
            super(itemView);
            bgRl = (itemView).findViewById(R.id.item_levelBg);
            headIv = (itemView).findViewById(R.id.headIv);
            levelTv = (itemView).findViewById(R.id.levelTv);
            picIv = (itemView).findViewById(R.id.home_together_pic_iv);
            titleTv = (itemView).findViewById(R.id.home_together_title_tv);
            contentTv = (itemView).findViewById(R.id.home_together_title_tv);
            rl = (itemView).findViewById(R.id.home_together__item_rl);
        }
    }
}
