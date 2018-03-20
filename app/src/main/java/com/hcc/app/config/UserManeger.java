package com.hcc.app.config;

import com.em.baseframe.config.UserInfoManger;
import com.em.baseframe.util.SPUtils;
import com.hcc.app.R;

/**
 * Created by sssbbb on 2018/3/5.
 */

public class UserManeger extends UserInfoManger{

    private static final String STATUS_BAR_COLOR = "statusBarColor";

    private static final String CRP_BLE = "crpBle";

    private static final String CRP_BLE_ADDRESS = "crpBleAddress";

    private static final String CRP_BLE_NAME = "crpBleName";

    /**
     * 保存状态栏的颜色
     * @param color
     */
    public static void setStatusBarColor(int color) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(STATUS_BAR_COLOR, color);
    }

    /**
     * 获取状态栏的颜色
     * 在没有设置的情况下，默认是#769dfc
     * 如果设置过，那么取到的颜色就是设置的颜色
     * @return
     */
    public static int getStatusBarColor() {
        SPUtils spUtils = new SPUtils(FILE);
        return (int) spUtils.get(STATUS_BAR_COLOR, R.color.colorPrimary);
    }

    /**
     * 保存手环绑定状
     * 0：未绑定
     * 1：已绑定
     * @param code
     */
    public static void setCrpBle(int code) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(CRP_BLE, code);
    }

    /**
     * 获取手环是否绑定的状态
     * @return
     */
    public static int getCrpBle() {
        SPUtils spUtils = new SPUtils(FILE);
        return (int) spUtils.get(CRP_BLE, 0);
    }

    /**
     * 保存手环蓝牙MAC地址
     * @param crpBleAddress
     */
    public static void setCrpBleAddress(String crpBleAddress) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(CRP_BLE_ADDRESS, crpBleAddress);
    }

    /**
     * 获取手环蓝牙MAC地址
     * @return
     */
    public static int getCrpBleAddress() {
        SPUtils spUtils = new SPUtils(FILE);
        return (int) spUtils.get(CRP_BLE_ADDRESS, "");
    }

    /**
     * 保存手环蓝牙名称
     * @param crpBleName
     */
    public static void setCrpBleName(String crpBleName) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(CRP_BLE_NAME, crpBleName);
    }

    /**
     * 获取手环蓝牙MAC名称
     * @return
     */
    public static int getCrpBleName() {
        SPUtils spUtils = new SPUtils(FILE);
        return (int) spUtils.get(CRP_BLE_NAME, "");
    }

}
