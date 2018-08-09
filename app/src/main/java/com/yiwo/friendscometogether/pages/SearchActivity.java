package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.czp.searchmlist.mSearchLayout;
import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.SearchListModel;
import com.yiwo.friendscometogether.network.NetConfig;

import java.io.Serializable;
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
                Log.i("11111111",s);
                ViseHttp.POST(NetConfig.searchFriendTogetherUrl)
                        .addParam("app_key",getToken(NetConfig.BaseUrl+NetConfig.searchFriendTogetherUrl))
                        .addParam("page","1")
                        .addParam("activity_name",s)
                        .addParam("type","1")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                SearchListModel model = new Gson().fromJson(data,SearchListModel.class);
                                if (model.getCode()==200){
                                       if(model.getObj().size()==0){
                                           toToast(SearchActivity.this,"暂无搜索结果");
                                       } else {
                                           Intent it = new Intent(SearchActivity.this,SearchListActivity.class);
                                           it.putExtra("list", (Serializable) model.getObj());
                                           it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                           startActivity(it);
                                           finish();
                                       }
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                toToast(SearchActivity.this,errMsg);
                            }
                        });
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
