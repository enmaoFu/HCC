package com.hcc.app.adapter;

import android.content.Context;

import com.em.baseframe.adapter.listview.CommonAdapter;
import com.em.baseframe.adapter.listview.ViewHolder;
import com.hcc.app.pojo.PromotePojo;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * @title  提升算力适配器
 * @date   2018/03/15
 * @author enmaoFu
 */
public class PromoteAdapter extends CommonAdapter<PromotePojo> {

    public PromoteAdapter(Context context, List<PromotePojo> mList, int itemLayoutId) {
        super(context, mList, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, PromotePojo item, int positon) {
    }
}
