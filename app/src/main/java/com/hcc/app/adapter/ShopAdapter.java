package com.hcc.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hcc.app.R;
import com.hcc.app.pojo.ShopPojo;

import java.util.List;

/**
 * @title  商店recyclerview适配器
 * @date   2018/02/26
 * @author enmaoFu
 */
public class ShopAdapter extends BaseQuickAdapter<ShopPojo,BaseViewHolder>{

    public ShopAdapter(int layoutResId, List<ShopPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopPojo item) {
        helper.setTextLine(R.id.item_shop_price_n);
        helper.setImageByUrl(R.id.item_shop_bg,item.getImg());
    }
}
