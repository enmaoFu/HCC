package com.hcc.app.ui.user;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.em.baseframe.util.DensityUtils;
import com.hcc.app.R;
import com.hcc.app.base.BaseAty;
import com.hcc.app.base.BaseLazyFgt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  登录页面
 * @date   2018/03/06
 * @author enmaoFu
 */
public class LoginAty extends BaseAty{

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
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

        mFragments = new ArrayList<>();
        mTabsString = new ArrayList<>();
        mTabsString.add("验证码登陆");
        mTabsString.add("账号密码登陆");

        mFragments.add(new LoginCodeFgt());
        mFragments.add(new LoginAccountFgt());

        //设置间隔
        LinearLayout linearLayout = (LinearLayout) mTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.tab_divider_vertical));
        linearLayout.setDividerPadding(DensityUtils.dp2px(this,15));
        //普通在activity里面嵌套fragment里这样写
        pageAdapter pageAdapter = new pageAdapter(this.getSupportFragmentManager());
        //在fragment里嵌套fragment的时候这样写
        //LoginAty.pageAdapter pageAdapter = new LoginAty.pageAdapter(this.getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(pageAdapter);

        mTab.setupWithViewPager(mViewPager);
        mTab.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.register,R.id.logout})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(RegisterAty.class,null);
                break;
            case R.id.logout:
                this.finish();
                System.exit(0);
                break;
        }
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
}
