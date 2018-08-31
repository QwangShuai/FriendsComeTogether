package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.InvitationOkModel;
import com.yiwo.friendscometogether.model.Paymodel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.network.UMConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyActivity extends BaseActivity {
    @BindView(R.id.apply_name_et)
    TextView tvName;
    @BindView(R.id.apply_phone_et)
    EditText etPhoneNum;
    @BindView(R.id.apply_title_tv)
    TextView tvActiveTitle;
    @BindView(R.id.apply_time_tv)
    TextView tvTime;
    @BindView(R.id.apply_cost_tv)
    TextView tvPrice;
    @BindView(R.id.apply_vessel_ll)
    LinearLayout apply_vessel_ll;
    @BindView(R.id.apply_pay_ll)
    LinearLayout apply_pay_ll;
    @BindView(R.id.activity_apply_rl_back)
    RelativeLayout apply_back;
    @BindView(R.id.apply_btn)
    TextView apply_btn;
    @BindView(R.id.apply_num_ll)
    RelativeLayout apply_num_ll;
    @BindView(R.id.iv_jian)
    ImageView ivJian;
    @BindView(R.id.iv_jia)
    ImageView ivJia;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_pay_decs)
    TextView tvPayDecs;
    @BindView(R.id.sex)
    TextView tvSex;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.age)
    TextView tvAge;
    @BindView(R.id.issingle)
    TextView tvIssingle;
    @BindView(R.id.city)
    TextView tvCity;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;
    @BindView(R.id.all_price)
    TextView tvAllPrice;
    @BindView(R.id.online_pay)
    TextView tvOnlinePay;

    private String yourChoice = "";
    private int payState = 0;
    private String pfID = "0";
    private String if_pay = "0";
    private SpImp spImp;
    private IWXAPI api;

    private int num = 1;
    private Double money;
    private Double perMoney;

    private String yqid = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        api = WXAPIFactory.createWXAPI(this, null);
        ButterKnife.bind(this);
        spImp = new SpImp(ApplyActivity.this);

        String id = getIntent().getStringExtra("id");
        String tid = getIntent().getStringExtra("tid");
        if(!TextUtils.isEmpty(id)&&!TextUtils.isEmpty(tid)){
            getShowViewTwo(id, tid);
        }else {
            getShowView();
        }

    }

    private void getShowViewTwo(String id, String tid) {

        yqid = id;

        ViseHttp.POST(NetConfig.invitationOkUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.invitationOkUrl))
                .addParam("id", tid)
                .addParam("yqid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                InvitationOkModel model = gson.fromJson(data, InvitationOkModel.class);
                                tvNum.setText(num + "");
                                if_pay = model.getObj().getPfspendtype();
                                Log.i("789456", if_pay);
                                String title = model.getObj().getPftitle();
                                String price = model.getObj().getPfspend();
                                money = Double.valueOf(price);
                                perMoney = money;
                                String begin_time = model.getObj().getPfgotime();
                                String sex = model.getObj().getPfpeoplesex();
                                String name = model.getObj().getUsername();
                                pfID = model.getObj().getId();
                                tvActiveTitle.setText(title);
                                tvPrice.setText("¥" + price + "元/人");
                                tvAllPrice.setText("¥" + price);
                                tvTime.setText("出发时间: " + begin_time);
                                tvName.setText(name);
                                tvSex.setText(sex);
                                String pic = model.getObj().getPic();
                                if (!TextUtils.isEmpty(pic)) {
                                    Glide.with(ApplyActivity.this).load(pic).into(ivTitle);
                                }
                                String age = model.getObj().getPfagebegin();
                                tvAge.setText(age + "岁");
                                String issingle = model.getObj().getPfmarry();
                                tvIssingle.setText(issingle);
                                String city = model.getObj().getPfaddress();
                                tvCity.setText("活动地点: " + city);

//                                if (sex.equals("无限制")) {
//                                    apply_num_ll.setVisibility(View.VISIBLE);
//                                }

                                if (if_pay.equals("2")) {
                                    setApplyPaymentView(1);
                                } else if (if_pay.equals("0")) {
                                    tvPayDecs.setText("现场支付");
                                } else if (if_pay.equals("1")) {
                                    tvPayDecs.setText("他人请客");
                                }
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

    @OnClick({R.id.activity_apply_rl_back, R.id.apply_btn, R.id.iv_jian, R.id.iv_jia, R.id.online_pay})
//,R.id.apply_sex_tv
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.activity_apply_rl_back:
                finish();
                break;
            case R.id.apply_btn:
                apply();
                break;
            case R.id.iv_jian:
                if (num > 1) {
                    num = num - 1;
                    tvNum.setText(num + "");
                    money = money - perMoney;
                    tvAllPrice.setText("¥" + money);
                }
                break;
            case R.id.iv_jia:
                num = num + 1;
                tvNum.setText(num + "");
                money = money + perMoney;
                tvAllPrice.setText("¥" + money);
                break;
            case R.id.online_pay:
                apply();
                break;
        }

    }

    public void setApplyPaymentView(int state) {
        if (state == 1) {
            apply_pay_ll.setVisibility(View.GONE);
            View v = LayoutInflater.from(this).inflate(R.layout.include_payment, null);
            ScreenAdapterTools.getInstance().loadView(v);
            RelativeLayout wachat_pay = (RelativeLayout) v.findViewById(R.id.wechat_pay);
//            RelativeLayout alipay_alipay = (RelativeLayout) v.findViewById(R.id.alipay_pay);
            final ImageView wechatIv = (ImageView) v.findViewById(R.id.wechatIv);
//            final ImageView alipayIv = (ImageView) v.findViewById(R.id.alipayIv);
            wachat_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payState = 0;
                    wechatIv.setImageResource(R.mipmap.apply_true);
//                    alipayIv.setImageResource(R.mipmap.apply_false);
                }
            });
//            alipay_alipay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    payState = 1;
//                    wechatIv.setImageResource(R.mipmap.apply_false);
//                    alipayIv.setImageResource(R.mipmap.apply_true);
//                }
//            });
            apply_vessel_ll.addView(v);
            apply_btn.setVisibility(View.INVISIBLE);
            llPay.setVisibility(View.VISIBLE);
        }
    }

    public void getShowView() {
        tvNum.setText(num + "");
        if_pay = getIntent().getStringExtra("if_pay");
        Log.i("789456", if_pay);
        String title = getIntent().getStringExtra("title");
        String price = getIntent().getStringExtra("price");
        money = Double.valueOf(price);
        perMoney = money;
        String begin_time = getIntent().getStringExtra("begin_time");
        String sex = getIntent().getStringExtra("sex");
        String name = getIntent().getStringExtra("name");
        pfID = getIntent().getStringExtra("pfID");
        tvActiveTitle.setText(title);
        tvPrice.setText("¥" + price + "元/人");
        tvAllPrice.setText("¥" + price);
        tvTime.setText("出发时间: " + begin_time);
        tvName.setText(name);
        tvSex.setText(sex);
        String pic = getIntent().getStringExtra("pic");
        if (!TextUtils.isEmpty(pic)) {
            Glide.with(ApplyActivity.this).load(pic).into(ivTitle);
        }
        String age = getIntent().getStringExtra("age");
        tvAge.setText(age + "岁");
        String issingle = getIntent().getStringExtra("issingle");
        tvIssingle.setText(issingle);
        String city = getIntent().getStringExtra("city");
        tvCity.setText("活动地点: " + city);

        if (sex.equals("无限制")) {
            apply_num_ll.setVisibility(View.VISIBLE);
        }

        if (if_pay.equals("2")) {
            setApplyPaymentView(1);
        } else if (if_pay.equals("0")) {
            tvPayDecs.setText("现场支付");
        } else if (if_pay.equals("1")) {
            tvPayDecs.setText("他人请客");
        }
    }

    public void apply() {
        String user_id = spImp.getUID();
        if (user_id.equals("0")) {
            startActivity(new Intent(ApplyActivity.this, LoginActivity.class));
        } else if (!StringUtils.isPhoneNumberValid(etPhoneNum.getText().toString())) {
            toToast(ApplyActivity.this, "请输入正确的手机号");
        } else {
            String peopleNum = "1";
            if (StringUtils.isEmpty(tvNum.getText().toString())) {
                peopleNum = tvNum.getText().toString();
            }
            ViseHttp.POST(NetConfig.applyActivityUrl)
                    .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.applyActivityUrl))
                    .addParam("user_id", user_id)
                    .addParam("num", peopleNum)
                    .addParam("pfid", pfID)
                    .addParam("phone", etPhoneNum.getText().toString())
                    .addParam("need_paytype", payState + "")
                    .addParam("id", yqid)
                    .request(new ACallback<String>() {
                        @Override
                        public void onFail(int errCode, String errMsg) {
                            Log.e("222", errMsg);
                        }

                        @Override
                        public void onSuccess(String data) {
                            Log.e("222", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.getInt("code") == 200) {
                                    Paymodel paymodel = new Gson().fromJson(data, Paymodel.class);
                                    toToast(ApplyActivity.this, "微信支付");
                                    wxPay(paymodel.getObj());
                                } else if (jsonObject.getInt("code") == 201) {
                                    toToast(ApplyActivity.this, "报名成功");
                                    finish();
                                } else if (jsonObject.getInt("code") == 400) {
                                    toToast(ApplyActivity.this, jsonObject.getString("message"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

        }
    }

    public void wxPay(Paymodel.ObjBean model) {
        api.registerApp(UMConfig.WECHAT_APPID);
        PayReq req = new PayReq();
        req.appId = model.getAppId();
        req.partnerId = model.getPartnerId();
        req.prepayId = model.getPrepayId();
        req.nonceStr = model.getNonceStr();
        req.timeStamp = model.getTimestamp() + "";
        req.packageValue = model.getPackageX();
        req.sign = model.getSign();
        req.extData = "app data";
        api.sendReq(req);
        finish();
    }
}
