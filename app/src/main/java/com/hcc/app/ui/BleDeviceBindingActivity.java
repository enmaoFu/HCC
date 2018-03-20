package com.hcc.app.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.crrepa.ble.CRPBleClient;
import com.crrepa.ble.conn.CRPBleConnection;
import com.crrepa.ble.conn.CRPBleDevice;
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener;
import com.crrepa.ble.conn.listener.CRPBloodPressureChangeListener;
import com.crrepa.ble.conn.type.CRPDominantHandType;
import com.crrepa.ble.conn.type.CRPTimeSystemType;
import com.crrepa.ble.conn.type.CRPWatchFacesType;
import com.crrepa.ble.scan.bean.CRPScanDevice;
import com.crrepa.ble.scan.callback.CRPScanCallback;
import com.hcc.app.R;
import com.hcc.app.application.MyApplication;
import com.hcc.app.base.BaseAty;
import com.hcc.app.config.UserManeger;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  搜索到的蓝牙设备列表
 * @date   2018/03/06
 * @author enmaoFu
 */
public class BleDeviceBindingActivity extends BaseAty{

    @BindView(R.id.query_text)
    TextView queryText;
    @BindView(R.id.ble_msg)
    TextView bleMsg;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.binding)
    TextView binding;
    @BindView(R.id.clear)
    TextView clear;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ble_device_binding;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.search,R.id.binding,R.id.clear})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.search:
                break;
            case R.id.binding:
                break;
            case R.id.clear:
                break;
        }
    }

}
