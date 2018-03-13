package com.hcc.app.ui.user;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hcc.app.R;
import com.hcc.app.base.BaseAty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  注册页面
 * @date   2018/03/06
 * @author enmaoFu
 */
public class RegisterAty extends BaseAty{

    @BindView(R.id.register_select_number)
    Spinner registerSelectNumber;

    /**
     * 定义一个String类型的List数组作为数据源
     */
    private List<String> dataList;

    /**
     * 定义一个ArrayAdapter适配器作为spinner的数据适配器
     */
    private ArrayAdapter<String> adapterSpinner;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        setSpinnerData();
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.back})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

    public void setSpinnerData(){

        //为dataList赋值，将下面这些数据添加到数据源中
        dataList = new ArrayList<String>();
        dataList.add("+86");
        dataList.add("+87");

        /*为spinner定义适配器，也就是将数据源存入adapter，这里需要三个参数
        1. 第一个是Context（当前上下文），这里就是this
        2. 第二个是spinner的布局样式，这里用android系统提供的一个样式
        3. 第三个就是spinner的数据源，这里就是dataList*/
        adapterSpinner = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataList);

        //为适配器设置下拉列表下拉时的菜单样式。
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //为spinner绑定我们定义好的数据适配器
        registerSelectNumber.setAdapter(adapterSpinner);
    }

}
