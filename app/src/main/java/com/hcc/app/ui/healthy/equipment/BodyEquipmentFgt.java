package com.hcc.app.ui.healthy.equipment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.crrepa.ble.CRPBleClient;
import com.crrepa.ble.conn.CRPBleConnection;
import com.crrepa.ble.conn.CRPBleDevice;
import com.hcc.app.R;
import com.hcc.app.application.MyApplication;
import com.hcc.app.base.BaseLazyFgt;
import com.hcc.app.config.UserManeger;
import com.hcc.app.ui.BleDeviceBindingActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @title  随身设备fragemnt
 * @date   2018/02/26
 * @author enmaoFu
 */
public class BodyEquipmentFgt extends BaseLazyFgt {

    @BindView(R.id.add_device)
    RelativeLayout addDevice;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_body_equipment;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
    }

    @OnClick({R.id.add_device,R.id.pressure_lin})
    @Override
    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.add_device:
                startActivityForResult(BleDeviceBindingActivity.class,null,1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                break;
        }
    }

}
