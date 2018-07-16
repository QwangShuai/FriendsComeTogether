package com.yiwo.friendscometogether;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
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
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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
}
