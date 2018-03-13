package com.hcc.app.ui.medical;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.em.baseframe.adapter.recyclerview.BaseQuickAdapter;
import com.em.baseframe.view.statusbar.StatusBarUtil;
import com.hcc.app.R;
import com.hcc.app.adapter.CheckNoteAdapter;
import com.hcc.app.adapter.PrescriptionNoteAdapter;
import com.hcc.app.base.BaseFgt;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.pojo.CheckNotePojo;
import com.hcc.app.pojo.PrescriptionNotePojo;

import java.util.List;

import butterknife.BindView;

/**
 * @title  处方笺fragemnt
 * @date   2018/03/02
 * @author enmaoFu
 */
public class PrescriptionNoteFgt extends BaseLazyFgt {

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    //recyclerview布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //适配器
    private PrescriptionNoteAdapter prescriptionNoteAdapter;
    //数据源
    private List<PrescriptionNotePojo> prescriptionNotePojos;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_medical_prescription_note;
    }

    @Override
    protected void initData() {

        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //实例化适配器
        prescriptionNoteAdapter = new PrescriptionNoteAdapter(R.layout.item_prescription_note, null);
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
        prescriptionNoteAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //设置删除动画类型
        rvData.setItemAnimator(new DefaultItemAnimator());
        //设置空数据页面
        setEmptyView(prescriptionNoteAdapter);
        //设置adapter
        rvData.setAdapter(prescriptionNoteAdapter);

    }

    @Override
    protected void requestData() {

    }

    /**
     * 设置处方笺的RecyclerView空数据页面
     *
     * @param quickAdapter
     */
    public void setEmptyView(BaseQuickAdapter quickAdapter) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_medical_null, null, false);
        quickAdapter.setEmptyView(view);
    }

}
