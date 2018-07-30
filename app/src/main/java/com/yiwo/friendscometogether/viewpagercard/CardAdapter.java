package com.yiwo.friendscometogether.viewpagercard;


import android.support.v7.widget.CardView;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
