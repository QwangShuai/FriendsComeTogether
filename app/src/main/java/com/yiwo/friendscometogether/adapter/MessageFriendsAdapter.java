package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.MessageFriendsModel;

import java.util.List;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class MessageFriendsAdapter extends RecyclerView.Adapter<MessageFriendsAdapter.ViewHolder> {

    private Context context;
    private List<MessageFriendsModel.ObjBean> data;
    private OnFriendsListener listener;

    public void setOnFriendsListener(OnFriendsListener listener){
        this.listener = listener;
    }

    public MessageFriendsAdapter(List<MessageFriendsModel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_message_friends, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.titleTv.setText(data.get(position).getTitle());
        if(!TextUtils.isEmpty(data.get(position).getPic())){
            Picasso.with(context).load(data.get(position).getPic()).into(holder.picIv);
        }
        holder.tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFriend(1, position);
            }
        });
        holder.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFriend(2, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private ImageView picIv;
        private TextView tvNo;
        private TextView tvOk;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (itemView).findViewById(R.id.message_view_title_tv);
            picIv = (itemView).findViewById(R.id.message_view_pic_iv);
            tvNo = itemView.findViewById(R.id.tv_no);
            tvOk = itemView.findViewById(R.id.tv_ok);
        }
    }

    public interface OnFriendsListener{
        void onFriend(int type, int position);
    }

}
