package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.FocusOnLeaderModel;
import com.yiwo.friendscometogether.model.HomeTogetherModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.DetailsOfFriendTogetherActivity;
import com.yiwo.friendscometogether.pages.LoginActivity;
import com.yiwo.friendscometogether.pages.OtherInformationActivity;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;
import com.yiwo.friendscometogether.utils.TokenUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/2.
 */

public class HomeTogetherAdapter extends RecyclerView.Adapter<HomeTogetherAdapter.ViewHolder>{
    private Context context;
    private List<HomeTogetherModel.ObjBean> data;
    SpImp spImp;
    private OnFocusListener listener;

    public void setOnFocusListener(OnFocusListener listener){
        this.listener = listener;
    }

    //    private List<HomeHotFriendsRememberModel.ObjBean.VideoBean> list;
    public HomeTogetherAdapter(List<HomeTogetherModel.ObjBean> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home_together, parent, false);
        this.context = parent.getContext();
        spImp = new SpImp(parent.getContext());
        ScreenAdapterTools.getInstance().loadView(view);
        HomeTogetherAdapter.ViewHolder holder = new HomeTogetherAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(!StringUtils.isEmpty(data.get(position).getPfpic()))
            Picasso.with(context).load(data.get(position).getPfpic()).into(holder.picIv);
        if(!StringUtils.isEmpty(data.get(position).getUpicurl()))
            Picasso.with(context).load(data.get(position).getUpicurl()).into(holder.headIv);
//        if(!StringUtils.isEmpty(data.get(position).getCaptain())&&!data.get(position).getCaptain().equals("0")){
//            holder.levelTv.setText(data.get(position).getSign().equals("0")?"普通领队":"签约领队");
//        } else {
//            holder.levelTv.setText("暂无领队");
//        }

        holder.nickname.setText(data.get(position).getUsername());
        holder.time.setText(data.get(position).getPftime());
        holder.lookNum.setText(data.get(position).getLook_num());
        holder.commentNum.setText(data.get(position).getComment_num());
        if(data.get(position).getFollow().equals("0")){
            holder.btnFocus.setText("+关注");
        }else {
            holder.btnFocus.setText("已关注");
        }

        holder.titleTv.setText(data.get(position).getPftitle());
        holder.contentTv.setText(data.get(position).getPfcontent());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spImp.getUID().equals("0")){
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                    Intent intent = new Intent();
                    intent.setClass(context, DetailsOfFriendTogetherActivity.class);
                    intent.putExtra("pfID", data.get(position).getPfID());
                    context.startActivity(intent);
                }
            }
        });
        holder.btnFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spImp.getUID().equals("0")){
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                    listener.onFocus(position);
                }
            }
        });
        holder.headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("uid", data.get(position).getCaptain());
                intent.setClass(context, OtherInformationActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView picIv;
        private TextView titleTv;
        private TextView levelTv;
        private ImageView headIv;
        private TextView contentTv;
        private LinearLayout rl;
        private TextView nickname;
        private TextView time;
        private TextView lookNum;
        private TextView commentNum;
        private Button btnFocus;

        public ViewHolder(View itemView) {
            super(itemView);
            headIv = (itemView).findViewById(R.id.headIv);
            levelTv = (itemView).findViewById(R.id.levelTv);
            picIv = (itemView).findViewById(R.id.home_together_pic_iv);
            titleTv = (itemView).findViewById(R.id.home_together_title_tv);
            contentTv = (itemView).findViewById(R.id.home_together_content_tv);
            rl = (itemView).findViewById(R.id.home_together__item_rl);
            nickname = itemView.findViewById(R.id.nickname);
            time = itemView.findViewById(R.id.time);
            lookNum = itemView.findViewById(R.id.look_num);
            commentNum = itemView.findViewById(R.id.comment_num);
            btnFocus = itemView.findViewById(R.id.activity_details_of_friends_btn_focus);
        }
    }

    public interface OnFocusListener{
        void onFocus(int position);
    }

}
