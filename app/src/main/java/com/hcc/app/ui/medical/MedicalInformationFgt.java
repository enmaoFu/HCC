package com.hcc.app.ui.medical;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.config.UserManeger;
import com.orhanobut.logger.Logger;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import qiu.niorgai.StatusBarCompat;

/**
 * @title  医疗信息fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class MedicalInformationFgt extends BaseLazyFgt{

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
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
        return R.layout.fragment_medical;
    }

    @Override
    protected void initData() {

        //在第一次进入并创建页面，初始化数据的时候，初始化默认的状态栏颜色，防止因为操作顺序和逻辑不同而导致的颜色混乱问题
        UserManeger.setStatusBarColor(R.color.colorPrimary);
        setStatusBar(R.color.colorPrimary);

        mFragments = new ArrayList<>();
        mTabsString = new ArrayList<>();
        mTabsString.add("病例");
        mTabsString.add("检查单");
        mTabsString.add("检验单");
        mTabsString.add("处方笺");

        mFragments.add(new CaseNoteFgt());
        mFragments.add(new CheckNoteFgt());
        mFragments.add(new TestNoteFgt());
        mFragments.add(new PrescriptionNoteFgt());

        pageAdapter pageAdapter = new pageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(pageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int number = mViewPager.getCurrentItem();
                switch (number){
                    case 0:
                        setStatusBar(R.color.caseNote);
                        UserManeger.setStatusBarColor(R.color.caseNote);
                        break;
                    case 1:
                        setStatusBar(R.color.checkNote);
                        UserManeger.setStatusBarColor(R.color.checkNote);
                        break;
                    case 2:
                        setStatusBar(R.color.testNote);
                        UserManeger.setStatusBarColor(R.color.testNote);
                        break;
                    case 3:
                        setStatusBar(R.color.prescriptionNote);
                        UserManeger.setStatusBarColor(R.color.prescriptionNote);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initMagicIndicator();

    }

    private void setStatusBar(int color) {
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(color));
    }

    @Override
    protected boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        setStatusBar(UserManeger.getStatusBarColor());
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setSkimOver(true);
        int padding = UIUtil.getScreenWidth(getActivity()) / 2;
        commonNavigator.setRightPadding(padding);
        commonNavigator.setLeftPadding(padding);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mFragments == null ? 0 : mFragments.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mTabsString.get(index));
                clipPagerTitleView.setTextSize(58);
                clipPagerTitleView.setTextColor(Color.parseColor("#80ffffff"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
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
