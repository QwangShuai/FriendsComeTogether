package com.yiwo.friendscometogether.pages;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yiwo.friendscometogether.R;
import com.yiwo.friendscometogether.adapter.MyCollectionAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectionActivity extends AppCompatActivity {

    @BindView(R.id.activity_my_collection_rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.activity_my_collection_rv)
    SwipeMenuRecyclerView recyclerView;

    private MyCollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);

        initData();

    }

    private void initData() {

        LinearLayoutManager manager = new LinearLayoutManager(MyCollectionActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        final List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        adapter = new MyCollectionAdapter(data);
//        recyclerView.setAdapter(adapter);
        recyclerView.setItemViewSwipeEnabled(true);

        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(MyCollectionActivity.this)
                        .setBackgroundColor(Color.RED)
                        .setImage(R.mipmap.ic_launcher) // 图标。
                        .setText("删除") // 文字。
                        .setTextColor(Color.WHITE) // 文字颜色。
                        .setTextSize(16) // 文字大小。
                        .setWidth(100)
                        .setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
                rightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
            }
        };
        // 设置监听器。
        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();

                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            }
        };
        // 菜单点击监听。
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

    }

    @OnClick({R.id.activity_my_collection_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_collection_rl_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyCollectionActivity.this.finish();
    }
}
