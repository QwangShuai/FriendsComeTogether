package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.HomeHotFriendsRememberModel;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2018/7/17.
 */

public class HomeHotAdapter extends RecyclerView.Adapter<HomeHotAdapter.ViewHolder> {
    private Context context;
    private List<HomeHotFriendsRememberModel.ObjBean.InfoBean> data;
//    private List<HomeHotFriendsRememberModel.ObjBean.VideoBean> list;
    public HomeHotAdapter(List<HomeHotFriendsRememberModel.ObjBean.InfoBean> data){
        this.data = data;
    }

    @Override
    public HomeHotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home_hot, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        HomeHotAdapter.ViewHolder holder = new HomeHotAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        if(data.get(position).getVideo().size()==0){
            Picasso.with(context).load(data.get(position).getFmpic()).into(holder.titleIv);
            holder.titleTv.setText(data.get(position).getFmtitle());
        Log.i("00000000",data.get(position).getFmcomment());
            holder.contentTv.setText(data.get(position).getFmcontent()+"");
            Picasso.with(context).load(data.get(position).getUpicurl()).into(holder.headIv);
            holder.timeTv.setText(data.get(position).getFmtime());
            holder.viewsTv.setText(data.get(position).getFmlook());
            holder.messageTv.setText(data.get(position).getFmcomment());
            holder.nameTv.setText(data.get(position).getUsername());
//        } else {
//            list = data.get(position).getVideo();
//            holder.childrenLl.setVisibility(View.GONE);
//            ((ViewGroup)videoPl.getParent()).removeView(videoPl);
//            holder.vesselLl.addView(videoPl);
//           videoPl.setAdapter(new PileLayoutVideoAdapter(context,data.get(position).getVideo()));
//        }

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView contentTv;
        private TextView nameTv;
        private TextView viewsTv;
        private TextView messageTv;
        private TextView timeTv;
        private ImageView titleIv;
        private ImageView headIv;
        private LinearLayout vesselLl;
        private LinearLayout childrenLl;

        public ViewHolder(View itemView) {
            super(itemView);
            titleIv = itemView.findViewById(R.id.home_hot_itemIv);
            titleTv = itemView.findViewById(R.id.home_hot_title);
            contentTv = itemView.findViewById(R.id.home_hot_content);
            headIv = itemView.findViewById(R.id.home_hot_itemHeadIv);
            nameTv = itemView.findViewById(R.id.home_hot_itemNameTv);
            timeTv = itemView.findViewById(R.id.home_hot_itemTimeTv);
            viewsTv = itemView.findViewById(R.id.home_hot_itemViewsTv);
            messageTv = itemView.findViewById(R.id.home_hot_itemMessageTv);
            vesselLl = itemView.findViewById(R.id.home_hot_vessel);
            childrenLl = itemView.findViewById(R.id.home_hot_children);
        }
    }
}
