package com.hcc.app.adapter;

import android.content.Context;

import com.em.baseframe.adapter.listview.CommonAdapter;
import com.em.baseframe.adapter.listview.ViewHolder;
import com.hcc.app.pojo.PromotePojo;
import com.hcc.app.pojo.WealthPojo;

import java.util.List;

/**
 * @title  收支记录适配器
 * @date   2018/03/19
 * @author enmaoFu
 */
public class WealthAdapter extends CommonAdapter<WealthPojo> {

    public WealthAdapter(Context context, List<WealthPojo> mList, int itemLayoutId) {
        super(context, mList, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, WealthPojo item, int positon) {
    }
}
