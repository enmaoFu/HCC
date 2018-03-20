package com.hcc.app.ui.healthy;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.em.baseframe.util.DensityUtils;
import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.ui.healthy.bodyfat.BodyFatInformationFgt;
import com.hcc.app.ui.healthy.daily.DailyFgt;
import com.hcc.app.ui.healthy.equipment.BodyEquipmentFgt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import qiu.niorgai.StatusBarCompat;


/**
 * @title  健康fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class HealthyFgt extends BaseLazyFgt {

    @BindView(R.id.tab_layout)
    TabLayout mTab;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    /**
     * Fragment页面集合
     */
    private List<BaseLazyFgt> mFragments;

    /**
     * Tab切换卡名字集合
     */
    private List<String> mTabsString;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_healthy;
    }

    @Override
    protected void initData() {

        //透明状态栏
        setStatusBar();

        mFragments = new ArrayList<>();
        mTabsString = new ArrayList<>();
        mTabsString.add("日常");
        mTabsString.add("随身设备");
        mTabsString.add("体脂信息");

        mFragments.add(new DailyFgt());
        mFragments.add(new BodyEquipmentFgt());
        mFragments.add(new BodyFatInformationFgt());

        //设置间隔
        LinearLayout linearLayout = (LinearLayout) mTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        /*linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.tab_divider_vertical));*/
        linearLayout.setDividerPadding(DensityUtils.dp2px(getActivity(),15));
        //普通在activity里面嵌套fragment里这样写
        //pageAdapter pageAdapter = new pageAdapter(getActivity().getSupportFragmentManager());
        //在fragment里嵌套fragment的时候这样写
        HealthyFgt.pageAdapter pageAdapter = new HealthyFgt.pageAdapter(getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(pageAdapter);

        mTab.setupWithViewPager(mViewPager);

        if (DensityUtils.getScreenWidth(getActivity())<=700){
            mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        }else {
            mTab.setTabMode(TabLayout.MODE_FIXED);
        }

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        //透明状态栏
        setStatusBar();
    }

    private void setStatusBar() {
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {
    }

    @Override
    public void btnClick(View view) {
    }

    class pageAdapter extends FragmentStatePagerAdapter {
        public pageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabsString.get(position);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

}
