package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.PayFragmentModel;
import com.yiwo.friendscometogether.pages.DetailsToBePaidActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/7/18.
 */

public class FragmentToPayAdapter extends RecyclerView.Adapter<FragmentToPayAdapter.ViewHolder> {

    private Context context;
    private List<PayFragmentModel.ObjBean> data;
    private OnPayListener listener;

    public void setOnPayListener(OnPayListener listener){
        this.listener = listener;
    }

    public FragmentToPayAdapter(List<PayFragmentModel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_to_pay, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.rlDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, DetailsToBePaidActivity.class);
                intent.putExtra("order_id", data.get(position).getOID());
                context.startActivity(intent);
            }
        });
        holder.tvTitle.setText(data.get(position).getTitle());
        if (!TextUtils.isEmpty(data.get(position).getPf_pic())) {
            Picasso.with(context).load(data.get(position).getPf_pic()).into(holder.iv);
        }
        holder.tvContent.setText(data.get(position).getInfo());
        holder.tvTime.setText("行程时间: " + data.get(position).getTime_info());
        holder.tvPeopleNum.setText("参加人数: " + data.get(position).getJoin_num());
        holder.tvPrice.setText("合计费用: " + data.get(position).getOpaymoney());
        holder.tvPriceDetails.setText(data.get(position).getPrice_type());
        holder.tvStatus.setText(data.get(position).getStatus());
        if(data.get(position).getOrder_type().equals("7")){
            holder.tvInvitation.setVisibility(View.GONE);
            holder.tvPay.setVisibility(View.GONE);
        }
        holder.tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPay(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlDetails;
        private TextView tvTitle;
        private ImageView iv;
        private TextView tvContent;
        private TextView tvTime;
        private TextView tvPeopleNum;
        private TextView tvPrice;
        private TextView tvPriceDetails;
        private TextView tvStatus;
        private TextView tvPay;
        private TextView tvCancelTrip;
        private TextView tvInvitation;

        public ViewHolder(View itemView) {
            super(itemView);
            rlDetails = itemView.findViewById(R.id.fragment_to_pay_rv_rl_details);
            tvTitle = itemView.findViewById(R.id.fragment_to_pay_rv_tv_title);
            iv = itemView.findViewById(R.id.fragment_to_pay_rv_iv);
            tvContent = itemView.findViewById(R.id.fragment_to_pay_rv_tv_content);
            tvTime = itemView.findViewById(R.id.fragment_to_pay_rv_tv_time);
            tvPeopleNum = itemView.findViewById(R.id.fragment_to_pay_rv_tv_people_num);
            tvPrice = itemView.findViewById(R.id.fragment_to_pay_rv_tv_price);
            tvPriceDetails = itemView.findViewById(R.id.fragment_to_pay_rv_tv_price_details);
            tvStatus = itemView.findViewById(R.id.fragment_to_pay_rv_tv_status);
            tvPay = itemView.findViewById(R.id.fragment_to_pay_rv_tv_payment);
            tvCancelTrip = itemView.findViewById(R.id.fragment_to_pay_rv_tv_cancle_trip);
            tvInvitation = itemView.findViewById(R.id.fragment_to_pay_rv_tv_invitation);
        }
    }

    public interface OnPayListener{
        void onPay(int position);
    }

}
