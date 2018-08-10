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
import com.yiwo.friendscometogether.model.DetailsRememberModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/17.
 */

public class DetailsOfFriendsUpDataAdapter extends RecyclerView.Adapter<DetailsOfFriendsUpDataAdapter.ViewHolder> {

    private Context context;
    private List<DetailsRememberModel.ObjBean.RenewBean.PicBean> data;

    public DetailsOfFriendsUpDataAdapter(List<DetailsRememberModel.ObjBean.RenewBean.PicBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_details_of_friends, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getFfpurl()).into(holder.ivTitle);
        holder.tvContent.setText(data.get(position).getFfptitle());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTitle;
        private TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTitle = itemView.findViewById(R.id.activity_details_of_friends_rv_iv_title);
            tvContent = itemView.findViewById(R.id.activity_details_of_friends_rv_rv_tv_content);
        }
    }

}
