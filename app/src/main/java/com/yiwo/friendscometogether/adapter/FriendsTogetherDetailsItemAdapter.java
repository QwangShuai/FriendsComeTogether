package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.FriendsTogetherDetailsModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/19.
 */

public class FriendsTogetherDetailsItemAdapter extends RecyclerView.Adapter<FriendsTogetherDetailsItemAdapter.ViewHolder> {
    List<FriendsTogetherDetailsModel.ObjBean.InfoListBean> data;
    private Context context;

    public FriendsTogetherDetailsItemAdapter(List<FriendsTogetherDetailsModel.ObjBean.InfoListBean> data){
        this.data=data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_friend_together,parent,false);
        ScreenAdapterTools.getInstance().loadView(view);
        FriendsTogetherDetailsItemAdapter.ViewHolder holder = new FriendsTogetherDetailsItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
