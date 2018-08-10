package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.LookHistoryAdapter;
import com.yiwo.friendscometogether.adapter.MyDraftAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.UserRememberModel;
import com.yiwo.friendscometogether.network.NetConfig;
import com.yiwo.friendscometogether.sp.SpImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDraftActivity extends BaseActivity {

    @BindView(R.id.activity_my_draft_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_my_draft_rv)
    SwipeMenuRecyclerView recyclerView;

    private MyDraftAdapter adapter;
    private List<UserRememberModel.ObjBean> mList;

    private SpImp spImp;
    private String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_draft);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        spImp = new SpImp(MyDraftActivity.this);

        initData();

    }

    private void initData() {

        uid = spImp.getUID();
        LinearLayoutManager manager = new LinearLayoutManager(MyDraftActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        ViseHttp.POST(NetConfig.userRemember)
                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.userRemember))
                .addParam("uid", uid)
                .addParam("type", "1")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getInt("code") == 200){
                                Gson gson = new Gson();
                                UserRememberModel userRememberModel = gson.fromJson(data, UserRememberModel.class);
                                mList = userRememberModel.getObj();
                                adapter = new MyDraftAdapter(userRememberModel.getObj());
                                recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);
                                recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
                                recyclerView.setAdapter(adapter);
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

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            final int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
//                Toast.makeText(MyDraftActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                switch (menuPosition){
                    case 0:
                        //编辑友记
                        Intent intent = new Intent();
                        intent.setClass(MyDraftActivity.this, ModifyFriendRememberActivity.class);
                        intent.putExtra("id", mList.get(adapterPosition).getFmID());
                        startActivity(intent);
                        break;
                    case 1:
                        ViseHttp.POST(NetConfig.releaseDraftUrl)
                                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.releaseDraftUrl))
                                .addParam("id", mList.get(adapterPosition).getFmID())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.getInt("code") == 200){
                                                toToast(MyDraftActivity.this, "发布成功");
                                                mList.remove(adapterPosition);
                                                adapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                        break;
                    case 2:
                        ViseHttp.POST(NetConfig.deleteFriendRememberUrl)
                                .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.deleteFriendRememberUrl))
                                .addParam("id", mList.get(adapterPosition).getFmID())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.getInt("code") == 200){
                                                toToast(MyDraftActivity.this, "删除成功");
                                                mList.remove(adapterPosition);
                                                adapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                        break;
                }
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(MyDraftActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem editItem = new SwipeMenuItem(MyDraftActivity.this)
                    .setBackgroundColor(Color.GRAY)
                    .setText("编辑")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(editItem);// 添加菜单到右侧。

            SwipeMenuItem sendItem = new SwipeMenuItem(MyDraftActivity.this)
                    .setBackgroundColor(Color.parseColor("#ff9d00"))
                    .setText("发布")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(sendItem);// 添加菜单到右侧。

            SwipeMenuItem deleteItem = new SwipeMenuItem(MyDraftActivity.this)
                    .setBackgroundColor(Color.RED)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
        }
    };

    @OnClick({R.id.activity_my_draft_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_draft_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyDraftActivity.this.finish();
    }
}
