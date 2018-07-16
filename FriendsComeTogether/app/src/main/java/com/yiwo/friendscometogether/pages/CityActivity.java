package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.custom.SlideBar;
import com.yiwo.friendscometogether.model.CityModel;

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
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        unbinder = ButterKnife.bind(this);
    }
}
