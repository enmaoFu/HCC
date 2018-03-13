package com.hcc.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hcc.app.pojo.PrescriptionNotePojo;
import com.hcc.app.pojo.TestNotePojo;

import java.util.List;

/**
 * @title  检验单适配器
 * @date   2018/03/05
 * @author enmaoFu
 */
public class TestNoteAdapter extends BaseQuickAdapter<TestNotePojo,BaseViewHolder> {
    public TestNoteAdapter(int layoutResId, List<TestNotePojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestNotePojo item) {

    }
}
