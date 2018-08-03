package com.yiwo.friendscometogether.pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MessageViewAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.MessageViewModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息中心查看
 */
public class MessageViewActivity extends BaseActivity {
    @BindView(R.id.activity_message_view_rl_back)
    RelativeLayout backRl;
    @BindView(R.id.activity_message_view_rv)
    RecyclerView messageRv;

    private SpImp spImp;
    private MessageViewAdapter adapter;
    List<MessageViewModel.ObjBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        ButterKnife.bind(this);
        spImp = new SpImp(MessageViewActivity.this);
        initData();
    }

    public void initData(){
        MessageViewModel.ObjBean model = new MessageViewModel.ObjBean();
        model.setMes_title("1243");
        model.setMes_message("我是内容");
        model.setMes_pic("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=2e86e12575310a55db24d8f487474387/09fa513d269759eee4dd1b1dbefb43166c22df53.jpg");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        initList(list);
//        String type = getIntent().getStringExtra("type");
//        ViseHttp.POST(NetConfig.messageListUrl)
//                .addParam("app_key",getToken(NetConfig.BaseUrl+NetConfig.messageListUrl))
//                .addParam("type",type)
//                .addParam("page","1")
//                .request(new ACallback<String>() {
//                    @Override
//                    public void onSuccess(String data) {
//                        Log.i("112233",data);
//                       MessageViewModel model = new Gson().fromJson(data,MessageViewModel.class);
//                       initList(model.getObj());
//                    }
//
//                    @Override
//                    public void onFail(int errCode, String errMsg) {
//                        toToast(MessageViewActivity.this,errMsg);
//                    }
//                });
    }

    public void initList(List<MessageViewModel.ObjBean> data) {
        LinearLayoutManager manager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        messageRv.setLayoutManager(manager);
        adapter = new MessageViewAdapter(data);
        messageRv.setAdapter(adapter);
    }
    @OnClick({R.id.activity_message_view_rl_back})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.activity_message_view_rl_back:
                finish();
                break;
        }
    }
}
