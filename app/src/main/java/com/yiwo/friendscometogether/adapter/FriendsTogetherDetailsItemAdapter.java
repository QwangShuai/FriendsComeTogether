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
import com.yiwo.friendscometogether.model.FriendsTogetherDetailsModel;
import com.yiwo.friendscometogether.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/7/19.
 */

public class FriendsTogetherDetailsItemAdapter extends RecyclerView.Adapter<FriendsTogetherDetailsItemAdapter.ViewHolder> {
    List<FriendsTogetherDetailsModel.ObjBean.InfoListBean.ImageListBean> data;
    private Context context;

    public FriendsTogetherDetailsItemAdapter(List<FriendsTogetherDetailsModel.ObjBean.InfoListBean.ImageListBean> data){
        this.data=data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_pic,parent,false);
        ScreenAdapterTools.getInstance().loadView(view);
        FriendsTogetherDetailsItemAdapter.ViewHolder holder = new FriendsTogetherDetailsItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(!StringUtils.isEmpty(data.get(position).getPic())){
            Picasso.with(context).load(data.get(position).getPic()).into(holder.item_picIv);
        }
        if(!StringUtils.isEmpty(data.get(position).getText_info())){
            holder.item_titleTv.setText(data.get(position).getText_info());
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_picIv;
        private TextView item_titleTv;
        public ViewHolder(View itemView) {
            super(itemView);
            item_picIv = (itemView).findViewById(R.id.item_picIv);
            item_titleTv = (itemView).findViewById(R.id.item_titleTv);
        }
    }
}
