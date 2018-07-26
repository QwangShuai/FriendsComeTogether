package com.yiwo.friendscometogether.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.model.CreateFriendsTogetherRequestModel;

import org.w3c.dom.Text;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/20.
 */

public class PeoplePriceDialog extends Dialog {

    private Context context;
    private TextView showTv,treatTv,submitBtn;
    private EditText priceEt,otherEt;
    CreateFriendsTogetherRequestModel model = new CreateFriendsTogetherRequestModel();
    public interface PeoplePriceListener {
        /**
         * 回调函数，用于在Dialog的监听事件触发后刷新Activity的UI显示
         */
        void setActivityText(CreateFriendsTogetherRequestModel model);
    }
    private PeoplePriceListener listener;

    public PeoplePriceDialog(@NonNull Context context,PeoplePriceListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_people_price, null);
        setContentView(view);
        ScreenAdapterTools.getInstance().loadView(view);
        showTv = (TextView) view.findViewById(R.id.dialog_people_price_tv_left);
        treatTv = (TextView) view.findViewById(R.id.dialog_people_price_tv_right);
        submitBtn = (TextView) view.findViewById(R.id.dialog_people_price_btn);
        priceEt = (EditText) view.findViewById(R.id.dialog_people_price_et_price);
        otherEt = (EditText) view.findViewById(R.id.dialog_people_price_et_explain);
        showTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setPrice_type("0");
                showTv.setBackgroundResource(R.drawable.bg_dialog_price_select);
                showTv.setTextColor(context.getResources().getColor(R.color.red_F71F1F));
            }
        });
        treatTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setPrice_type("1");
                treatTv.setBackgroundResource(R.drawable.bg_dialog_price_un_select);
                treatTv.setTextColor(context.getResources().getColor(R.color.black_333333));
            }
        });
    }
}
