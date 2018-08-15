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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.custom.EditContentDialog;
import com.yiwo.friendscometogether.model.FocusOnToFriendTogetherModel;
import com.yiwo.friendscometogether.model.JoinActivemodel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.pages.DetailsOfFriendTogetherActivity;
import com.yiwo.friendscometogether.pages.EditorFriendTogetherActivity;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;
import com.yiwo.friendscometogether.utils.TokenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public class JoinActiveAdapter extends RecyclerView.Adapter<JoinActiveAdapter.ViewHolder> {

    private Context context;
    private List<JoinActivemodel.ObjBean> data;
    private SpImp spImp;

    public JoinActiveAdapter(List<JoinActivemodel.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        spImp = new SpImp(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_join_active, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (!StringUtils.isEmpty(data.get(position).getPfpic())) {
            Picasso.with(context).load(data.get(position).getPfpic()).into(holder.picIv);
        }
        holder.titleTv.setText(data.get(position).getPftitle());
        holder.beginTimeTv.setText("开始时间：" + data.get(position).getPfgotime());
        holder.endTimeTv.setText("结束时间：" + data.get(position).getPfendtime());
        holder.priceTv.setText("人均费用：" + data.get(position).getPfspend());
        holder.applyTv.setText("报名人数：" + data.get(position).getJoin_num());
        holder.viewsyTv.setText("浏览：" + data.get(position).getPflook());
        holder.focusOnTv.setText("关注：" + data.get(position).getFocusOn());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("pfID", data.get(position).getPfID());
                intent.setClass(context, DetailsOfFriendTogetherActivity.class);
                context.startActivity(intent);
            }
        });

        holder.cancleRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditContentDialog dialog = new EditContentDialog(context, new EditContentDialog.OnReturnListener() {
                    @Override
                    public void onReturn(String content) {
                        if (StringUtils.isEmpty(content)) {
                            Toast.makeText(context, "取消原因不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            ViseHttp.POST(NetConfig.cancleActivityUrl)
                                    .addParam("app_key", TokenUtils.getToken(NetConfig.BaseUrl + NetConfig.cancleActivityUrl))
                                    .addParam("user_id", spImp.getUID())
                                    .addParam("pfID", data.get(position).getPfID())
                                    .addParam("info", content)
                                    .request(new ACallback<String>() {
                                        @Override
                                        public void onSuccess(String obj) {
                                            Log.e("222", obj+"uid"+spImp.getUID()+"pfid"+data.get(position).getPfID());
                                            try {
                                                JSONObject jsonObject = new JSONObject(obj);
                                                if (jsonObject.getInt("code") == 200) {
                                                    FocusOnToFriendTogetherModel model = new Gson().fromJson(obj, FocusOnToFriendTogetherModel.class);
                                                    data.remove(position);
                                                    notifyItemRemoved(position);
                                                    notifyDataSetChanged();
                                                    Toast.makeText(context, "活动取消成功", Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onFail(int errCode, String errMsg) {

                                        }
                                    });
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView beginTimeTv;
        private TextView endTimeTv;
        private TextView priceTv;
        private TextView applyTv;
        private ImageView picIv;
        private TextView viewsyTv;
        private TextView focusOnTv;
        private RelativeLayout cancleRl;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (itemView).findViewById(R.id.recyclerview_join_active_tv_title);
            beginTimeTv = (itemView).findViewById(R.id.recyclerview_join_active_tv_begin_time);
            endTimeTv = (itemView).findViewById(R.id.recyclerview_join_active_tv_end_time);
            priceTv = (itemView).findViewById(R.id.recyclerview_join_active_tv_price);
            applyTv = (itemView).findViewById(R.id.recyclerview_join_active_tv_apply_num);
            picIv = (itemView).findViewById(R.id.activity_join_active_rv_iv_title);
            viewsyTv = (itemView).findViewById(R.id.recyclerview_join_active_tv_views_num);
            focusOnTv = (itemView).findViewById(R.id.recyclerview_join_active_tv_focus_on_num);
            cancleRl = (itemView).findViewById(R.id.recyclerview_join_active_rl_cancle_active);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
