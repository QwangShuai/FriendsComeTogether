package com.yiwo.friendscometogether.pages;

import android.os.Bundle;

import com.czp.searchmlist.mSearchLayout;
import com.vise.xsnow.http.ViseHttp;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchActivity extends BaseActivity {
    private Unbinder unbinder;
    @BindView(R.id.mSearch)
    mSearchLayout mSearch;
    //热门搜索数据
    String shareHotData = "日月潭,卢步坡,太阳岛";
    List skillHots = Arrays.asList(shareHotData.split(","));
    //历史记录
    String shareData = "hehe,黑大,密西,卢瑟,火箭,啊呀,破皮";
    List skills = Arrays.asList(shareData.split(","));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        unbinder = ButterKnife.bind(this);
        this.mSearch.initData(skills, skillHots, new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String s) {
//                ViseHttp.POST()
            }

            @Override
            public void Back() {//取消
                finish();
            }

            @Override
            public void ClearOldData() {

            }

            @Override
            public void SaveOldData(ArrayList<String> arrayList) {

            }
        });
    }
}
