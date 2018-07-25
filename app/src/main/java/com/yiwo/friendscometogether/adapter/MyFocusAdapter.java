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
import com.yiwo.friendscometogether.model.UserFocusModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/19.
 */

public class MyFocusAdapter extends RecyclerView.Adapter<MyFocusAdapter.ViewHolder> {

    private Context context;
    private List<UserFocusModel.ObjBean> data;

    public MyFocusAdapter(List<UserFocusModel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_my_focus, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getUpicurl()).into(holder.ivAvatar);
        holder.tvNickname.setText("昵称: " + data.get(position).getUsername());
        Picasso.with(context).load(data.get(position).getUsersex().equals("0") ? R.mipmap.nan : R.mipmap.nv).into(holder.ivSex);
        holder.tvArticleNum.setText("文章: " + data.get(position).getArticle_num());
        holder.tvLikeNum.setText("粉丝: " + data.get(position).getLike_num());
        holder.tvActivityId.setText(data.get(position).getActivity_id().equals("0") ? "活动状态: 未参加活动" : "活动状态: 活动中");
        holder.tv2.setText(data.get(position).getStatus() == 0 ? "已结束" : "进行中");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvNickname;
        private ImageView ivSex;
        private TextView tvArticleNum;
        private TextView tvLikeNum;
        private TextView tvActivityId;
        private TextView tv2;
        private TextView tv1;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.activity_my_focus_rv_iv_avatar);
            tvNickname = itemView.findViewById(R.id.activity_my_focus_rv_tv_nickname);
            ivSex = itemView.findViewById(R.id.activity_my_focus_rv_iv_sex);
            tvArticleNum = itemView.findViewById(R.id.activity_my_focus_rv_tv_article_num);
            tvLikeNum = itemView.findViewById(R.id.activity_my_focus_rv_tv_like_num);
            tvActivityId = itemView.findViewById(R.id.activity_my_focus_rv_tv_activity_id);
            tv2 = itemView.findViewById(R.id.activity_my_focus_rv_tv_2);
            tv1 = itemView.findViewById(R.id.activity_my_focus_rv_tv_1);
        }
    }

}
