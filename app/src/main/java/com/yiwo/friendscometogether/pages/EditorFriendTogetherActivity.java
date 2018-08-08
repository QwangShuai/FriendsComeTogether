package com.yiwo.friendscometogether.pages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
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
import com.yiwo.friendscometogether.adapter.EditorFriendRememberAdapter;
import com.yiwo.friendscometogether.adapter.EditorFriendTogetherAdapter;
import com.yiwo.friendscometogether.base.BaseActivity;
import com.yiwo.friendscometogether.model.EditorFriendRememberModel;
import com.yiwo.friendscometogether.model.GetEditorFriendTogetherModel;
import com.yiwo.friendscometogether.network.NetConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditorFriendTogetherActivity extends BaseActivity {

    @BindView(R.id.activity_editor_friend_together_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_editor_friend_together_tv_title)
    TextView tvTitle;
    @BindView(R.id.activity_editor_friend_together_iv_title)
    ImageView ivTitle;
    @BindView(R.id.activity_editor_friend_together_tv_start)
    TextView tvStart;
    @BindView(R.id.activity_editor_friend_together_tv_end)
    TextView tvEnd;
    @BindView(R.id.activity_editor_friend_together_tv_price)
    TextView tvPrice;
    @BindView(R.id.activity_editor_friend_together_tv_browse_num)
    TextView tvBrowseNum;
    @BindView(R.id.activity_editor_friend_together_tv_focus_num)
    TextView tvFocusNum;
    @BindView(R.id.activity_editor_friend_together_rv)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.activity_editor_friend_together_tv_add)
    TextView tvAdd;

    private EditorFriendTogetherAdapter adapter;
    private List<GetEditorFriendTogetherModel.ObjBean.TitleListBean> mList;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_friend_together);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("pfID");
        ViseHttp.POST(NetConfig.getEditorFriendTogetherUrl)
                .addParam("app_key", getToken(NetConfig.BaseUrl + NetConfig.getEditorFriendTogetherUrl))
                .addParam("activity_id", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson = new Gson();
                                GetEditorFriendTogetherModel model = gson.fromJson(data, GetEditorFriendTogetherModel.class);
                                tvTitle.setText(model.getObj().getPftitle());
                                Picasso.with(EditorFriendTogetherActivity.this).load(model.getObj().getPfpic()).into(ivTitle);
                                tvStart.setText("开始时间: " + model.getObj().getPfgotime());
                                tvEnd.setText("结束时间: " + model.getObj().getPfendtime());
                                tvPrice.setText("人均费用: " + model.getObj().getPfspend());
//                                tvBrowseNum.setText("浏览: " + model.getObj().get);
//                                tvFocusNum.setText("关注: " + model.getObj().getFriendsList().getFmfavorite());
                                LinearLayoutManager manager = new LinearLayoutManager(EditorFriendTogetherActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                mList = model.getObj().getTitle_list();
                                adapter = new EditorFriendTogetherAdapter(model.getObj().getTitle_list());
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
//                Toast.makeText(EditorFriendtogetherActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                ViseHttp.POST(NetConfig.deleteRenewUrl)
                        .addParam("app_key", getToken(NetConfig.BaseUrl+NetConfig.deleteRenewUrl))
                        .addParam("id", mList.get(adapterPosition).getId())
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.getInt("code") == 200){
                                        mList.remove(adapterPosition);
                                        adapter.notifyDataSetChanged();
                                        toToast(EditorFriendTogetherActivity.this, "删除成功");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(EditorFriendTogetherActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
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
            SwipeMenuItem editItem = new SwipeMenuItem(EditorFriendTogetherActivity.this)
                    .setBackgroundColor(Color.RED)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(editItem);// 添加菜单到右侧。
        }
    };

    @OnClick({R.id.activity_editor_friend_together_rl_back, R.id.activity_editor_friend_together_tv_add})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.activity_editor_friend_together_rl_back:
                onBackPressed();
                break;
            case R.id.activity_editor_friend_together_tv_add:
                intent.setClass(EditorFriendTogetherActivity.this, CreateIntercalationActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EditorFriendTogetherActivity.this.finish();
    }
}
