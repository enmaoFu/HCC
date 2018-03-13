package com.hcc.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hcc.app.pojo.CaseNotePojo;

import java.util.List;

/**
 * @title  病历适配器
 * @date   2018/03/05
 * @author enmaoFu
 */
public class CaseNoteAdapter extends BaseQuickAdapter<CaseNotePojo,BaseViewHolder> {

    public CaseNoteAdapter(int layoutResId, List<CaseNotePojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CaseNotePojo item) {

    }
}
