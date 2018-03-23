package com.hcc.app.ui.shop;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.listener.OnItemClickListener;
import com.em.baseframe.util.AppJsonUtil;
import com.em.baseframe.util.RetrofitUtils;
import com.em.baseframe.view.refresh.PtrInitHelper;
import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.em.refresh.PtrDefaultHandler;
import com.em.refresh.PtrFrameLayout;
import com.hcc.app.R;
import com.hcc.app.adapter.ShopAdapter;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.http.ShopInterface;
import com.hcc.app.pojo.ShopPojo;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import qiu.niorgai.StatusBarCompat;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @title  商店fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class ShopFgt extends BaseLazyFgt {

    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.ptr_frame)
    PtrFrameLayout mPtrFrame;

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

    /**
     * 用来标记是否在加载
     */
    private boolean isLoading = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initData() {

        //透明状态栏
        setStatusBar();

        PtrInitHelper.initPtr(getActivity(), mPtrFrame);

        /**
         * 下拉刷新
         */
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                doHttp(RetrofitUtils.createApi(ShopInterface.class).store(),1);
            }
        });

        //实例化布局管理器
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        //mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        shopAdapter = new ShopAdapter(R.layout.item_shop, new ArrayList<ShopPojo>());
        //设置布局管理器
        rvData.setLayoutManager(mLayoutManager);
        //设置间隔样式
        /*mTekeRecyclerview.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.parseColor(getResources().getString(R.string.parseColor)))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());*/
        //设置item点击事件
        rvData.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(ShopDetiasAty.class, null);
            }
        });
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoadingContentDialog();
    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        setStatusBar();
    }

    @Override
    public void onUserInvisible() {
        super.onUserInvisible();
        isLoading = false;
    }

    private void setStatusBar() {
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected boolean setIsInitRequestData() {
        return true;
    }

    @Override
    protected void requestData() {
        doHttp(RetrofitUtils.createApi(ShopInterface.class).store(),1);
    }

    @Override
    public void onSuccess(String result, Call<ResponseBody> call, Response<ResponseBody> response, int what) {
        super.onSuccess(result, call, response, what);
        isLoading = false;
        switch (what){
            case 1:
                mPtrFrame.refreshComplete();
                shopAdapter.removeAll();
                shopPojos = AppJsonUtil.getArrayList(result, ShopPojo.class);
                shopAdapter.setNewData(shopPojos);
                break;
        }
    }

    @Override
    public void onFailure(String result, Call<ResponseBody> call, Response<ResponseBody> response, int what) {
        super.onFailure(result, call, response, what);
        if (mPtrFrame != null) {
            mPtrFrame.refreshComplete();
        }
        isLoading = false;
    }

    @Override
    public void onError(Call<ResponseBody> call, Throwable t, int what) {
        super.onError(call, t, what);
        if (mPtrFrame != null) {
            mPtrFrame.refreshComplete();
        }
        isLoading = false;
    }
}
