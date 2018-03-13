package com.em.baseframe.view.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @title  禁止滑动的ViewPager，点击切换没有滑动效果
 * @date   2017/06/17
 * @author enmaoFu
 */
public class CustomViewPagerN extends ViewPager {

    private boolean isCanScroll = false;

    public CustomViewPagerN(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomViewPagerN(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void setCanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if(isCanScroll){
            return super.onTouchEvent(arg0);
        }else{
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if(isCanScroll){
            return super.onInterceptTouchEvent(arg0);
        }else{
            return false;
        }

    }


    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, smoothScroll);
    }

    //切换不需要转换时间
    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        //表示切换的时候，需要不切换时间(切换效果)。
        super.setCurrentItem(item, false);
    }
}
