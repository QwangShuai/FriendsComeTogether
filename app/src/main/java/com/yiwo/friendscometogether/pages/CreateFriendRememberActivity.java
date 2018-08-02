package com.yiwo.friendscometogether.pages;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.yiwo.friendscometogether.custom.EditContentDialog;
import com.yiwo.friendscometogether.custom.EditTitleDialog;
import com.yiwo.friendscometogether.custom.PeoplePriceDialog;
import com.yiwo.friendscometogether.custom.SetPasswordDialog;
import com.yiwo.friendscometogether.model.JsonBean;
import com.yiwo.friendscometogether.model.UserLabelModel;
import com.yiwo.friendscometogether.model.UserReleaseModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.GetJsonDataUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class CreateFriendRememberActivity extends BaseActivity {

    @BindView(R.id.activity_create_friend_remember_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_create_friend_remember_rl_edit_title)
    RelativeLayout rlEditTitle;
    @BindView(R.id.activity_create_friend_remember_rl_edit_content)
    RelativeLayout rlEditContent;
    @BindView(R.id.activity_create_friend_remember_rl_time_start)
    RelativeLayout rlTimeStart;
    @BindView(R.id.activity_create_friend_remember_tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.activity_create_friend_remember_rl_time_end)
    RelativeLayout rlTimeEnd;
    @BindView(R.id.activity_create_friend_remember_tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.activity_create_friend_remember_rl_activity_city)
    RelativeLayout rlSelectCity;
    @BindView(R.id.activity_create_friend_remember_tv_activity_city)
    TextView tvCity;
    @BindView(R.id.activity_create_friend_remember_rl_price)
    RelativeLayout rlPrice;
    @BindView(R.id.activity_create_friend_remember_rl_complete)
    RelativeLayout rlComplete;
    @BindView(R.id.activity_create_friend_remember_rl_set_password)
    RelativeLayout rlPassword;
    @BindView(R.id.activity_create_friend_remember_iv_add)
    ImageView ivAdd;
    @BindView(R.id.activity_create_friend_remember_iv_title)
    ImageView ivTitle;
    @BindView(R.id.activity_create_friend_remember_tv_first_iv)
    TextView tvFirstIv;
    @BindView(R.id.activity_create_friend_remember_iv_delete)
    ImageView ivDelete;
    @BindView(R.id.activity_create_friend_remember_tv_title)
    EditText etTitle;
    @BindView(R.id.activity_create_friend_remember_tv_content)
    EditText etContent;
    @BindView(R.id.activity_create_friend_remember_rl_label)
    RelativeLayout rlLabel;
    @BindView(R.id.activity_create_friend_remember_tv_label)
    TextView tvLabel;
    @BindView(R.id.activity_create_friend_remember_et_price)
    EditText etPrice;
    @BindView(R.id.activity_create_friend_remember_tv_title_num)
    TextView tvTitleNum;
    @BindView(R.id.activity_create_friend_remember_tv_content_num)
    TextView tvContentNum;

    private int mYear;
    private int mMonth;
    private int mDay;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private PopupWindow popupWindow;

    private static final int REQUEST_CODE = 0x00000011;

    private String[] itemId;
    private String[] itemName;
    private String yourChoiceId = "";
    private String yourChoiceName = "";

    private SpImp spImp;
    private String uid = "";

    private String images = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_friend_remember);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        spImp = new SpImp(CreateFriendRememberActivity.this);

        init();

    }

    private void init() {

        uid = spImp.getUID();

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

        ViseHttp.POST(NetConfig.userLabel)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userLabel))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Log.e("222", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson = new Gson();
                                UserLabelModel userLabelModel = gson.fromJson(data, UserLabelModel.class);
                                itemId = new String[userLabelModel.getObj().size()];
                                itemName = new String[userLabelModel.getObj().size()];
                                for (int i = 0; i < userLabelModel.getObj().size(); i++) {
                                    itemId[i] = userLabelModel.getObj().get(i).getLID();
                                    itemName[i] = userLabelModel.getObj().get(i).getLname();
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

        etTitle.addTextChangedListener(textTitleWatcher);
        etContent.addTextChangedListener(textContentWatcher);

    }

    TextWatcher textTitleWatcher = new TextWatcher() {

        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tvTitleNum.setText(temp.length()+"/30");
            if(temp.length()>=30){
                toToast(CreateFriendRememberActivity.this, "您输入的字数已经超过了限制");
            }
        }
    };

    TextWatcher textContentWatcher = new TextWatcher() {

        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            temp = charSequence;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tvContentNum.setText(temp.length()+"/2000");
            if(temp.length()>=2000){
                toToast(CreateFriendRememberActivity.this, "您输入的字数已经超过了限制");
            }
        }
    };

    @OnClick({R.id.activity_create_friend_remember_rl_back, R.id.activity_create_friend_remember_rl_edit_title, R.id.activity_create_friend_remember_rl_edit_content,
            R.id.activity_create_friend_remember_rl_time_start, R.id.activity_create_friend_remember_rl_time_end, R.id.activity_create_friend_remember_rl_activity_city,
            R.id.activity_create_friend_remember_rl_price, R.id.activity_create_friend_remember_rl_complete, R.id.activity_create_friend_remember_rl_set_password,
            R.id.activity_create_friend_remember_iv_add, R.id.activity_create_friend_remember_iv_delete, R.id.activity_create_friend_remember_rl_label})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_create_friend_remember_rl_back:
                onBackPressed();
                break;
            case R.id.activity_create_friend_remember_rl_edit_title:
//                final EditTitleDialog editTitleDialog = new EditTitleDialog(CreateFriendRememberActivity.this);
//                editTitleDialog.show();
//                editTitleDialog.setOnReturnListener(new EditTitleDialog.OnReturnListener() {
//                    @Override
//                    public void onReturn(String title) {
//                        tvTitle.setText(title);
//                        editTitleDialog.dismiss();
//                    }
//                });
                break;
            case R.id.activity_create_friend_remember_rl_edit_content:
//                final EditContentDialog editContentDialog = new EditContentDialog(CreateFriendRememberActivity.this);
//                editContentDialog.show();
//                editContentDialog.setOnReturnListener(new EditContentDialog.OnReturnListener() {
//                    @Override
//                    public void onReturn(String content) {
//                        tvContent.setText(content);
//                        editContentDialog.dismiss();
//                    }
//                });
                break;
            case R.id.activity_create_friend_remember_rl_time_start:
                new DatePickerDialog(CreateFriendRememberActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.activity_create_friend_remember_rl_time_end:
                new DatePickerDialog(CreateFriendRememberActivity.this, onDateSetListenerEnd, mYear, mMonth, mDay).show();
                break;
            case R.id.activity_create_friend_remember_rl_activity_city:
                OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText() + "-" +
                                options2Items.get(options1).get(options2) + "-" +
                                options3Items.get(options1).get(options2).get(options3);
                        tvCity.setText(tx);
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
            case R.id.activity_create_friend_remember_rl_price:

                break;
            case R.id.activity_create_friend_remember_rl_complete:
                if(TextUtils.isEmpty(etTitle.getText().toString())||TextUtils.isEmpty(etContent.getText().toString())||TextUtils.isEmpty(tvTimeStart.getText().toString())
                        ||TextUtils.isEmpty(tvTimeEnd.getText().toString())||TextUtils.isEmpty(tvCity.getText().toString())||TextUtils.isEmpty(etPrice.getText().toString())
                        ||TextUtils.isEmpty(tvLabel.getText().toString())){
                    toToast(CreateFriendRememberActivity.this, "请完善信息");
                }else {
                    showCompletePopupwindow();
                }
                break;
            case R.id.activity_create_friend_remember_rl_set_password:
                SetPasswordDialog setPasswordDialog = new SetPasswordDialog(CreateFriendRememberActivity.this, new SetPasswordDialog.SetPasswordListener() {
                    @Override
                    public void setActivityText(String s) {

                    }
                });
                setPasswordDialog.show();
                break;
            case R.id.activity_create_friend_remember_iv_add:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(true)  //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected(selected) // 把已选的图片传入默认选中。
                        .start(CreateFriendRememberActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.activity_create_friend_remember_iv_delete:
                ivDelete.setVisibility(View.GONE);
                ivTitle.setVisibility(View.INVISIBLE);
                tvFirstIv.setVisibility(View.INVISIBLE);
                break;
            case R.id.activity_create_friend_remember_rl_label:
                AlertDialog.Builder singleChoiceDialog =
                        new AlertDialog.Builder(CreateFriendRememberActivity.this);
                singleChoiceDialog.setTitle("请选择标签");
                // 第二个参数是默认选项，此处设置为0
                singleChoiceDialog.setSingleChoiceItems(itemName, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                yourChoiceName = itemName[which];
                                yourChoiceId = itemId[which];
                            }
                        });
                singleChoiceDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (TextUtils.isEmpty(yourChoiceName)) {
                                    tvLabel.setText(itemName[0]);
                                    yourChoiceId = itemId[0];
                                } else {
                                    tvLabel.setText(yourChoiceName);
                                    yourChoiceName = "";
                                }
                            }
                        });
                singleChoiceDialog.show();
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
            images = scList.get(0);
            Picasso.with(CreateFriendRememberActivity.this).load("file://" + scList.get(0)).into(ivTitle);
            ivTitle.setVisibility(View.VISIBLE);
            tvFirstIv.setVisibility(View.VISIBLE);
            ivDelete.setVisibility(View.VISIBLE);
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
            tvTimeEnd.setText(days);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CreateFriendRememberActivity.this.finish();
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

        View view = LayoutInflater.from(CreateFriendRememberActivity.this).inflate(R.layout.popupwindow_complete, null);
        ScreenAdapterTools.getInstance().loadView(view);

        TextView tvRelease = view.findViewById(R.id.popupwindow_complete_tv_release);
        TextView tvSave = view.findViewById(R.id.popupwindow_complete_tv_save);
        TextView tvNext = view.findViewById(R.id.popupwindow_complete_tv_next);
        TextView tvCancel = view.findViewById(R.id.popupwindow_complete_tv_cancel);

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

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        tvRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable<File> observable = Observable.create(new ObservableOnSubscribe<File>() {
                    @Override
                    public void subscribe(final ObservableEmitter<File> e) throws Exception {
//                        File file = new File(images);
                        Luban.with(CreateFriendRememberActivity.this)
                                .load(images)
                                .ignoreBy(100)
                                .filter(new CompressionPredicate() {
                                    @Override
                                    public boolean apply(String path) {
                                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                                    }
                                })
                                .setCompressListener(new OnCompressListener() {
                                    @Override
                                    public void onStart() {
                                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                    }

                                    @Override
                                    public void onSuccess(File file) {
                                        // TODO 压缩成功后调用，返回压缩后的图片文件
                                        e.onNext(file);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // TODO 当压缩过程出现问题时调用
                                    }
                                }).launch();
                    }
                });
                Observer<File> observer = new Observer<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(File value) {
                        ViseHttp.UPLOAD(NetConfig.userRelease)
                                .addHeader("Content-Type","multipart/form-data")
                                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userRelease))
                                .addParam("fmtitle", etTitle.getText().toString())
                                .addParam("fmcontent", etContent.getText().toString())
                                .addParam("fmaddress", tvCity.getText().toString())
                                .addParam("uid", uid)
                                .addParam("fmlable", yourChoiceId)
                                .addParam("fmgotime", tvTimeStart.getText().toString())
                                .addParam("fmendtime", tvTimeEnd.getText().toString())
                                .addParam("percapitacost", etPrice.getText().toString())
                                .addParam("activity_id", "0")
                                .addParam("type", "0")
                                .addFile("fmpic", value)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if (jsonObject.getInt("code") == 200) {
                                                toToast(CreateFriendRememberActivity.this, jsonObject.getString("message") + "");
                                                onBackPressed();
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

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };
                observable.observeOn(Schedulers.newThread())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable<File> observable = Observable.create(new ObservableOnSubscribe<File>() {
                    @Override
                    public void subscribe(final ObservableEmitter<File> e) throws Exception {
//                        File file = new File(images);
                        Luban.with(CreateFriendRememberActivity.this)
                                .load(images)
                                .ignoreBy(100)
                                .filter(new CompressionPredicate() {
                                    @Override
                                    public boolean apply(String path) {
                                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                                    }
                                })
                                .setCompressListener(new OnCompressListener() {
                                    @Override
                                    public void onStart() {
                                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                    }

                                    @Override
                                    public void onSuccess(File file) {
                                        // TODO 压缩成功后调用，返回压缩后的图片文件
                                        e.onNext(file);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // TODO 当压缩过程出现问题时调用
                                    }
                                }).launch();
                    }
                });
                Observer<File> observer = new Observer<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(File value) {
                        ViseHttp.UPLOAD(NetConfig.userRelease)
                                .addHeader("Content-Type","multipart/form-data")
                                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userRelease))
                                .addParam("fmtitle", etTitle.getText().toString())
                                .addParam("fmcontent", etContent.getText().toString())
                                .addParam("fmaddress", tvCity.getText().toString())
                                .addParam("uid", uid)
                                .addParam("fmlable", yourChoiceId)
                                .addParam("fmgotime", tvTimeStart.getText().toString())
                                .addParam("fmendtime", tvTimeEnd.getText().toString())
                                .addParam("percapitacost", etPrice.getText().toString())
                                .addParam("activity_id", "0")
                                .addParam("type", "1")
                                .addFile("fmpic", value)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if (jsonObject.getInt("code") == 200) {
                                                toToast(CreateFriendRememberActivity.this, jsonObject.getString("message") + "");
                                                onBackPressed();
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

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };
                observable.observeOn(Schedulers.newThread())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable<File> observable = Observable.create(new ObservableOnSubscribe<File>() {
                    @Override
                    public void subscribe(final ObservableEmitter<File> e) throws Exception {
//                        File file = new File(images);
                        Luban.with(CreateFriendRememberActivity.this)
                                .load(images)
                                .ignoreBy(100)
                                .filter(new CompressionPredicate() {
                                    @Override
                                    public boolean apply(String path) {
                                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                                    }
                                })
                                .setCompressListener(new OnCompressListener() {
                                    @Override
                                    public void onStart() {
                                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                    }

                                    @Override
                                    public void onSuccess(File file) {
                                        // TODO 压缩成功后调用，返回压缩后的图片文件
                                        e.onNext(file);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // TODO 当压缩过程出现问题时调用
                                    }
                                }).launch();
                    }
                });
                Observer<File> observer = new Observer<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(File value) {
                        ViseHttp.UPLOAD(NetConfig.userRelease)
                                .addHeader("Content-Type","multipart/form-data")
                                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.userRelease))
                                .addParam("fmtitle", etTitle.getText().toString())
                                .addParam("fmcontent", etContent.getText().toString())
                                .addParam("fmaddress", tvCity.getText().toString())
                                .addParam("uid", uid)
                                .addParam("fmlable", yourChoiceId)
                                .addParam("fmgotime", tvTimeStart.getText().toString())
                                .addParam("fmendtime", tvTimeEnd.getText().toString())
                                .addParam("percapitacost", etPrice.getText().toString())
                                .addParam("activity_id", "0")
                                .addParam("type", "0")
                                .addFile("fmpic", value)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            Log.e("222", data);
                                            JSONObject jsonObject = new JSONObject(data);
                                            if (jsonObject.getInt("code") == 200) {
                                                Gson gson = new Gson();
                                                UserReleaseModel userReleaseModel = gson.fromJson(data, UserReleaseModel.class);
                                                Intent intent = new Intent();
                                                intent.putExtra("id", userReleaseModel.getObj().getId()+"");
                                                intent.setClass(CreateFriendRememberActivity.this, CreateIntercalationActivity.class);
                                                startActivity(intent);
                                                CreateFriendRememberActivity.this.finish();
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

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                };
                observable.observeOn(Schedulers.newThread())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
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

}
