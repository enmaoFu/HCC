package com.hcc.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hcc.app.pojo.CheckNotePojo;

import java.util.List;

/**
 * @title  检查单适配器
 * @date   2018/03/05
 * @author enmaoFu
 */
public class CheckNoteAdapter extends BaseQuickAdapter<CheckNotePojo,BaseViewHolder> {
    public CheckNoteAdapter(int layoutResId, List<CheckNotePojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckNotePojo item) {

    }
}
