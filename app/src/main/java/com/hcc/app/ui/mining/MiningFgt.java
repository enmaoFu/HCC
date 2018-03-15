package com.hcc.app.ui.mining;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.ui.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  挖矿fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class MiningFgt extends BaseLazyFgt {

    @BindView(R.id.mining_re)
    RelativeLayout miningRe;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mining;
    }

    @Override
    protected void initData() {
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.colorPrimary),00);
    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.colorPrimary),00);
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
