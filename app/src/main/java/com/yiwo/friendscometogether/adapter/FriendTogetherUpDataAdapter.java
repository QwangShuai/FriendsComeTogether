package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.FocusOnToFriendTogetherModel;
import com.yiwo.friendscometogether.model.FriendsTogethermodel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.DetailsOfFriendTogetherActivity;
import com.yiwo.friendscometogether.pages.LoginActivity;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;
import com.yiwo.friendscometogether.utils.TokenUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/7/18.
 */

public class FriendTogetherUpDataAdapter extends RecyclerView.Adapter<FriendTogetherUpDataAdapter.ViewHolder> {
    List<FriendsTogethermodel.ObjBean> data;
    private Context context;
    View v;
    ImageView iv;
    SpImp spImp;

    public FriendTogetherUpDataAdapter(List<FriendsTogethermodel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_friend_together, parent, false);
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
        spImp = new SpImp(context);
        iv = (ImageView) v.findViewById(R.id.item_pic);
        ScreenAdapterTools.getInstance().loadView(view);
        FriendTogetherUpDataAdapter.ViewHolder holder = new FriendTogetherUpDataAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.i("123456", data.get(position).getFocusOn());
        if (!StringUtils.isEmpty(data.get(position).getPfpic())) {
            Picasso.with(context).load(data.get(position).getPfpic()).into(holder.picIv);
        }
        holder.titleTv.setText(data.get(position).getPftitle());
        holder.contentTv.setText(data.get(position).getPfcontent());
        if (!StringUtils.isEmpty(data.get(position).getUpicurl())) {
            Picasso.with(context).load(data.get(position).getUpicurl()).into(holder.headIv);
        }
        if(!StringUtils.isEmpty(data.get(position).getCaptain())&&!data.get(position).getCaptain().equals("0")){
            holder.levelTv.setText(data.get(position).getSign().equals("1") ? "签约领队" : "普通领队");
        } else {
            holder.levelTv.setText("暂无领队");
        }

        holder.levelBg.setBackgroundResource(data.get(position).getUsergrade().equals("1") ? R.mipmap.level_golden_yellow : R.mipmap.level_red);
        holder.personTv.setText(data.get(position).getHave_num() + "/" + data.get(position).getPfpeople());
        holder.focusOnIv.setImageResource(data.get(position).getFocusOn().equals("0") ? R.mipmap.focus_on_empty_y : R.mipmap.focus_on_y);
        holder.focus_on_activitiesLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = spImp.getUID();
                if(userID.equals("0")){
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else {
                    ViseHttp.POST(NetConfig.focusOnToFriendTogetherUrl)
                            .addParam("app_key", TokenUtils.getToken(NetConfig.BaseUrl + NetConfig.focusOnToFriendTogetherUrl))
                            .addParam("userID", userID)
                            .addParam("pfID", data.get(position).getPfID())
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    FocusOnToFriendTogetherModel model = new Gson().fromJson(result, FocusOnToFriendTogetherModel.class);
                                    if (model.getCode() == 200) {
                                        if (model.getObj().equals("1")) {
                                            holder.focusOnIv.setImageResource(R.mipmap.focus_on_y);
//                                        Toast.makeText(context, "关注成功", Toast.LENGTH_SHORT).show();
                                        } else {
                                            holder.focusOnIv.setImageResource(R.mipmap.focus_on_empty_y);
//                                        Toast.makeText(context, "取消成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {

                                }
                            });
                }

            }
        });
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        if (data.get(position).getAll_u_pic().size() < 8) {
            for (int i = 0; i < data.get(position).getAll_u_pic().size(); i++) {
                Picasso.with(context).load(data.get(position).getAll_u_pic().get(i)).into(iv);
                if (iv.getParent() != null) {
                    ((ViewGroup) iv.getParent()).removeView(iv);
                    holder.vessel.addView(iv);
                }

            }
        } else {
            for (int i = 0; i < 8; i++) {
                if(!StringUtils.isEmpty(data.get(position).getAll_u_pic().get(i))){
                    Picasso.with(context).load(data.get(position).getAll_u_pic().get(i)).into(iv);
                }
                if (iv.getParent() != null) {
                    ((ViewGroup) iv.getParent()).removeView(iv);
                    holder.vessel.addView(iv);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picIv;
        private TextView titleTv;
        private TextView contentTv;
        private LinearLayout vessel;
        private ImageView headIv;
        private ImageView focusOnIv;
        private RelativeLayout levelBg;
        private TextView levelTv;
        private TextView personTv;
        private LinearLayout look_overLl;
        private LinearLayout consult_leaderLl;
        private LinearLayout focus_on_activitiesLl;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            picIv = (itemView).findViewById(R.id.fragment_friend_together_rv_iv_title);
            titleTv = (itemView).findViewById(R.id.titleTv);
            contentTv = (itemView).findViewById(R.id.contentTv);
            vessel = (itemView).findViewById(R.id.vessel);
            levelBg = (itemView).findViewById(R.id.item_levelBg);
            headIv = (itemView).findViewById(R.id.headIv);
            levelTv = (itemView).findViewById(R.id.levelTv);
            personTv = (itemView).findViewById(R.id.item_person);
            look_overLl = (itemView).findViewById(R.id.look_overLl);
            focusOnIv = (itemView).findViewById(R.id.focus_on_iv);
            consult_leaderLl = (itemView).findViewById(R.id.consult_leaderLl);
            focus_on_activitiesLl = (itemView).findViewById(R.id.focus_on_activitiesLl);
            ll = (itemView).findViewById(R.id.recyclerview_fragment_friend_together_ll);
        }
    }
}
