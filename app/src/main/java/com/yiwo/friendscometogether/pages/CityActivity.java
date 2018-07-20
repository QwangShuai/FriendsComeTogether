package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.squareup.okhttp.Request;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.CityAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.custom.SlideBar;
import com.yiwo.friendscometogether.model.CityModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CityActivity extends BaseActivity {
    @BindView(R.id.rl_city_return)
    RelativeLayout returnRl;
    @BindView(R.id.lv_city)
    ListView cityLv;
    @BindView(R.id.sb_city)
    SlideBar citySb;
    List<CityModel> list;
    String[] letter;
    private Unbinder unbinder;
    private CityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        unbinder = ButterKnife.bind(this);

        initData();
        setListener();
        loadHot();
        loadCity();
    }

    public void initData(){
        list = new ArrayList<>();
        adapter = new CityAdapter(this, list);
        letter = getResources().getStringArray(R.array.lowerletter);
        cityLv.setAdapter(adapter);
    }
    private void setListener() {
        citySb.setOnTouchLetterChangeListenner(new SlideBar.OnTouchLetterChangeListenner() {
            @Override
            public void onTouchLetterChange(MotionEvent event, String s) {
                for (int i = 0; i < list.size(); i++) {
                    if (s.equals(list.get(i).getName())) {
                        cityLv.setSelection(i);
                    }
                }
            }
        });
        cityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toToast(CityActivity.this,list.get(position).getName());
            }
        });
    }
    public void loadHot(){
        String token = getToken(NetConfig.BaseUrl+NetConfig.hotCityUrl);
        OkHttpUtils.post()
                .tag(this)
                .url(NetConfig.BaseUrl+NetConfig.hotCityUrl)
                .addParams("app_key",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            int code = jsonObject.optInt("code");
                            JSONArray arr = jsonObject.optJSONArray("obj");
                            if(code==200){
                               if (arr!=null&&arr.length()!=0){
                                   CityModel c = new CityModel();
                                   c.setId("-1");
                                   c.setName("热门城市");
                                   list.add(c);
                                   for(int j=0;j<arr.length();j++){
                                       JSONObject o = arr.optJSONObject(j);
                                       CityModel cm = new CityModel();
                                       cm.setId(o.optString("city_id"));
                                       cm.setName(o.optString("city_name"));
                                       list.add(cm);
                                   }
                               }
                            } else {
                                toToast(CityActivity.this,jsonObject.optString("message").toString());
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void loadCity(){
        String token = getToken(NetConfig.BaseUrl+NetConfig.cityUrl);
        OkHttpUtils.post()
                .tag(this)
                .url(NetConfig.BaseUrl+NetConfig.cityUrl)
                .addParams("app_key",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            int code = jsonObject.optInt("code");
                            JSONObject jsonobj = jsonObject.optJSONObject("obj");
                            if(code==200){
                                for(int i=0;i<letter.length;i++){
                                    JSONArray arr = jsonobj.optJSONArray(letter[i]);
                                    if(arr!=null){
                                        CityModel c = new CityModel();
                                        c.setId("-1");
                                        c.setName(letter[i].toUpperCase());
                                        list.add(c);
                                        for(int j=0;j<arr.length();j++){
                                            JSONObject o = arr.optJSONObject(j);
                                            CityModel cm = new CityModel();
                                            cm.setId(o.optString("ID"));
                                            cm.setName(o.optString("name"));
                                            list.add(cm);
                                        }
                                    }
                                }
                            } else {
                                toToast(CityActivity.this,jsonObject.optString("message").toString());
                            }
                           adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
