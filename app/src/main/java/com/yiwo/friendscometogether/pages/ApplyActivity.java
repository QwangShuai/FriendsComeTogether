package com.yiwo.friendscometogether.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.Paymodel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.network.UMConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyActivity extends BaseActivity {
    @BindView(R.id.apply_name_et)
    TextView apply_name_tv;
    @BindView(R.id.apply_phone_et)
    TextView apply_phone_tv;
    @BindView(R.id.apply_title_tv)
    TextView apply_title_tv;
//    @BindView(R.id.apply_sex_tv)
//    TextView apply_sex_tv;
//    @BindView(R.id.apply_age_et)
//    EditText apply_age_et;
//    @BindView(R.id.apply_marriage_et)
//    EditText apply_marriage_et;
    @BindView(R.id.apply_num_et)
    EditText apply_num_et;
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
    @BindView(R.id.apply_num_ll)
    LinearLayout apply_num_ll;
    private String yourChoice = "";
    private int payState = 0;
    private String pfID ="0";
    private String if_pay="0";
    SpImp spImp;
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        api = WXAPIFactory.createWXAPI(this, null);
        ButterKnife.bind(this);
        spImp =new SpImp(ApplyActivity.this);
        getShowView();
    }

    @OnClick({R.id.activity_apply_rl_back,R.id.apply_btn})//,R.id.apply_sex_tv
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.activity_apply_rl_back:
                finish();
                break;
//            case R.id.apply_sex_tv:
//                final String[] items = { "男","女" };
//
//                AlertDialog.Builder singleChoiceDialog =
//                        new AlertDialog.Builder(ApplyActivity.this);
//                singleChoiceDialog.setTitle("请选择性别");
//                // 第二个参数是默认选项，此处设置为0
//                singleChoiceDialog.setSingleChoiceItems(items, 0,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                yourChoice = items[which];
//                            }
//                        });
//                singleChoiceDialog.setPositiveButton("确定",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                apply_sex_tv.setText(yourChoice);
//                                yourChoice = "男";
//                            }
//                        });
//                singleChoiceDialog.show();
//                break;
            case R.id.apply_btn:
                apply();
                break;
        }

    }
    public void setApplyPaymentView(int state){
        if(state==1){
            apply_pay_ll.setVisibility(View.GONE);
            View v = LayoutInflater.from(this).inflate(R.layout.include_payment,null);
            ScreenAdapterTools.getInstance().loadView(v);
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

    public void getShowView(){
        if_pay = getIntent().getStringExtra("if_pay");
        Log.i("789456",if_pay);
        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");
        String begin_time = getIntent().getStringExtra("begin_time");
        String sex = getIntent().getStringExtra("sex");
        pfID = getIntent().getStringExtra("pfID");
        apply_title_tv.setText(title);
        apply_cost_tv.setText(price);
        apply_time_tv.setText(begin_time);

        if(!sex.equals("0")){
            apply_num_ll.setVisibility(View.GONE);
        }

        if(if_pay.equals("2")){
                setApplyPaymentView(1);
        } else {
            setApplyPaymentView(0);
        }
    }
    public void apply(){
        String user_id = spImp.getUID();
        String toast = "报名成功";
        if(user_id.equals("0")){
            startActivity(new Intent(ApplyActivity.this,LoginActivity.class));
        } else if(!StringUtils.isPhoneNumberValid(apply_phone_tv.getText().toString())){
           toast = "请输入正确的手机号";
        }
//        else if(Integer.valueOf(apply_age_et.getText().toString())>100){
//            toast = "您输入的年龄过高";
//        } else if(StringUtils.isEmpty(apply_marriage_et.getText().toString())){
//            toast = "婚姻状况为空";
//        }
        else {
            String num ="1";
            if(StringUtils.isEmpty(apply_num_et.getText().toString())){
                num = apply_num_et.getText().toString();
            }
            ViseHttp.POST(NetConfig.applyActivityUrl)
                    .addParam("user_id",user_id)
                    .addParam("num",num)
                    .addParam("pfid",pfID)
                    .addParam("phone",apply_phone_tv.getText().toString())
                    .addParam("need_paytype",payState+"")
//                    .addParam("join_age",apply_age_et.getText().toString())
//                    .addParam("join_marry",apply_marriage_et.getText().toString())
                    .request(new ACallback<String>() {
                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }

                        @Override
                        public void onSuccess(String data) {
                            Log.i("123654",data);
                            Paymodel paymodel = new Gson().fromJson(data,Paymodel.class);
                            if(paymodel.getCode()==200){
                                wxPay(paymodel.getObj());
                            } else {

                            }
                        }
                    });

        }
       toToast(ApplyActivity.this,toast);
    }
    public void wxPay(Paymodel.ObjBean model){
        api.registerApp(UMConfig.WECHAT_APPID);
        PayReq req = new PayReq();
        req.appId = model.getAppId();
        req.partnerId = model.getPartnerId();
        req.prepayId = model.getPrepayId();
        req.nonceStr = model.getNonceStr();
        req.timeStamp = model.getTimestamp()+"";
        req.packageValue = model.getPackageX();
        req.sign = model.getSign();
        req.extData = "app data";
        api.sendReq(req);
    }
}
