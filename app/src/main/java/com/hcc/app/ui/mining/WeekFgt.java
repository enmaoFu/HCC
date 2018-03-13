package com.hcc.app.ui.mining;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.hcc.app.R;
import com.hcc.app.base.BaseLazyFgt;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * @title  聚宝盆图表统计本周fragemnt
 * @date   2018/03/12
 * @author enmaoFu
 */
public class WeekFgt extends BaseLazyFgt{

    @BindView(R.id.line_chart)
    LineChart lineChart;

    /**
     * 折线图数据
     */
    private int[] mDataYs = new int[]{80, 40, 20, 30, 40, 10, 10};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mining_week;
    }

    @Override
    protected void initData() {
        setLineChart(lineChart);
        loadLineChartData(lineChart);
    }
    @Override
    protected void requestData() {

    }

    /**
     * 为折线图设置数据
     *
     * @param chart
     */
    private void loadLineChartData(final LineChart chart) {
        //所有线的List
        ArrayList<LineDataSet> allLinesList = new ArrayList<LineDataSet>();

        ArrayList<Entry> entryList = new ArrayList<Entry>();
        for (int i = 0; i < 7; i++) {
            //Entry(yValue,xIndex);一个Entry表示一个点，第一个参数为y值，第二个为X轴List的角标
            entryList.add(new Entry(mDataYs[i], i));
        }
        //折线数据的集合（左下角）
        LineDataSet dataSet = new LineDataSet(entryList, "");
        //设置折线的宽度
        dataSet.setLineWidth(2.5f);
        //设置折线为曲线
        dataSet.setDrawCubic(true);
        //设置折线的颜色
        dataSet.setColor(Color.parseColor("#769dfc"));
        //设置折线原点的大小
        dataSet.setCircleSize(4.5f);
        //设置折线原点的颜色
        dataSet.setCircleColor(Color.parseColor("#769dfc"));
        //设置折线原点是否是空心
        dataSet.setDrawCircleHole(false);
        //是否在点上绘制数值（Value）
        dataSet.setDrawValues(true);
        //设置点上数值（Value）的颜色
        dataSet.setValueTextColor(Color.parseColor("#666666"));
        //设置点上数值（Value）的大小
        dataSet.setValueTextSize(12f);
        //把以上折线的设置加入集合
        allLinesList.add(dataSet);
        //LineData表示一个LineChart的所有数据(即一个LineChart中所有折线的数据)
        LineData mChartData = new LineData(getXAxisShowLable(), allLinesList);
        //设置数据
        chart.setData((LineData) mChartData);
        //设置动画
        chart.animateX(1000);
        //设置100就是进入折线图Y轴的坐标轴值按10递增，0-10-20-30
        chart.setVisibleYRangeMaximum(100f,YAxis.AxisDependency.LEFT);
        //进入折线图从Y轴底部的0开始显示（如果最高是100，不设置0的话，那么进入后就要手动滑到0）
        chart.moveViewToY(0,YAxis.AxisDependency.LEFT);
    }

    /**
     * 设置折线图的样式
     *
     * @param chart
     */
    private void setLineChart(LineChart chart) {

        //设置为空字符串，右下角的字就会隐藏
        chart.setDescription("");
        //设置网格背景
        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);
        chart.setScaleXEnabled(false);
        chart.setScaleYEnabled(false);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setHighlightPerDragEnabled(false);
        //设置双击不进行缩放
        chart.setDoubleTapToZoomEnabled(false);
        chart.setAutoScaleMinMaxEnabled(false);

        //图例，设为0，左下角就不会出现
        Legend legend = chart.getLegend();
        legend.setTextSize(0f);
        legend.setFormSize(0f);

        //获得X轴
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
        //右侧坐标轴线
        rightAxis.setDrawAxisLine(false);
        //右侧坐标轴数组Label
        rightAxis.setDrawLabels(false);

    }

    private ArrayList<String> getXAxisShowLable() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("周一");
        m.add("周二");
        m.add("周三");
        m.add("周四");
        m.add("周五");
        m.add("周六");
        m.add("周七");
        return m;
    }

}
