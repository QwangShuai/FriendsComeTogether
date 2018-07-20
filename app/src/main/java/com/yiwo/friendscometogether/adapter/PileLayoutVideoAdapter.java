package com.yiwo.friendscometogether.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stone.pile.libs.PileLayout;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.HomeHotFriendsRememberModel;
import com.yiwo.friendscometogether.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/7/20.
 */

public class PileLayoutVideoAdapter extends PileLayout.Adapter {
    List<HomeHotFriendsRememberModel.ObjBean.VideoBean> data;
    Context context;
    public PileLayoutVideoAdapter(Context context, List<HomeHotFriendsRememberModel.ObjBean.VideoBean> data){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getLayoutId() {
        return R.layout.home_hot_video;
    }

    @Override
    public void onItemClick(View view, int position) {

        super.onItemClick(view, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void bindView(View view, int index) {
        ViewHolder holedr = (ViewHolder) view.getTag();
        if(holedr==null){
            holedr = new ViewHolder();
            holedr.videoIv = (ImageView) view.findViewById(R.id.home_hot_video_iv);
            holedr.videoTv = (TextView) view.findViewById(R.id.home_hot_video_tv);
        }
        if(!StringUtils.isEmpty(data.get(index).getImg())){
            Picasso.with(context).load(data.get(index).getImg()).into(holedr.videoIv);
        }
        holedr.videoTv.setText(data.get(index).getVname());
//        super.bindView(view, index);
    }
    static class ViewHolder {
        ImageView videoIv;
        TextView videoTv;
    }
}
