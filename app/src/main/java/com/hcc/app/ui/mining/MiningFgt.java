package com.hcc.app.ui.mining;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.em.baseframe.base.BaseActivity;
import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.ui.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * @title  挖矿fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class MiningFgt extends BaseLazyFgt {

    /**
     * 透明状态栏（图片）
     */
    private boolean isHide = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mining;
    }

    @Override
    protected void initData() {
        //透明状态栏
        setStatusBar();
    }


    @Override
    public void onUserVisible() {
        super.onUserVisible();
        //透明状态栏
        setStatusBar();
    }

    private void setStatusBar() {
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorPrimary));
        StatusBarCompat.translucentStatusBar(getActivity(), isHide);
    }

    @Override
    protected boolean setIsInitRequestData() {
        return true;
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.treasure,R.id.friend,R.id.ptomote})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.treasure:
                startActivity(TreasureAty.class,null);
                break;
            case R.id.friend:
                startActivity(InviteFriendAty.class,null);
                break;
            case R.id.ptomote:
                startActivity(PromoteAty.class,null);
                break;
        }
    }
}
