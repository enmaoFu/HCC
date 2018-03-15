package com.hcc.app.ui.mining;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.hcc.app.R;
import com.hcc.app.adapter.InviteFriendAdapter;
import com.hcc.app.base.BaseAty;
import com.hcc.app.pojo.InviteFriendPojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @title  邀请好友页面
 * @date   2018/03/06
 * @author enmaoFu
 */
public class InviteFriendAty extends BaseAty{

    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private InviteFriendAdapter inviteFriendAdapter;
    //数据源
    private List<InviteFriendPojo> inviteFriendPojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    protected void initData() {

        initToolbar(mToolbar,"邀请好友");

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //实例化适配器
        inviteFriendAdapter = new InviteFriendAdapter(R.layout.item_invite, setData());
        //设置布局管理器
        rvData.setLayoutManager(mLayoutManager);
        //设置间隔样式
        /*mTekeRecyclerview.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.parseColor(getResources().getString(R.string.parseColor)))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());*/
        //大小不受适配器影响
        rvData.setHasFixedSize(true);
        //设置加载动画类型
        inviteFriendAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rvData.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        rvData.setAdapter(inviteFriendAdapter);
    }

    @Override
    protected void requestData() {

    }

    /**
     * 设置数据
     * @return
     */
    public List<InviteFriendPojo> setData(){
        inviteFriendPojos = new ArrayList<>();
        InviteFriendPojo inviteFriendPojo = null;
        for(int i = 0; i < 8; i++){
            inviteFriendPojo = new InviteFriendPojo();
            inviteFriendPojos.add(inviteFriendPojo);
        }
        return inviteFriendPojos;
    }

}
