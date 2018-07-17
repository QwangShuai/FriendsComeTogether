package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yiwo.friendscometogether.R;

import java.util.List;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FriendRememberUpDataAdapter extends RecyclerView.Adapter<FriendRememberUpDataAdapter.ViewHolder> {

    private List<String> data;
    private Context context;

    public FriendRememberUpDataAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_friend_remember, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position)).into(holder.ivTitle);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTitle = itemView.findViewById(R.id.fragment_friend_remember_rv_iv_title);
        }
    }

}
