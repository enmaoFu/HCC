package com.hcc.app.adapter;

import android.content.Context;

import com.em.baseframe.adapter.listview.CommonAdapter;
import com.em.baseframe.adapter.listview.ViewHolder;
import com.hcc.app.R;
import com.hcc.app.pojo.ShopDetiasImgPojo;
import com.hcc.app.pojo.ShopDetiasTextPojo;

import java.util.List;

/**
 * @title  商店详情页面listView Img 的适配器
 * @date   2018/03/23
 * @author enmaoFu
 */
public class ShopDetiasImgAdapter extends CommonAdapter<ShopDetiasImgPojo> {
    public ShopDetiasImgAdapter(Context context, List<ShopDetiasImgPojo> mList, int itemLayoutId) {
        super(context, mList, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, ShopDetiasImgPojo item, int positon) {
        holder.setImageByUrl(R.id.img,item.getImg());
    }
}
