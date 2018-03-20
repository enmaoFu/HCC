package com.hcc.app.application;

import android.content.Context;

import com.crrepa.ble.CRPBleClient;
import com.em.baseframe.application.BaseApplication;

/**
 * @title  全局类
 * @date   2017/06/17
 * @author enmaoFu
 */
public class MyApplication extends BaseApplication{

    private CRPBleClient mBleClient;

    public static CRPBleClient getBleClient(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.mBleClient;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBleClient = CRPBleClient.create(this);
    }

}
