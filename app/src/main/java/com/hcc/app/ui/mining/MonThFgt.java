package com.hcc.app.ui.mining;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @title  聚宝盆图表统计本月fragemnt
 * @date   2018/03/12
 * @author enmaoFu
 */
public class MonThFgt extends BaseLazyFgt{

    @BindView(R.id.bar_chart)
    BarChart barChart;

    /**
     * 柱状图数据
     */
    private int[] mDataYs = new int[]{80, 50, 60, 30};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mining_month;
    }

    @Override
    protected void initData() {

        setBarChart(barChart);
        loadBarChartData(barChart);

    }

    @Override
    protected void requestData() {

    }

    /**
     * 加载并设置柱形图的数据
     *
     * @param chart
     */
    private void loadBarChartData(BarChart chart) {
        //所有数据点的集合
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            entries.add(new BarEntry(mDataYs[i], i));
        }
        //柱形数据的集合（左下角）
        BarDataSet mBarDataSet = new BarDataSet(entries, "");
        mBarDataSet.setBarSpacePercent(70f);
        //是否在柱状上绘制数值（Value）
        mBarDataSet.setDrawValues(true);
        //设置柱状上数值（Value）的颜色
        mBarDataSet.setValueTextColor(Color.parseColor("#666666"));
        //设置柱状上数值（Value）的大小
        mBarDataSet.setValueTextSize(12f);
        //设置柱状颜色
        mBarDataSet.setColor(Color.parseColor("#769dfc"));
        //设置点击后颜色
        mBarDataSet.setHighLightColor(Color.parseColor("#769dfc"));
        //BarData表示一个BarChart的所有数据(即一个BarChart中所有折线的数据)
        BarData mBarData = new BarData(getXAxisShowLable(), mBarDataSet);
        //设置数据
        chart.setData(mBarData);
        //设置动画
        chart.animateY(1000);
        //设置100就是进入柱状图Y轴的坐标轴值按10递增，0-10-20-30
        chart.setVisibleYRangeMaximum(100f,YAxis.AxisDependency.LEFT);
        //进入柱状图从Y轴底部的0开始显示（如果最高是100，不设置0的话，那么进入后就要手动滑到0）
        chart.moveViewToY(0,YAxis.AxisDependency.LEFT);
    }

    /**
     * 设置柱形图的样式
     *
     * @param chart
     */
    private void setBarChart(BarChart chart) {
        //设置为空字符串，右下角的字就会隐藏
        chart.setDescription("");
        //设置网格背景
        chart.setDrawGridBackground(false);
        //设置缩放
        chart.setScaleEnabled(false);
        //设置双击不进行缩放
        chart.setDoubleTapToZoomEnabled(false);

        //图例，设为0，左下角就不会出现
        Legend legend = chart.getLegend();
        legend.setTextSize(0f);
        legend.setFormSize(0f);

        //设置X轴
        XAxis xAxis = chart.getXAxis();
        //设置X轴的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //启用画线
        xAxis.setEnabled(true);
        //禁止画网格线（竖线）
        xAxis.setDrawGridLines(false);

        //获得左侧侧坐标轴
        YAxis leftAxis = chart.getAxisLeft();
        //Y轴标识显示在外面
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        //设置右侧坐标轴，隐藏右边的Y轴
        YAxis rightAxis = chart.getAxisRight();
        leftAxis.setLabelCount(10, false);
        //右侧坐标轴线
        rightAxis.setDrawAxisLine(false);
        //右侧坐标轴数组Label
        rightAxis.setDrawLabels(false);
    }

    private ArrayList<String> getXAxisShowLable() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("第一周");
        m.add("第二周");
        m.add("第三周");
        m.add("第四周");
        return m;
    }

}
