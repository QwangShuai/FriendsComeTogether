package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.FriendsTogethermodel;
import com.yiwo.friendscometogether.pages.DetailsOfFriendTogetherActivity;
import com.yiwo.friendscometogether.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/7/18.
 */

public class FriendTogetherUpDataAdapter extends RecyclerView.Adapter<FriendTogetherUpDataAdapter.ViewHolder>{
    List<FriendsTogethermodel.ObjBean> data;
    private Context context;
    View v;
    ImageView iv;
    public FriendTogetherUpDataAdapter(List<FriendsTogethermodel.ObjBean> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_friend_together,parent,false);
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head,parent,false);
        iv = (ImageView) v.findViewById(R.id.item_pic) ;
        ScreenAdapterTools.getInstance().loadView(view);
        FriendTogetherUpDataAdapter.ViewHolder holder = new FriendTogetherUpDataAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context).load(data.get(position).getPfpic()).into(holder.picIv);
        holder.titleTv.setText(data.get(position).getPftitle());
        holder.contentTv.setText(data.get(position).getPfcontent());
        if(!StringUtils.isEmpty(data.get(position).getUpicurl())){
            Picasso.with(context).load(data.get(position).getUpicurl()).into(holder.headIv);
        }
        holder.levelTv.setText(data.get(position).getUsergrade()==1?"签约领队":"普通领队");
        holder.levelBg.setBackgroundResource(data.get(position).getUsergrade()==1?R.mipmap.level_golden_yellow:R.mipmap.level_red);
        holder.personTv.setText(data.get(position).getHave_num()+"/"+data.get(position).getPfpeople());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, DetailsOfFriendTogetherActivity.class);
                intent.putExtra("fmid", data.get(position).getPfID());
                context.startActivity(intent);
            }
        });
        if(data.get(position).getAll_u_pic().size()<8){
            for(int i=0;i<data.get(position).getAll_u_pic().size();i++){
                Picasso.with(context).load(data.get(position).getAll_u_pic().get(i)).into(iv);
                if(iv.getParent()!=null){
                    ((ViewGroup)iv.getParent()).removeView(iv);
                    holder.vessel.addView(iv);
                }

            }
        } else {
            for(int i=0;i<8;i++){
                Picasso.with(context).load(data.get(position).getAll_u_pic().get(i)).into(iv);
                holder.vessel.addView(iv);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picIv;
        private TextView titleTv;
        private TextView contentTv;
        private LinearLayout vessel;
        private ImageView headIv;
        private RelativeLayout levelBg;
        private TextView levelTv;
        private TextView personTv;
        private LinearLayout look_overLl;
        private LinearLayout consult_leaderLl;
        private LinearLayout focus_on_activitiesLl;
        private LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            picIv = (itemView).findViewById(R.id.fragment_friend_together_rv_iv_title);
            titleTv = (itemView).findViewById(R.id.titleTv);
            contentTv = (itemView).findViewById(R.id.contentTv);
            vessel = (itemView).findViewById(R.id.vessel);
            levelBg = (itemView).findViewById(R.id.item_levelBg);
            headIv = (itemView).findViewById(R.id.headIv);
            levelTv = (itemView).findViewById(R.id.levelTv);
            personTv = (itemView).findViewById(R.id.item_person);
            look_overLl = (itemView).findViewById(R.id.look_overLl);
            consult_leaderLl = (itemView).findViewById(R.id.consult_leaderLl);
            focus_on_activitiesLl = (itemView).findViewById(R.id.focus_on_activitiesLl);
            ll = (itemView).findViewById(R.id.recyclerview_fragment_friend_together_ll);
        }
    }
}
