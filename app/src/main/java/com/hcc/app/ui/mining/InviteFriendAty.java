package com.hcc.app.ui.mining;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.example.qrlibrary.qrcode.utils.QRCodeUtil;
import com.hcc.app.R;
import com.hcc.app.adapter.InviteFriendAdapter;
import com.hcc.app.base.BaseAty;
import com.hcc.app.pojo.InviteFriendPojo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.invite_img)
    ImageView invite_img;

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
    public boolean setIsInitRequestData() {
        return false;
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.invite_down})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.invite_down:
                startActivity(InviteFriendImageAty.class,null);
                break;
        }
    }

    /**
     * 设置数据
     * @return
     */
    public List<InviteFriendPojo> setData(){
        inviteFriendPojos = new ArrayList<>();
        InviteFriendPojo inviteFriendPojo = null;
        for(int i = 0; i < 5; i++){
            inviteFriendPojo = new InviteFriendPojo();
            inviteFriendPojos.add(inviteFriendPojo);
        }
        return inviteFriendPojos;
    }

}
