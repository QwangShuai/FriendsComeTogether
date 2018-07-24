package com.yiwo.friendscometogether.pages;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
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

public class LoginActivity extends BaseActivity {
    @BindView(R.id.rl_set_return)
    RelativeLayout returnRl;
    @BindView(R.id.headIv)
    CImageView headIv;
    @BindView(R.id.login_phoneEt)
    EditText login_phoneEt;
    @BindView(R.id.login_pwEt)
    EditText login_pwEt;
    @BindView(R.id.login_btn)
    Button login_btn;
    @BindView(R.id.login_registerTv)
    TextView login_registerTv;
    @BindView(R.id.login_forgetPwTv)
    TextView login_forgetPwTv;
    @BindView(R.id.login_wechatIv)
    ImageView login_wechatIv;
    Context c;
    public SpImp spImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);
        c = LoginActivity.this;
        spImp = new SpImp(c);
    }
    @OnClick({R.id.rl_set_return,R.id.login_btn,R.id.login_registerTv,R.id.login_forgetPwTv,R.id.login_wechatIv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rl_set_return:
                finish();
                break;
            case R.id.login_btn:
                login(login_phoneEt.getText().toString(),login_pwEt.getText().toString());
                break;
            case R.id.login_registerTv:
                Intent it = new Intent(c,RegisterActivity.class);
                startActivity(it);
                break;
            case R.id.login_forgetPwTv:
                Intent itf = new Intent(c,ForgetPwActivity.class);
                startActivity(itf);
                break;
            case R.id.login_wechatIv:

                break;
        }
    }
    private void login(String phone,String pwd){
        if(!StringUtils.isPhoneNumberValid(phone)){
            toToast(this,"请输入正确的手机号");
        } else {
            String token =getToken(NetConfig.BaseUrl+NetConfig.loginUrl);
            ViseHttp.POST(NetConfig.loginUrl)
                    .addParam("app_key",token)
                    .addParam("phone",phone)
                    .addParam("password",pwd)
                    .request(new ACallback<String>() {
                        public void onSuccess(String data) {
                            Log.e("222", data);
                            try {
                                JSONObject jsonObject =new JSONObject(data);
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

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }
    }
}