package com.yiwo.friendscometogether;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.yiwo.friendscometogether.fragment.ChatFragment;
import com.yiwo.friendscometogether.fragment.FriendsRememberFragment;
import com.yiwo.friendscometogether.fragment.FriendsTogetherFragment;
import com.yiwo.friendscometogether.fragment.HomeFragment;
import com.yiwo.friendscometogether.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends FragmentActivity {
  public static FragmentTabHost tabHost;
  // Tab选项卡的文字
  private String mTextviewArray[] = {"首页", "友聚","友记", "聊天","我的"};
  // 定义一个布局
  private LayoutInflater layoutInflater;
  // 定义数组来存放按钮图片
  private int mImageViewArray[] = {R.drawable.select_index,
          R.drawable.select_friends_together,R.drawable.select_friends_remember,R.drawable.select_chat, R.drawable.select_my};

  // 定义数组来存放Fragment界面
  private Class[] fragmentArray = {HomeFragment.class, FriendsTogetherFragment.class, FriendsRememberFragment.class, ChatFragment.class, MyFragment.class};
  //安卓6.0动态获取权限
  String[] permissions = new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
          Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
  List<String> mPermissionList = new ArrayList<>();
  boolean mShowRequestPermission = true;//用户是否禁止权限
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  getPermissions();
    initView();
  }

  private void initView(){
    layoutInflater = LayoutInflater.from(this);
    tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
    tabHost.getTabWidget().setDividerDrawable(null);

    // 得到fragment的个数
    int count = fragmentArray.length;

    for (int i = 0; i < count; i++) {
      // 为每一个Tab按钮设置图标、文字和内容
      TabHost.TabSpec tabSpec = tabHost.newTabSpec(mTextviewArray[i])
              .setIndicator(getTabItemView(i));
      // 将Tab按钮添加进Tab选项卡中
      tabHost.addTab(tabSpec, fragmentArray[i], null);
    }
  }
  /**
   * 给Tab按钮设置图标和文字
   */
  private View getTabItemView(int index) {
    View view = layoutInflater.inflate(R.layout.item_tableview, null);
    tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
    imageView.setImageResource(mImageViewArray[index]);

    TextView textView = (TextView) view.findViewById(R.id.textview);
    textView.setText(mTextviewArray[index]);

    return view;
  }
  public void setmTabHost(int i){
    tabHost.setCurrentTab(i);
  }

  public void getPermissions(){
    /**
     * 判断哪些权限未授予
     */
    mPermissionList.clear();
    for (int i = 0; i < permissions.length; i++) {
      if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
        mPermissionList.add(permissions[i]);
      }
    }
    /**
     * 判断是否为空
     */
    if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
      initView();
    } else {//请求权限方法
      String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
      ActivityCompat.requestPermissions(this, permissions, 1);
    }
  }
}
