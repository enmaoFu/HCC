package com.hcc.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hcc.app.pojo.CheckNotePojo;
import com.hcc.app.pojo.PrescriptionNotePojo;

import java.util.List;

/**
 * @title  处方笺适配器
 * @date   2018/03/05
 * @author enmaoFu
 */
public class PrescriptionNoteAdapter extends BaseQuickAdapter<PrescriptionNotePojo,BaseViewHolder> {
    public PrescriptionNoteAdapter(int layoutResId, List<PrescriptionNotePojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PrescriptionNotePojo item) {

    }
}
