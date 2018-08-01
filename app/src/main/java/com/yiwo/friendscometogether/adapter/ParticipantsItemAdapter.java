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

public class ParticipantsItemAdapter extends RecyclerView.Adapter<ParticipantsItemAdapter.ViewHolder>{
    private Context context;
    private List<FriendsTogetherDetailsModel.ObjBean.UserListBean> data;

    public ParticipantsItemAdapter(List<FriendsTogetherDetailsModel.ObjBean.UserListBean> data) {
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participants, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(data.size()!=0){
            if(!StringUtils.isEmpty(data.get(position).getUserpic())){
                Picasso.with(context).load(data.get(position).getUserpic()).into(holder.headIv);
            }
            holder.nicknameTv.setText(data.get(position).getUsername());
            if(data.get(position).getNum().equals("1")){
                holder.numTv.setVisibility(View.INVISIBLE);
            } else {
                holder.numTv.setText(data.get(position).getNum());
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView numTv;
        private ImageView headIv;
        private TextView nicknameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            numTv = (itemView).findViewById(R.id.item_num);
            headIv = (itemView).findViewById(R.id.item_head);
            nicknameTv = (itemView).findViewById(R.id.item_nickname);
        }
    }

}
