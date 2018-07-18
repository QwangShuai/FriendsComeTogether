package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.FriendsRememberModel;
import com.yiwo.friendscometogether.pages.DetailsOfFriendsActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FriendRememberUpDataAdapter extends RecyclerView.Adapter<FriendRememberUpDataAdapter.ViewHolder> {

    private List<FriendsRememberModel.ObjBean> data;
    private Context context;

    public FriendRememberUpDataAdapter(List<FriendsRememberModel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_friend_remember, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context).load(data.get(position).getFmpic()).into(holder.ivTitle);
        holder.tvTitle.setText(data.get(position).getFmtitle());
        holder.tvContent.setText(data.get(position).getFmcontent());
        Picasso.with(context).load(data.get(position).getUpicurl()).into(holder.ivAvatar);
        holder.tvNickname.setText(data.get(position).getUsername());
        holder.tvUsergrade.setText("LV" + data.get(position).getUsergrade());
        holder.tvCreateTime.setText(data.get(position).getFmtime());
        holder.tvLookNum.setText(data.get(position).getFmlook());
        holder.tvCommentNum.setText(data.get(position).getFmcomment());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, DetailsOfFriendsActivity.class);
                intent.putExtra("fmid", data.get(position).getFmID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTitle;
        private TextView tvTitle;
        private TextView tvContent;
        private ImageView ivAvatar;
        private TextView tvNickname;
        private TextView tvUsergrade;
        private TextView tvCreateTime;
        private TextView tvLookNum;
        private TextView tvCommentNum;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTitle = itemView.findViewById(R.id.fragment_friend_remember_rv_iv_title);
            tvTitle = itemView.findViewById(R.id.fragment_friend_remember_rv_tv_title);
            tvContent = itemView.findViewById(R.id.fragment_friend_remember_rv_tv_content);
            ivAvatar = itemView.findViewById(R.id.fragment_friend_remember_rv_iv_avatar);
            tvNickname = itemView.findViewById(R.id.fragment_friend_remember_rv_tv_nickname);
            tvUsergrade = itemView.findViewById(R.id.fragment_friend_remember_rv_tv_usergrade);
            tvCreateTime = itemView.findViewById(R.id.fragment_friend_remember_rv_tv_c_time);
            tvLookNum = itemView.findViewById(R.id.fragment_friend_remember_rv_tv_look_num);
            tvCommentNum = itemView.findViewById(R.id.fragment_friend_remember_rv_tv_comment_num);
            ll = itemView.findViewById(R.id.fragment_friend_remember_rv_ll);
        }
    }

}
