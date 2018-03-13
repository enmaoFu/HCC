package com.hcc.app.ui.shop;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hcc.app.R;
import com.hcc.app.adapter.ShopAdapter;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.pojo.ShopPojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  商店fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class ShopFgt extends BaseLazyFgt {

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    /**
     * recyclerview布局管理器
     */
    private RecyclerView.LayoutManager mLayoutManager;
    /**
     * 适配器
     */
    private ShopAdapter shopAdapter;
    /**
     * 数据源
     */
    private List<ShopPojo> shopPojos;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initData() {

        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.colorPrimary),00);

        //实例化布局管理器
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        //mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        shopAdapter = new ShopAdapter(R.layout.item_shop, setData());
        //设置布局管理器
        rvData.setLayoutManager(mLayoutManager);
        //设置间隔样式
        /*mTekeRecyclerview.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.parseColor(getResources().getString(R.string.parseColor)))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());*/
        //大小不受适配器影响
        rvData.setHasFixedSize(true);
        //设置加载动画类型
        shopAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rvData.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rvData.setAdapter(shopAdapter);

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.colorPrimary),00);
    }

    @Override
    protected boolean setIsInitRequestData() {
        return true;
    }

    @Override
    protected void requestData() {

    }

    /**
     * 设置数据
     * @return
     */
    public List<ShopPojo> setData(){
        shopPojos = new ArrayList<>();
        ShopPojo shopPojo = null;
        for(int i = 0; i < 16; i++){
            shopPojo = new ShopPojo("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2577947464,883329486&fm=27&gp=0.jpg",
            "运动手腕","300" + "人参与","¥" + "858","¥" + "1800");
            shopPojos.add(shopPojo);
        }
        return shopPojos;
    }

}
