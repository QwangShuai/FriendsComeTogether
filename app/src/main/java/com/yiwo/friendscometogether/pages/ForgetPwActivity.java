package com.yiwo.friendscometogether.pages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.yiwo.friendscometogether.MyApplication;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.custom.CImageView;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ForgetPwActivity extends BaseActivity {
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.rl_set_return)
    RelativeLayout returnRl;
    @BindView(R.id.headIv)
    CImageView headIv;
    @BindView(R.id.getCode_btn)
    Button getCode_btn;
    @BindView(R.id.forgetPw_phoneEt)
    EditText forgetPw_phoneEt;
    @BindView(R.id.forgetPw_pwEt)
    EditText forgetPw_pwEt;
    @BindView(R.id.forgetPw_confirmPwEt)
    EditText forgetPw_confirmPwEt;
    @BindView(R.id.forgetPw_codeEt)
    EditText forgetPw_codeEt;
    @BindView(R.id.forgetPw_btn)
    Button forgetPw_btn;
    private Unbinder unbinder;
    String codeID = "";
    Context c;
    public Button getCode_btn(){
        return getCode_btn;
    }
    public SpImp spImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pw);
        c = ForgetPwActivity.this;
        unbinder = ButterKnife.bind(this);
        MyApplication.ftptimecount
                .setActivity(ForgetPwActivity.this);
        spImp = new SpImp(c);
    }
    @OnClick({R.id.getCode_btn,R.id.forgetPw_btn,R.id.rl_set_return})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_set_return:
                finish();
                break;
            case R.id.getCode_btn:
                getCode(forgetPw_phoneEt.getText().toString());
                break;
            case R.id.forgetPw_btn:
                register(forgetPw_phoneEt.getText().toString(),forgetPw_pwEt.getText().toString(),
                        forgetPw_confirmPwEt.getText().toString(),forgetPw_codeEt.getText().toString());
                break;
        }
    }
    public void getCode(String phone){
        if(!StringUtils.isPhoneNumberValid(phone)){
            toToast(c,"请输入正确的手机号");
        }
        else {
            MyApplication.ftptimecount.start();
            String token =getToken(NetConfig.getCodeUrl);
            OkHttpUtils.post()
                    .tag(this)
                    .url(NetConfig.getCodeUrl)
                    .addParams("app_key",token)
                    .addParams("phone",phone)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            String result = new String(Base64.decode(response.getBytes(),Base64.DEFAULT));
                            try {
                                JSONObject jsonObject =new JSONObject(result);
                                int code = jsonObject.optInt("code");
                                if(code==200){
                                    String a = jsonObject.optString("obj");
                                    JSONObject js = new JSONObject(a);
                                    codeID = js.optString("codeID");
                                    Log.i("我的codeID",js.optString("codeID").toString());
                                } else {
                                    toToast(c,jsonObject.optString("message").toString());
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }
    public void register(String phone,String pwd,String cpwd,String code){
        String token = getToken(NetConfig.forgetPwUrl);
        if(!StringUtils.isPhoneNumberValid(phone)){
            toToast(c,"请输入正确的手机号");
        } else if(code.length()!=6){
            toToast(c,"请输入正确的验证码");
        } else if(StringUtils.isEmpty(pwd)||!pwd.equals(cpwd)){
            toToast(c,"两次输入的密码不一致");
        } else {
            OkHttpUtils.post()
                    .tag(this)
                    .url(NetConfig.forgetPwUrl)
                    .addParams("app_key",token)
                    .addParams("phone",phone)
                    .addParams("CodeId",codeID)
                    .addParams("password",pwd)
//                    .addParams("confirm_password",cpwd)
                    .addParams("code",code)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            String result = new String(Base64.decode(response.getBytes(),Base64.DEFAULT));
                            try {
                                JSONObject jsonObject =new JSONObject(result);
                                int code = jsonObject.optInt("code");
                                if(code==200){
                                    Log.i("我的UID",jsonObject.optString("obj").toString());
                                    JSONObject js = new JSONObject(jsonObject.optString("obj"));
                                    spImp.setUID(js.optString("uid"));
                                    finish();
                                } else {
                                    toToast(c,jsonObject.optString("message").toString());
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }
}
