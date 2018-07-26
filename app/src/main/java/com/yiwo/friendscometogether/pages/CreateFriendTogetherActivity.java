package com.yiwo.friendscometogether.pages;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.custom.ActivitiesRequireDialog;
import com.yiwo.friendscometogether.custom.EditContentDialog;
import com.yiwo.friendscometogether.custom.EditTitleDialog;
import com.yiwo.friendscometogether.custom.PeoplePriceDialog;
import com.yiwo.friendscometogether.custom.PeopleRequireDialog;
import com.yiwo.friendscometogether.custom.SetPasswordDialog;
import com.yiwo.friendscometogether.model.CityModel;
import com.yiwo.friendscometogether.model.CreateFriendsTogetherRequestModel;
import com.yiwo.friendscometogether.model.JsonBean;
import com.yiwo.friendscometogether.network.ActivityConfig;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.utils.GetJsonDataUtil;
import com.yiwo.friendscometogether.utils.StringUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateFriendTogetherActivity extends BaseActivity {
    @BindView(R.id.activity_create_friend_together_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_create_friend_together_rl_edit_title)
    RelativeLayout rlEditTitle;
    @BindView(R.id.activity_create_friend_together_rl_edit_content)
    RelativeLayout rlEditContent;
    @BindView(R.id.activity_create_friend_together_rl_time_start)
    RelativeLayout rlTimeStart;
    @BindView(R.id.activity_create_friend_together_tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.activity_create_friend_together_rl_time_end)
    RelativeLayout rlTimeEnd;
    @BindView(R.id.activity_create_friend_together_tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.activity_create_friend_together_rl_activity_city)
    RelativeLayout rlSelectCity;
    @BindView(R.id.activity_create_friend_together_tv_activity_city)
    TextView tvCity;
    @BindView(R.id.activity_create_friend_together_rl_price)
    RelativeLayout rlPrice;
    @BindView(R.id.activity_create_friend_together_rl_complete)
    RelativeLayout rlComplete;
    @BindView(R.id.activity_create_friend_together_rl_enter_activities)
    RelativeLayout rlEnterActivities;
    @BindView(R.id.activity_create_friend_together_rl_person_require)
    RelativeLayout rlPeopleRequire;
    @BindView(R.id.activity_create_friend_together_rl_activities_require)
    RelativeLayout rlActivitiesRequire;
    @BindView(R.id.activity_create_friend_together_iv_add)
    ImageView ivAdd;
    @BindView(R.id.activity_create_friend_together_iv_title)
    ImageView ivTitle;
    @BindView(R.id.activity_create_friend_together_tv_first_iv)
    TextView tvFirstIv;
    @BindView(R.id.activity_create_friend_together_iv_delete)
    ImageView ivDelete;
    @BindView(R.id.activity_create_friend_together_tv_price)
    TextView tvPrice;
    @BindView(R.id.activity_create_friend_together_tv_price_require)
    TextView tvPriceRequire;
    @BindView(R.id.activity_create_friend_together_tv_activity_require)
    TextView tvActivityRequire;
    @BindView(R.id.activity_create_friend_together_tv_pwd)
    TextView pwdTv;
    @BindView(R.id.activity_create_friend_together_tv_title)
    TextView titleTv;
    @BindView(R.id.activity_create_friend_together_tv_content)
    TextView contentTv;
    private int mYear;
    private int mMonth;
    private int mDay;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Map<String,String> map = new HashMap<>();
    private PopupWindow popupWindow;

    private static final int REQUEST_CODE = 0x00000011;
    private static final int CITY_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_friend_together);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        init();

    }

    private void init() {

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

    }

    @OnClick({R.id.activity_create_friend_together_rl_back, R.id.activity_create_friend_together_rl_edit_title, R.id.activity_create_friend_together_rl_edit_content,
            R.id.activity_create_friend_together_rl_time_start, R.id.activity_create_friend_together_rl_time_end, R.id.activity_create_friend_together_rl_activity_city,
            R.id.activity_create_friend_together_rl_price, R.id.activity_create_friend_together_rl_complete, R.id.activity_create_friend_together_rl_enter_activities,
            R.id.activity_create_friend_together_rl_person_require,R.id.activity_create_friend_together_rl_activities_require,
            R.id.activity_create_friend_together_iv_add, R.id.activity_create_friend_together_iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_create_friend_together_rl_back:
                onBackPressed();
                break;
            case R.id.activity_create_friend_together_rl_edit_title:
                final EditTitleDialog editTitleDialog = new EditTitleDialog(CreateFriendTogetherActivity.this);
                editTitleDialog.show();
                editTitleDialog.setOnReturnListener(new EditTitleDialog.OnReturnListener() {
                    @Override
                    public void onReturn(String title) {
                        map.put("title",title);
                        titleTv.setText(title);
                        editTitleDialog.dismiss();
                    }
                });
                break;
            case R.id.activity_create_friend_together_rl_edit_content:
                final EditContentDialog editContentDialog = new EditContentDialog(CreateFriendTogetherActivity.this);
                editContentDialog.show();
                editContentDialog.setOnReturnListener(new EditContentDialog.OnReturnListener() {
                    @Override
                    public void onReturn(String content) {
                        map.put("content",content);
                        contentTv.setText(content);
                        editContentDialog.dismiss();
                    }
                });
                break;
            case R.id.activity_create_friend_together_rl_time_start:
                new DatePickerDialog(CreateFriendTogetherActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.activity_create_friend_together_rl_time_end:
                new DatePickerDialog(CreateFriendTogetherActivity.this, onDateSetListenerEnd, mYear, mMonth, mDay).show();
                break;
            case R.id.activity_create_friend_together_rl_activity_city:
                Intent it = new Intent(CreateFriendTogetherActivity.this,CityActivity.class);
                it.putExtra(ActivityConfig.ACTIVITY,"youju");
                startActivityForResult(it,CITY_REQUEST);
                break;
            case R.id.activity_create_friend_together_rl_price:
                PeoplePriceDialog peoplePriceDialog = new PeoplePriceDialog(CreateFriendTogetherActivity.this, new PeoplePriceDialog.PeoplePriceListener() {
                    @Override
                    public void setActivityText(CreateFriendsTogetherRequestModel model) {
                        map.put("price",model.getPrice());
                        map.put("price_type",model.getPrice_type());
                        map.put("price_info",model.getPrice_info());
                        tvPrice.setText(model.getPrice());
                    }
                });
                peoplePriceDialog.show();
                break;
            case R.id.activity_create_friend_together_rl_complete:
                showCompletePopupwindow();
                break;
            case R.id.activity_create_friend_together_rl_enter_activities:
                SetPasswordDialog setPasswordDialog = new SetPasswordDialog(CreateFriendTogetherActivity.this, new SetPasswordDialog.SetPasswordListener() {
                    @Override
                    public void setActivityText(String s) {
                        if(!StringUtils.isEmpty(s)){
                            map.put("follow_pass",s);
                            pwdTv.setText(s);
                        }

                    }
                });
                setPasswordDialog.show();
                break;
            case R.id.activity_create_friend_together_rl_person_require:
                PeopleRequireDialog peopleRequireDialog = new PeopleRequireDialog(CreateFriendTogetherActivity.this, new PeopleRequireDialog.PeopleRequireListener() {
                    @Override
                    public void setActivityText(CreateFriendsTogetherRequestModel model) {
                        map.put("min_num",model.getMin_num());
                        map.put("max_num",model.getMax_num());
                        tvPriceRequire.setText(model.getMin_num()+"~"+model.getMax_num());
                    }
                });
                peopleRequireDialog.show();
                break;
            case R.id.activity_create_friend_together_rl_activities_require:
                ActivitiesRequireDialog activitiesRequireDialog = new ActivitiesRequireDialog(CreateFriendTogetherActivity.this, new ActivitiesRequireDialog.ActivitiesRequireListener() {
                    @Override
                    public void setActivityText(CreateFriendsTogetherRequestModel model) {
                        map.put("peoplesex",model.getPeoplesex());
                        map.put("age_begin",model.getAge_begin());
                        map.put("age_end",model.getAge_end());
                        map.put("marry",model.getMarry());
                        map.put("warning",model.getWarning());
                        tvActivityRequire.setText("已填写");
                    }
                });
                activitiesRequireDialog.show();
                break;
            case R.id.activity_create_friend_together_iv_add:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(true)  //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected(selected) // 把已选的图片传入默认选中。
                        .start(CreateFriendTogetherActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.activity_create_friend_together_iv_delete:
                ivDelete.setVisibility(View.GONE);
                ivTitle.setVisibility(View.INVISIBLE);
                tvFirstIv.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            List<String> scList = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Log.e("222", scList.get(0));
            Picasso.with(CreateFriendTogetherActivity.this).load("file://" + scList.get(0)).into(ivTitle);
            ivTitle.setVisibility(View.VISIBLE);
            tvFirstIv.setVisibility(View.VISIBLE);
            ivDelete.setVisibility(View.VISIBLE);
            map.put("file_img","http://47.92.136.19/uploads/header/2018/06/27/52b94a60085237df2b0ceb1a7599f91b15300847792.jpg");
        }
        if(requestCode==CITY_REQUEST && data!=null){
            CityModel model = (CityModel) data.getSerializableExtra(ActivityConfig.CITY);
            tvCity.setText(model.getName());
            map.put("city",model.getId());
        }
    }

    /**
     * 日期选择器对话框监听
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
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("-").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("-").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("-").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("-").toString();
                }

            }
            map.put("begin_time",days);
            tvTimeStart.setText(days);
        }
    };

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            map.put("end_time",days);
            tvTimeEnd.setText(days);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CreateFriendTogetherActivity.this.finish();
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

    private void showCompletePopupwindow() {
        TextView relaseTv,nextTv,cancleTv;
        View view = LayoutInflater.from(CreateFriendTogetherActivity.this).inflate(R.layout.popupwindow_complete_together, null);
        ScreenAdapterTools.getInstance().loadView(view);
        relaseTv = (TextView) view.findViewById(R.id.popupwindow_complete_tv_release);
        nextTv = (TextView) view.findViewById(R.id.popupwindow_complete_tv_next);
        cancleTv = (TextView) view.findViewById(R.id.popupwindow_complete_tv_cancel);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
        relaseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onComplete(0);
            }
        });
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onComplete(1);
            }
        });
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void onComplete(int state){
        map.put("user_id","7");
        if(map.size()==18){
            String token = getToken(NetConfig.BaseUrl+NetConfig.createActivityUrl);
            ViseHttp.POST(NetConfig.createActivityUrl)
                    .addParams(map)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Log.i("myData",data.toString());
                        }
                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        } else {
            toToast(CreateFriendTogetherActivity.this,"请输入完整的创建活动信息");
            // 获取所有键值对对象的集合
            Set<Map.Entry<String, String>> set = map.entrySet();
            // 遍历键值对对象的集合，得到每一个键值对对象
            for (Map.Entry<String, String> me : set) {
                // 根据键值对对象获取键和值
                String key = me.getKey();
                String value = me.getValue();
                Log.i(key, "---" + value);
            }

        }
    }
}
