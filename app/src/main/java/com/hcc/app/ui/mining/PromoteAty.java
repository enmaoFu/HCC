package com.hcc.app.ui.mining;

import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;
import android.widget.Scroller;

import com.em.baseframe.view.gradview.GridViewForScrolview;
import com.hcc.app.R;
import com.hcc.app.adapter.PromoteAdapter;
import com.hcc.app.base.BaseAty;
import com.hcc.app.pojo.PromotePojo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @title  提升算力页面
 * @date   2018/03/15
 * @author enmaoFu
 */
public class PromoteAty extends BaseAty{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.gridview)
    GridViewForScrolview gridview;
    @BindView(R.id.scroll)
    ScrollView scrollView;

    private PromoteAdapter promoteAdapter;
    private List<PromotePojo> promotePojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promote;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"提升算力");
        promoteAdapter = new PromoteAdapter(this, setData(), R.layout.item_promote);
        gridview.setAdapter(promoteAdapter);
        gridview.setFocusable(false);
        scrollView.smoothScrollTo(0,20);
    }

    @Override
    protected void requestData() {

    }

    public List<PromotePojo> setData(){
        promotePojos = new ArrayList<>();
        PromotePojo paPromotePojo = null;
        for(int i = 0; i < 16; i++){
            paPromotePojo = new PromotePojo("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2577947464,883329486&fm=27&gp=0.jpg");
            promotePojos.add(paPromotePojo);
        }
        return promotePojos;
    }

}
