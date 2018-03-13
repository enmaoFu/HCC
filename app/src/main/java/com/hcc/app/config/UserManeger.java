package com.hcc.app.config;

import com.em.baseframe.config.UserInfoManger;
import com.em.baseframe.util.SPUtils;

/**
 * Created by sssbbb on 2018/3/5.
 */

public class UserManeger extends UserInfoManger{

    private static final String STATUS_BAR_COLOR = "statusBarColor";

    /**
     * 保存状态栏的颜色
     * @param color
     */
    public static void setStatusBarColor(String color) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(STATUS_BAR_COLOR, color);
    }

    /**
     * 获取状态栏的颜色
     * 在没有设置的情况下，默认是#769dfc
     * 如果设置过，那么取到的颜色就是设置的颜色
     * @return
     */
    public static String getStatusBarColor() {
        SPUtils spUtils = new SPUtils(FILE);
        return (String) spUtils.get(STATUS_BAR_COLOR, "#769dfc");
    }

}
