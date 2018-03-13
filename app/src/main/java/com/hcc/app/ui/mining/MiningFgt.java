package com.hcc.app.ui.mining;

import android.view.View;

import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;

import butterknife.OnClick;

/**
 * @title  挖矿fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class MiningFgt extends BaseLazyFgt {

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

    @OnClick({R.id.jbp})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.jbp:
                startActivity(WealthAty.class,null);
                break;
        }
    }
}
