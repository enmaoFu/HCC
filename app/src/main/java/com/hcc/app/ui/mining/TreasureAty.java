package com.hcc.app.ui.mining;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcc.app.R;
import com.hcc.app.base.BaseAty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  聚宝盆主页面
 * @date   2018/03/15
 * @author enmaoFu
 */
public class TreasureAty extends BaseAty{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.save_re)
    RelativeLayout saveRe;
    @BindView(R.id.take_re)
    RelativeLayout takeRe;
    @BindView(R.id.save_img)
    ImageView saveImg;
    @BindView(R.id.save_text)
    TextView saveText;
    @BindView(R.id.take_img)
    ImageView takeImg;
    @BindView(R.id.take_text)
    TextView takeText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_treasure;
    }

    @Override
    protected void initData() {
        initToolbar(mToolbar,"聚宝盆");
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.save_re,R.id.take_re})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.save_re:
                saveRe.setBackground(getResources().getDrawable(R.drawable.mining_treasure_left_roundy));
                takeRe.setBackground(getResources().getDrawable(R.drawable.mining_treasure_right_roundn));
                saveImg.setImageResource(R.drawable.saven);
                saveText.setTextColor(Color.parseColor("#769dfc"));
                takeImg.setImageResource(R.drawable.tqy);
                takeText.setTextColor(Color.parseColor("#ffffff"));
                break;
            case R.id.take_re:
                saveRe.setBackground(getResources().getDrawable(R.drawable.mining_treasure_left_roundn));
                takeRe.setBackground(getResources().getDrawable(R.drawable.mining_treasure_right_roundy));
                saveImg.setImageResource(R.drawable.savey);
                saveText.setTextColor(Color.parseColor("#ffffff"));
                takeImg.setImageResource(R.drawable.tqn);
                takeText.setTextColor(Color.parseColor("#769dfc"));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_public, menu);
        menu.getItem(0).setTitle("收支记录");
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(WealthAty.class,null);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
