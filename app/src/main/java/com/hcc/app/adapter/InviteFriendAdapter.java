package com.hcc.app.adapter;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.adapter.recyclerview.BaseViewHolder;
import com.hcc.app.pojo.CaseNotePojo;
import com.hcc.app.pojo.InviteFriendPojo;

import java.util.List;

/**
 * @title  邀请好友适配器
 * @date   2018/03/06
 * @author enmaoFu
 */
public class InviteFriendAdapter extends BaseQuickAdapter<InviteFriendPojo,BaseViewHolder> {

    public InviteFriendAdapter(int layoutResId, List<InviteFriendPojo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InviteFriendPojo item) {

    }
}
