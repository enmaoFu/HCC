package com.hcc.app.ui.me;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.hcc.app.R;
import com.hcc.app.adapter.FamilyAdapter;
import com.hcc.app.adapter.InviteFriendAdapter;
import com.hcc.app.base.BaseAty;
import com.hcc.app.pojo.FamilyPojo;
import com.hcc.app.pojo.InviteFriendPojo;
import com.hcc.app.ui.mining.WealthAty;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @title  我的家族谱页面
 * @date   2018/03/20
 * @author enmaoFu
 */
public class FamilyAty extends BaseAty{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.re_data)
    RecyclerView reData;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private FamilyAdapter familyAdapter;
    //数据源
    private List<FamilyPojo> familyPojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_family;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"我的家族谱");

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        familyAdapter = new FamilyAdapter(R.layout.item_family, setData());
        //设置布局管理器
        reData.setLayoutManager(mLayoutManager);
        //设置间隔样式
        reData.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.parseColor("#E2E2E2"))
                        .sizeResId(R.dimen.size_0_5p)
                        .build());
        //大小不受适配器影响
        reData.setHasFixedSize(true);
        //设置加载动画类型
        familyAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        reData.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        reData.setAdapter(familyAdapter);

    }

    @Override
    protected void requestData() {

    }

    /**
     * 设置数据
     * @return
     */
    public List<FamilyPojo> setData(){
        familyPojos = new ArrayList<>();
        FamilyPojo familyPojo = null;
        for(int i = 0; i < 20; i++){
            familyPojo = new FamilyPojo();
            familyPojos.add(familyPojo);
        }
        return familyPojos;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_public, menu);
        menu.getItem(0).setTitle("添加");
        return super.onCreateOptionsMenu(menu);
    }

}
