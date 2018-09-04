package com.yiwo.friendscometogether.pages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MyFriendAdapter;
import com.yiwo.friendscometogether.model.MyFriendModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;
import com.yiwo.friendscometogether.utils.TokenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzletterssidebar.ZzLetterSideBar;
import me.zhouzhuo.zzletterssidebar.interf.OnLetterTouchListener;

public class MyFriendActivity extends AppCompatActivity {

    @BindView(R.id.activity_my_friend_rl_back)
    RelativeLayout rlBack;

    private ListView listView;
    private ZzLetterSideBar sideBar;
    private TextView dialog;
    private MyFriendAdapter adapter;
    private List<MyFriendModel.ObjBean> mDatas;

    private SpImp spImp;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);

        ButterKnife.bind(MyFriendActivity.this);
        spImp = new SpImp(MyFriendActivity.this);

        initData();

    }

    private void initData() {

        uid = spImp.getUID();

        sideBar = (ZzLetterSideBar) findViewById(R.id.sidebar);
        dialog = (TextView) findViewById(R.id.tv_dialog);
        listView = (ListView) findViewById(R.id.list_view);

//        //optional
//        View header = LayoutInflater.from(this).inflate(R.layout.list_item_head, null);
//        listView.addHeaderView(header);
//
//        //optional
//        View footer = LayoutInflater.from(this).inflate(R.layout.list_item_foot, null);
//        tvFoot = (TextView) footer.findViewById(R.id.tv_foot);
//        listView.addFooterView(footer);

        ViseHttp.POST(NetConfig.MyFriendListUrl)
                .addParam("app_key", TokenUtils.getToken(NetConfig.BaseUrl+NetConfig.MyFriendListUrl))
                .addParam("uid", uid)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                MyFriendModel model = gson.fromJson(data, MyFriendModel.class);
                                //set adapter
                                mDatas = model.getObj();
                                adapter = new MyFriendAdapter(MyFriendActivity.this, mDatas);
                                listView.setAdapter(adapter);

                                //update data
//                                adapter.updateListView(mDatas);
//        tvFoot.setText(mDatas.size() + "位联系人");
                                //设置右侧触摸监听
                                sideBar.setLetterTouchListener(listView, adapter, dialog, new OnLetterTouchListener() {
                                    @Override
                                    public void onLetterTouch(String letter, int position) {
                                    }

                                    @Override
                                    public void onActionUp() {
                                    }
                                });
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

    @OnClick({R.id.activity_my_friend_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_friend_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyFriendActivity.this.finish();
    }
}
