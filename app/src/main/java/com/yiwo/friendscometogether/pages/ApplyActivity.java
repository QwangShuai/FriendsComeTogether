package com.yiwo.friendscometogether.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyActivity extends AppCompatActivity {
    @BindView(R.id.apply_name_et)
    EditText apply_name_et;
    @BindView(R.id.apply_phone_et)
    EditText apply_phone_et;
    @BindView(R.id.apply_title_tv)
    TextView apply_title_tv;
    @BindView(R.id.apply_sex_tv)
    TextView apply_sex_tv;
    @BindView(R.id.apply_age_et)
    EditText apply_age_et;
    @BindView(R.id.apply_marriage_et)
    EditText apply_marriage_et;
    @BindView(R.id.apply_time_tv)
    TextView apply_time_tv;
    @BindView(R.id.apply_cost_tv)
    TextView apply_cost_tv;
    @BindView(R.id.apply_vessel_ll)
    LinearLayout apply_vessel_ll;
    @BindView(R.id.apply_pay_ll)
    LinearLayout apply_pay_ll;
    @BindView(R.id.activity_apply_rl_back)
    RelativeLayout apply_back;
    @BindView(R.id.apply_btn)
    TextView apply_btn;

    private String yourChoice = "";
    private int payState = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        setApplyPaymentView(1);
    }

    @OnClick({R.id.activity_apply_rl_back,R.id.apply_sex_tv,R.id.apply_btn})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.activity_apply_rl_back:
                finish();
                break;
            case R.id.apply_sex_tv:
                final String[] items = { "男","女" };

                AlertDialog.Builder singleChoiceDialog =
                        new AlertDialog.Builder(ApplyActivity.this);
                singleChoiceDialog.setTitle("请选择性别");
                // 第二个参数是默认选项，此处设置为0
                singleChoiceDialog.setSingleChoiceItems(items, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                yourChoice = items[which];
                            }
                        });
                singleChoiceDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                apply_sex_tv.setText(yourChoice);
                                yourChoice = "男";
                            }
                        });
                singleChoiceDialog.show();
                break;
            case R.id.apply_btn:

                break;
        }

    }
    public void setApplyPaymentView(int state){
        if(state==1){
            apply_pay_ll.setVisibility(View.GONE);
            View v = LayoutInflater.from(this).inflate(R.layout.include_payment,null);
            RelativeLayout wachat_pay = (RelativeLayout) v.findViewById(R.id.wechat_pay) ;
            RelativeLayout alipay_alipay = (RelativeLayout) v.findViewById(R.id.alipay_pay) ;
            final ImageView wechatIv = (ImageView) v.findViewById(R.id.wechatIv);
            final ImageView alipayIv = (ImageView) v.findViewById(R.id.alipayIv);
            wachat_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payState = 0;
                    wechatIv.setImageResource(R.mipmap.apply_true);
                    alipayIv.setImageResource(R.mipmap.apply_false);
                }
            });
            alipay_alipay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payState = 1;
                    wechatIv.setImageResource(R.mipmap.apply_false);
                    alipayIv.setImageResource(R.mipmap.apply_true);
                }
            });
            apply_vessel_ll.addView(v);
            apply_btn.setText("支付");
        }
    }
}
