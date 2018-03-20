package com.hcc.app.ui.me;

import android.net.Uri;
import android.support.v7.widget.Toolbar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hcc.app.R;
import com.hcc.app.base.BaseAty;

import butterknife.BindView;

/**
 * @title  身份认证页面
 * @date   2018/03/20
 * @author enmaoFu
 */
public class IdentityAty extends BaseAty{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.head)
    SimpleDraweeView head;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_identity;
    }

    @Override
    protected void initData() {

        initToolbar(mToolbar,"身份认证");

        Uri uri = Uri.parse("http://wx1.sinaimg.cn/thumb150/006DLFVFgy1fop6mhqouyj30xc0xc0y0.jpg");
        head.setImageURI(uri);
    }

    @Override
    protected void requestData() {

    }
}
