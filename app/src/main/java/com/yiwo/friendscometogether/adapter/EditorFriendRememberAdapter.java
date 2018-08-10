package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.EditorFriendRememberModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/27.
 */

public class EditorFriendRememberAdapter extends RecyclerView.Adapter<EditorFriendRememberAdapter.ViewHolder> {

    private Context context;
    private List<EditorFriendRememberModel.ObjBean.RenewListBean> data;

    public EditorFriendRememberAdapter(List<EditorFriendRememberModel.ObjBean.RenewListBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_editor_friend_remember, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getFftitle());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.activity_editor_friend_remember_rv_tv);
            rl = itemView.findViewById(R.id.activity_editor_friend_remember_rv_rl);
        }
    }

}
