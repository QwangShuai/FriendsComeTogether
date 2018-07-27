package com.yiwo.friendscometogether.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.UserRememberModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.EditorFriendRememberActivity;
import com.yiwo.friendscometogether.utils.TokenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/7/23.
 */

public class MyFriendRememberAdapter extends RecyclerView.Adapter<MyFriendRememberAdapter.ViewHolder> {

    private Context context;
    private List<UserRememberModel.ObjBean> data;

    private OnDeleteListener listener;

    public void setOnDeleteListener(OnDeleteListener listener){
        this.listener = listener;
    }

    public MyFriendRememberAdapter(List<UserRememberModel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_my_friend_remember, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(data.get(position).getFmtitle());
        Picasso.with(context).load(data.get(position).getFmpic()).into(holder.ivTitle);
        holder.tvStart.setText("开始时间: " + data.get(position).getFmgotime());
        holder.tvEnd.setText("结束时间: " + data.get(position).getFmendtime());
        holder.tvPrice.setText("人均费用: " + data.get(position).getPercapitacost());
        holder.tvBrowse.setText("浏览: " + data.get(position).getFmlook());
        holder.tvFocus.setText("关注: " + data.get(position).getFmfavorite());
        holder.rlEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id", data.get(position).getFmID());
                intent.setClass(context, EditorFriendRememberActivity.class);
                context.startActivity(intent);
            }
        });
        holder.rlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView ivTitle;
        private TextView tvStart;
        private TextView tvEnd;
        private TextView tvPrice;
        private TextView tvBrowse;
        private TextView tvFocus;
        private RelativeLayout rlEditor;
        private RelativeLayout rlDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.activity_my_friend_remember_rv_tv_title);
            ivTitle = itemView.findViewById(R.id.activity_my_friend_remember_rv_iv_title);
            tvStart = itemView.findViewById(R.id.activity_my_friend_remember_rv_tv_start);
            tvEnd = itemView.findViewById(R.id.activity_my_friend_remember_rv_tv_end);
            tvPrice = itemView.findViewById(R.id.activity_my_friend_remember_rv_tv_price);
            tvBrowse = itemView.findViewById(R.id.activity_my_friend_remember_rv_tv_browse_num);
            tvFocus = itemView.findViewById(R.id.activity_my_friend_remember_rv_tv_focus_num);
            rlEditor = itemView.findViewById(R.id.activity_my_friend_remember_rv_rl_editor);
            rlDelete = itemView.findViewById(R.id.activity_my_friend_remember_rv_rl_delete);
        }
    }

    public interface OnDeleteListener{
        void onDelete(int i);
    }

}
