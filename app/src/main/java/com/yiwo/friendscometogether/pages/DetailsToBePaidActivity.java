package com.yiwo.friendscometogether.pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.DetailsOrderModel;
import com.yiwo.friendscometogether.model.OrderToPayModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.network.UMConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.TokenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsToBePaidActivity extends BaseActivity {

    @BindView(R.id.activity_details_to_pay_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_details_to_pay_tv_status)
    TextView tvStatus;
    @BindView(R.id.activity_details_to_pay_tv_title)
    TextView tvTitle;
    @BindView(R.id.activity_details_to_pay_iv_title)
    ImageView ivTitle;
    @BindView(R.id.activity_details_to_pay_tv_content)
    TextView tvContent;
    @BindView(R.id.activity_details_to_pay_tv_time)
    TextView tvTime;
    @BindView(R.id.activity_details_to_pay_tv_people_num)
    TextView tvPeopleNum;
    @BindView(R.id.activity_details_to_pay_tv_price_details)
    TextView tvPriceDetails;
    @BindView(R.id.activity_details_to_pay_tv_price)
    TextView tvPrice;
    @BindView(R.id.activity_details_to_pay_tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.activity_details_to_pay_tv_trade_number)
    TextView tvTradeNumber;
    @BindView(R.id.activity_details_to_pay_tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.activity_details_to_pay_tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.activity_details_to_pay_tv_pay_ok_time)
    TextView tvOkTime;
    @BindView(R.id.details_to_pay_rv_tv_cancle_trip)
    TextView tvCancelTrip;
    @BindView(R.id.details_to_pay_rv_tv_delete_trip)
    TextView tvDeleteTrip;
    @BindView(R.id.details_to_pay_rv_tv_to_trip)
    TextView tvToTrip;
    @BindView(R.id.details_to_pay_rv_tv_triping)
    TextView tvTriping;
    @BindView(R.id.details_to_pay_rv_tv_comment)
    TextView tvComment;
    @BindView(R.id.details_to_pay_rv_tv_payment)
    TextView tvPay;
    @BindView(R.id.details_to_pay_rv_tv_ok_return)
    TextView tvOkReturn;
    @BindView(R.id.details_to_pay_rv_tv_returning)
    TextView tvReturning;

    private SpImp spImp;
    private String uid = "";
    private String orderId = "";

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_to_be_paid);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(DetailsToBePaidActivity.this, null);
        spImp = new SpImp(DetailsToBePaidActivity.this);

        initData();

    }

    private void initData() {

        uid = spImp.getUID();
        Intent intent = getIntent();
        orderId = intent.getStringExtra("order_id");

        ViseHttp.POST(NetConfig.detailsOrderUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.detailsOrderUrl))
                .addParam("order_id", orderId)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson = new Gson();
                                DetailsOrderModel model = gson.fromJson(data, DetailsOrderModel.class);
                                tvStatus.setText(model.getObj().getStatus());
                                tvTitle.setText(model.getObj().getTitle());
                                if (!TextUtils.isEmpty(model.getObj().getPicture())) {
                                    Picasso.with(DetailsToBePaidActivity.this).load(model.getObj().getPicture()).into(ivTitle);
                                }
                                tvContent.setText(model.getObj().getContent());
                                tvTime.setText("行程时间: " + model.getObj().getTime());
                                tvPeopleNum.setText("参加人数: " + model.getObj().getGo_num());
                                tvPriceDetails.setText(model.getObj().getPrice_type());
                                tvPrice.setText("合计费用: " + model.getObj().getPrice());
                                tvOrderNumber.setText("订单编号: " + model.getObj().getOrder_sn());
                                if (model.getObj().getPay_type().equals("0")) {
                                    tvTradeNumber.setText("微信交易号: " + model.getObj().getPaycode());
                                } else {
                                    tvTradeNumber.setText("支付宝交易号: " + model.getObj().getPaycode());
                                }
                                tvCreateTime.setText("创建时间: " + model.getObj().getCreate_time());
                                tvPayTime.setText("付款时间: " + model.getObj().getPay_time());
                                tvOkTime.setText("成交时间: " + model.getObj().getOver_time());
                                if(model.getObj().getOrder_type().equals("7")){
                                    tvDeleteTrip.setVisibility(View.VISIBLE);
                                }else if(model.getObj().getOrder_type().equals("6")){
                                    tvTriping.setVisibility(View.VISIBLE);
                                }else if(model.getObj().getOrder_type().equals("5")){
                                    tvDeleteTrip.setVisibility(View.VISIBLE);
                                    tvOkReturn.setVisibility(View.VISIBLE);
                                }else if(model.getObj().getOrder_type().equals("4")){
                                    tvDeleteTrip.setVisibility(View.VISIBLE);
                                    tvReturning.setVisibility(View.VISIBLE);
                                }else if(model.getObj().getOrder_type().equals("3")){
                                    tvDeleteTrip.setVisibility(View.VISIBLE);
                                    tvComment.setVisibility(View.VISIBLE);
                                }else if(model.getObj().getOrder_type().equals("2")){
                                    tvCancelTrip.setVisibility(View.VISIBLE);
                                    tvToTrip.setVisibility(View.VISIBLE);
                                }else if(model.getObj().getOrder_type().equals("1")){
                                    tvCancelTrip.setVisibility(View.VISIBLE);
                                    tvPay.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.activity_details_to_pay_rl_back, R.id.details_to_pay_rv_tv_cancle_trip, R.id.details_to_pay_rv_tv_delete_trip, R.id.details_to_pay_rv_tv_payment,
            R.id.details_to_pay_rv_tv_comment})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.activity_details_to_pay_rl_back:
                onBackPressed();
                break;
            case R.id.details_to_pay_rv_tv_cancle_trip:
                AlertDialog.Builder normalDialog = new AlertDialog.Builder(DetailsToBePaidActivity.this);
                normalDialog.setIcon(R.mipmap.ic_launcher);
                normalDialog.setTitle("提示");
                normalDialog.setMessage("是否取消行程");
                normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ViseHttp.POST(NetConfig.cancelOrderTripUrl)
                                .addParam("app_key", TokenUtils.getToken(NetConfig.BaseUrl+NetConfig.cancelOrderTripUrl))
                                .addParam("order_id", orderId)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject1 = new JSONObject(data);
                                            if(jsonObject1.getInt("code") == 200){
                                                Toast.makeText(DetailsToBePaidActivity.this, "取消行程成功", Toast.LENGTH_SHORT).show();
                                                finish();
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
                });
                normalDialog.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                // 显示
                normalDialog.show();
                break;
            case R.id.details_to_pay_rv_tv_delete_trip:
                AlertDialog.Builder normalDialog1 = new AlertDialog.Builder(DetailsToBePaidActivity.this);
                normalDialog1.setIcon(R.mipmap.ic_launcher);
                normalDialog1.setTitle("提示");
                normalDialog1.setMessage("是否删除行程");
                normalDialog1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ViseHttp.POST(NetConfig.deleteOrderTripUrl)
                                .addParam("app_key", TokenUtils.getToken(NetConfig.BaseUrl+NetConfig.deleteOrderTripUrl))
                                .addParam("order_id", orderId)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject1 = new JSONObject(data);
                                            if(jsonObject1.getInt("code") == 200){
                                                Toast.makeText(DetailsToBePaidActivity.this, "删除行程成功", Toast.LENGTH_SHORT).show();
                                                finish();
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
                });
                normalDialog1.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                // 显示
                normalDialog1.show();
                break;
            case R.id.details_to_pay_rv_tv_payment:
                ViseHttp.POST(NetConfig.orderToPayUrl)
                        .addParam("app_key", TokenUtils.getToken(NetConfig.BaseUrl+NetConfig.orderToPayUrl))
                        .addParam("order_id", orderId)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject pay = new JSONObject(data);
                                    if(pay.getInt("code") == 200){
                                        Gson gson1 = new Gson();
                                        OrderToPayModel model1 = gson1.fromJson(data, OrderToPayModel.class);
                                        wxPay(model1.getObj());
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
                break;
            case R.id.details_to_pay_rv_tv_comment:
                intent.setClass(DetailsToBePaidActivity.this, OrderCommentActivity.class);
                intent.putExtra("orderid", orderId);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DetailsToBePaidActivity.this.finish();
    }

    public void wxPay(OrderToPayModel.ObjBean model){
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
