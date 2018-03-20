package com.hcc.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hcc.app.R;
import com.hcc.app.pojo.FamilyPojo;
import com.hcc.app.pojo.ShopPojo;

import java.util.List;

/**
 * @title  我的家族谱recyclerview适配器
 * @date   2018/03/20
 * @author enmaoFu
 */
public class FamilyAdapter extends BaseQuickAdapter<FamilyPojo,BaseViewHolder>{

    public FamilyAdapter(int layoutResId, List<FamilyPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyPojo item) {
    }
}
