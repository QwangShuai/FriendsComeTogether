package com.yiwo.friendscometogether.imagepreview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/9/5 0005.
 */

public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {

        this(context, null);

    }



    public CustomViewPager(Context context, AttributeSet attrs) {

        super(context, attrs);

    }



    @Override

    public boolean onInterceptTouchEvent(MotionEvent ev) {

        try {

            return super.onInterceptTouchEvent(ev);

        } catch (IllegalArgumentException e) {

            Log.i("TAG", "onInterceptTouchEvent: 多点触摸系统Bug");

        } catch (ArrayIndexOutOfBoundsException e) {

            Log.i("TAG", "onInterceptTouchEvent: 多点触摸系统Bug");

        }

        return false;

    }

}
