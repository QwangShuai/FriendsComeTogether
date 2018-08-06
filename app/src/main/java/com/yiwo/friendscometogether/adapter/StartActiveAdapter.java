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
import com.yiwo.friendscometogether.model.InitiativesModel;
import com.yiwo.friendscometogether.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class StartActiveAdapter extends RecyclerView.Adapter<StartActiveAdapter.ViewHolder> {

    private Context context;
    private List<InitiativesModel.ObjBean> data;

    public StartActiveAdapter(List<InitiativesModel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_start_active, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(!StringUtils.isEmpty(data.get(position).getPfpic())){
            Picasso.with(context).load(data.get(position).getPfpic()).into(holder.picIv);
        }
        holder.titleTv.setText(data.get(position).getPftitle());
        holder.beginTimeTv.setText("开始时间："+data.get(position).getPfgotime());
        holder.endTimeTv.setText("结束时间："+data.get(position).getPfendtime());
        holder.priceTv.setText("人均费用："+data.get(position).getPfspend());
        holder.applyTv.setText("报名人数："+data.get(position).getJoin_num());
        holder.viewsyTv.setText("浏览："+data.get(position).getPflook());
        holder.focusOnTv.setText("关注："+data.get(position).getFocusOn());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        TextView beginTimeTv;
        TextView endTimeTv;
        TextView priceTv;
        TextView applyTv;
        ImageView picIv;
        TextView viewsyTv;
        TextView focusOnTv;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (itemView).findViewById(R.id.recyclerview_start_active_tv_title);
            beginTimeTv = (itemView).findViewById(R.id.recyclerview_start_active_tv_begin_time);
            endTimeTv = (itemView).findViewById(R.id.recyclerview_start_active_tv_end_time);
            priceTv = (itemView).findViewById(R.id.recyclerview_start_active_tv_price);
            applyTv = (itemView).findViewById(R.id.recyclerview_start_active_tv_apply_num);
            picIv = (itemView).findViewById(R.id.activity_start_active_rv_iv_title);
            viewsyTv = (itemView).findViewById(R.id.recyclerview_start_active_tv_views_num);
            focusOnTv = (itemView).findViewById(R.id.recyclerview_start_active_tv_focus_on_num);
        }
    }

}
