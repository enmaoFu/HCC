package com.hcc.app.adapter;

import android.content.Context;

import com.em.baseframe.adapter.listview.CommonAdapter;
import com.em.baseframe.adapter.listview.ViewHolder;
import com.hcc.app.pojo.ShopDetiasTextPojo;

import java.util.List;

/**
 * @title  商店详情页面listView Text 的适配器
 * @date   2018/03/23
 * @author enmaoFu
 */
public class ShopDetiasTextAdapter extends CommonAdapter<ShopDetiasTextPojo> {
    public ShopDetiasTextAdapter(Context context, List<ShopDetiasTextPojo> mList, int itemLayoutId) {
        super(context, mList, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, ShopDetiasTextPojo item, int positon) {

    }
}
