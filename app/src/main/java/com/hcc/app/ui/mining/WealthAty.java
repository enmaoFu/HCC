package com.hcc.app.ui.mining;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.em.baseframe.view.listview.ListViewForScrollView;
import com.em.baseframe.view.popupwindow.CBDialogBuilder;
import com.em.baseframe.view.viewpager.CustomViewPagerN;
import com.hcc.app.R;
import com.hcc.app.adapter.PromoteAdapter;
import com.hcc.app.adapter.WealthAdapter;
import com.hcc.app.base.BaseAty;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.pojo.PromotePojo;
import com.hcc.app.pojo.WealthPojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  收支记录页面
 * @date   2018/03/09
 * @author enmaoFu
 */
public class WealthAty extends BaseAty{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mining_week)
    TextView miningWeek;
    @BindView(R.id.mining_month)
    TextView miningMonth;
    @BindView(R.id.mining_all)
    TextView miningAll;
    @BindView(R.id.mining_viewpaer)
    CustomViewPagerN miningViewpager;
    @BindView(R.id.money_type)
    Spinner moneyType;
    @BindView(R.id.type)
    Spinner type;
    @BindView(R.id.listview)
    ListViewForScrollView listview;
    @BindView(R.id.scro)
    ScrollView scro;

    /**
     * 币项目的类型
     */
    private List<String> moneyTypeList;

    /**
     * 收入的类型
     */
    private List<String> typeList;

    /**
     * 币项目的类型适配器
     */
    private ArrayAdapter<String> moneyTypeAdapter;

    /**
     * 收入的类型适配器
     */
    private ArrayAdapter<String> typeListAdapter;

    /**
     * Fragment集合
     */
    private List<BaseLazyFgt> mFragments;

    private WealthAdapter wealthAdapter;
    private List<WealthPojo> wealthPojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wealth;
    }

    @Override
    protected void initData() {

        initToolbar(mToolbar,"收支记录");

        miningWeek.setBackground(getResources().getDrawable(R.drawable.mining_text_round));
        miningWeek.setTextColor(Color.parseColor("#ffffff"));
        miningMonth.setBackground(getResources().getDrawable(R.drawable.mining_text_roundn));
        miningMonth.setTextColor(Color.parseColor("#2a2a2a"));
        miningAll.setBackground(getResources().getDrawable(R.drawable.mining_text_roundn));
        miningAll.setTextColor(Color.parseColor("#2a2a2a"));

        mFragments = new ArrayList<>();
        mFragments.add(new WeekFgt());
        mFragments.add(new MonThFgt());
        mFragments.add(new AllFgt());

        WealthAty.pageAdapter pageAdapter = new WealthAty.pageAdapter(this.getSupportFragmentManager());
        miningViewpager.setCurrentItem(0);
        miningViewpager.setOffscreenPageLimit(3);
        miningViewpager.setAdapter(pageAdapter);

        setSpinnerData();

        wealthAdapter = new WealthAdapter(this, setData(), R.layout.item_wealth);
        listview.setAdapter(wealthAdapter);
        scro.smoothScrollTo(0,20);

    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.mining_week,R.id.mining_month,R.id.mining_all})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.mining_week:
                miningViewpager.setCurrentItem(0);
                miningWeek.setBackground(getResources().getDrawable(R.drawable.mining_text_round));
                miningWeek.setTextColor(Color.parseColor("#ffffff"));
                miningMonth.setBackground(getResources().getDrawable(R.drawable.mining_text_roundn));
                miningMonth.setTextColor(Color.parseColor("#2a2a2a"));
                miningAll.setBackground(getResources().getDrawable(R.drawable.mining_text_roundn));
                miningAll.setTextColor(Color.parseColor("#2a2a2a"));
                break;
            case R.id.mining_month:
                miningViewpager.setCurrentItem(1);
                miningMonth.setBackground(getResources().getDrawable(R.drawable.mining_text_round));
                miningMonth.setTextColor(Color.parseColor("#ffffff"));
                miningWeek.setBackground(getResources().getDrawable(R.drawable.mining_text_roundn));
                miningWeek.setTextColor(Color.parseColor("#2a2a2a"));
                miningAll.setBackground(getResources().getDrawable(R.drawable.mining_text_roundn));
                miningAll.setTextColor(Color.parseColor("#2a2a2a"));
                break;
            case R.id.mining_all:
                miningViewpager.setCurrentItem(2);
                miningAll.setBackground(getResources().getDrawable(R.drawable.mining_text_round));
                miningAll.setTextColor(Color.parseColor("#ffffff"));
                miningWeek.setBackground(getResources().getDrawable(R.drawable.mining_text_roundn));
                miningWeek.setTextColor(Color.parseColor("#2a2a2a"));
                miningMonth.setBackground(getResources().getDrawable(R.drawable.mining_text_roundn));
                miningMonth.setTextColor(Color.parseColor("#2a2a2a"));
                break;
        }
    }

    /**
     * 初始化币项目的类型、收入的类型的数据
     */
    public void setSpinnerData(){
        moneyTypeList = new ArrayList<String>();
        moneyTypeList.add("HCC");
        moneyTypeAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, moneyTypeList);
        moneyTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moneyType.setAdapter(moneyTypeAdapter);

        typeList = new ArrayList<>();
        typeList.add("全部");
        typeList.add("挖矿收入");
        typeList.add("钱包提取");
        typeList.add("钱包转入");
        typeListAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);
        typeListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeListAdapter);
    }

    public List<WealthPojo> setData(){
        wealthPojos = new ArrayList<>();
        WealthPojo waWealthPojo = null;
        for(int i = 0; i < 8; i++){
            waWealthPojo = new WealthPojo();
            wealthPojos.add(waWealthPojo);
        }
        return wealthPojos;
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

    }

}
