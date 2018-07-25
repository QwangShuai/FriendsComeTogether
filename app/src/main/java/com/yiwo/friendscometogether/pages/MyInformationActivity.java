package com.yiwo.friendscometogether.pages;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.JsonBean;
import com.yiwo.friendscometogether.model.UserModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.GetJsonDataUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyInformationActivity extends BaseActivity {

    @BindView(R.id.activity_my_information_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_my_information_rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.activity_my_information_rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.activity_my_information_tv_location)
    TextView tvLocation;
    @BindView(R.id.activity_my_information_rl_birthday)
    RelativeLayout rlBirthday;
    @BindView(R.id.activity_my_information_tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.activity_my_information_rl_register_time)
    RelativeLayout rlRegister;
    @BindView(R.id.activity_my_information_tv_register_time)
    TextView tvRegister;
    @BindView(R.id.activity_my_information_rl_is_single)
    RelativeLayout rlSingle;
    @BindView(R.id.activity_my_information_tv_is_single)
    TextView tvSingle;
    @BindView(R.id.activity_my_information_rl_real_name)
    RelativeLayout rlRealName;
    @BindView(R.id.activity_my_information_iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.activity_my_information_tv_nickname)
    TextView tvNickname;
    @BindView(R.id.activity_my_information_iv_sex)
    ImageView ivSex;
    @BindView(R.id.activity_my_information_rl_sign_team)
    RelativeLayout rlSignTeam;
    @BindView(R.id.activity_my_information_et_username)
    EditText etUsername;
    @BindView(R.id.activity_my_information_tv_sex)
    TextView tvSex;
    @BindView(R.id.activity_my_information_et_sign)
    EditText etSign;
    @BindView(R.id.activity_my_information_tv_real_name)
    TextView tvRealName;
    @BindView(R.id.activity_my_information_tv_level)
    TextView tvLevel;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private int mYear;
    private int mMonth;
    private int mDay;

    private SpImp spImp;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        spImp = new SpImp(MyInformationActivity.this);

        initData();

    }

    private void initData() {

        Observable.just("").subscribeOn(Schedulers.newThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                initJsonData();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        uid = spImp.getUID();
        ViseHttp.POST(NetConfig.userInformation)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.userInformation))
                .addParam("uid", uid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson = new Gson();
                                UserModel userModel = gson.fromJson(data, UserModel.class);
                                Picasso.with(MyInformationActivity.this).load(userModel.getObj().getHeadeimg()).into(ivAvatar);
                                tvNickname.setText("昵称: " + userModel.getObj().getUsername());
                                if(userModel.getObj().getSex().equals("男")){
                                    Picasso.with(MyInformationActivity.this).load(R.mipmap.nan).into(ivSex);
                                }else {
                                    Picasso.with(MyInformationActivity.this).load(R.mipmap.nv).into(ivSex);
                                }
                                if(userModel.getObj().getSign().equals("0")){
                                    rlSignTeam.setVisibility(View.GONE);
                                }else {
                                    rlSignTeam.setVisibility(View.VISIBLE);
                                }
                                etUsername.setText(userModel.getObj().getUsername());
                                tvSex.setText(userModel.getObj().getSex());
                                tvLocation.setText(userModel.getObj().getUseraddress());
                                etSign.setText(userModel.getObj().getUserautograph());
                                tvBirthday.setText(userModel.getObj().getUserbirthday());
                                tvRegister.setText(userModel.getObj().getUsertime());
                                tvRealName.setText(userModel.getObj().getUsercodeok());
                                tvSingle.setText(userModel.getObj().getUsermarry());
                                tvLevel.setText(userModel.getObj().getUsergrade());
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

    private String yourChoice = "";
    @OnClick({R.id.activity_my_information_rl_back, R.id.activity_my_information_rl_sex, R.id.activity_my_information_rl_location, R.id.activity_my_information_rl_birthday,
            R.id.activity_my_information_rl_register_time, R.id.activity_my_information_rl_is_single, R.id.activity_my_information_rl_real_name})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_information_rl_back:
                onBackPressed();
                break;
            case R.id.activity_my_information_rl_sex:
                final String[] items = { "男","女" };

                AlertDialog.Builder singleChoiceDialog =
                        new AlertDialog.Builder(MyInformationActivity.this);
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
                                if(TextUtils.isEmpty(yourChoice)){
                                    tvSex.setText("男");
                                }else {
                                    tvSex.setText(yourChoice);
                                    yourChoice = "";
                                }
                            }
                        });
                singleChoiceDialog.show();
                break;
            case R.id.activity_my_information_rl_location:
                OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText() + "-" +
                                options2Items.get(options1).get(options2) + "-" +
                                options3Items.get(options1).get(options2).get(options3);
                        tvLocation.setText(tx);
                    }
                })
                        .setTitleText("城市选择")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
                pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                pvOptions.show();
                break;
            case R.id.activity_my_information_rl_birthday:
                new DatePickerDialog(MyInformationActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.activity_my_information_rl_register_time:
                new DatePickerDialog(MyInformationActivity.this, onRegisterDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.activity_my_information_rl_is_single:
                final String[] items1 = { "是","否" };

                AlertDialog.Builder singleChoiceDialog1 =
                        new AlertDialog.Builder(MyInformationActivity.this);
                singleChoiceDialog1.setTitle("请选择性别");
                // 第二个参数是默认选项，此处设置为0
                singleChoiceDialog1.setSingleChoiceItems(items1, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                yourChoice = items1[which];
                            }
                        });
                singleChoiceDialog1.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(TextUtils.isEmpty(yourChoice)){
                                    tvSingle.setText("是");
                                }else {
                                    tvSingle.setText(yourChoice);
                                    yourChoice = "";
                                }
                            }
                        });
                singleChoiceDialog1.show();
                break;
            case R.id.activity_my_information_rl_real_name:
                Intent intent = new Intent();
                intent.setClass(MyInformationActivity.this, RealNameActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 注册日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onRegisterDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("年").append("0").
                            append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("年").append("0").
                            append(mMonth + 1).append("月").append(mDay).append("日").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("年").
                            append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("年").
                            append(mMonth + 1).append("月").append(mDay).append("日").toString();
                }

            }
            tvRegister.setText(days);
        }
    };

    /**
     * 出生日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("年").append("0").
                            append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("年").append("0").
                            append(mMonth + 1).append("月").append(mDay).append("日").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("年").
                            append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("年").
                            append(mMonth + 1).append("月").append(mDay).append("日").toString();
                }

            }
            tvBirthday.setText(days);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyInformationActivity.this.finish();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

}
