package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class MyPicturesAdapter extends RecyclerView.Adapter<MyPicturesAdapter.ViewHolder> {

    private static final int TYPE_ADD = 1;
    private static final int TYPE_PIC = 2;

    private Context context;
    private List<String> data;

    private onItemClickListener listener;

    public MyPicturesAdapter(List<String> data) {
        this.data = data;
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_my_pictures, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ADD) {
            holder.rlAdd.setVisibility(View.VISIBLE);
            holder.iv.setVisibility(View.GONE);
        } else {
            holder.rlAdd.setVisibility(View.GONE);
            holder.iv.setVisibility(View.VISIBLE);
        }
        holder.rlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ADD;
        } else {
            return TYPE_PIC;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlAdd;
        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            rlAdd = itemView.findViewById(R.id.activity_my_pictures_rv_rl_add);
            iv = itemView.findViewById(R.id.activity_my_pictures_rv_iv);
        }
    }

    public interface onItemClickListener{
        void onClick(View view);
    }

}
