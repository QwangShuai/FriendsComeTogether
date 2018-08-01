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
import com.yiwo.friendscometogether.model.LookHistoryModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/19.
 */

public class LookHistoryAdapter extends RecyclerView.Adapter<LookHistoryAdapter.ViewHolder> {

    private Context context;
    private List<LookHistoryModel.ObjBean> data;

    public LookHistoryAdapter(List<LookHistoryModel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_look_history, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getPic_url()).into(holder.ivTitle);
        holder.tvTime.setText(data.get(position).getLook_time());
        holder.tvTitle.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTitle;
        private TextView tvTime;
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTitle = itemView.findViewById(R.id.activity_look_history_rv_iv);
            tvTime = itemView.findViewById(R.id.activity_look_history_rv_tv_time);
            tvTitle = itemView.findViewById(R.id.activity_look_history_rv_tv_title);
        }
    }

}
