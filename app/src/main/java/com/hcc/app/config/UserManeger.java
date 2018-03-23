package com.hcc.app.config;

import com.em.baseframe.config.UserInfoManger;
import com.em.baseframe.util.SPUtils;
import com.hcc.app.R;

/**
 * Created by sssbbb on 2018/3/5.
 */

public class UserManeger extends UserInfoManger{

    /**
     * 状态栏沉浸式颜色保存
     */
    private static final String STATUS_BAR_COLOR = "statusBarColor";

    /**
     * 用户名
     */
    private static final String USERNAME = "username";

    /**
     * 用户头像
     */
    private static final String HEAD = "head";

    /**
     * 用户性别
     */
    private static final String SEX = "sex";

    /**
     * 用户身高
     */
    private static final String HEIGHT = "height";

    /**
     * 用户血型
     */
    private static final String BLOOD = "blood";

    /**
     * 用户认证状态
     */
    private static final String STATUS = "status";

    /**
     * 用户出生日期
     */
    private static final String DATE = "date";

    /**
     * 用户体重
     */
    private static final String WEIGHT = "weight";

    /**
     * 用户过敏源
     */
    private static final String ALLERGY = "allergy";

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
     * 保存用户名
     * @param username
     */
    public static void setUsername(String username) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(USERNAME, username);
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUsername() {
        SPUtils spUtils = new SPUtils(FILE);
        return (String) spUtils.get(USERNAME, "");
    }

    /**
     * 保存用户头像
     * @param head
     */
    public static void setHead(String head) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(HEAD, head);
    }

    /**
     * 获取用户头像
     * @return
     */
    public static String getHead() {
        SPUtils spUtils = new SPUtils(FILE);
        return (String) spUtils.get(HEAD, "");
    }

    /**
     * 保存用户性别
     * @param sex
     */
    public static void setSex(String sex) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(SEX, sex);
    }

    /**
     * 获取用户性别
     * @return
     */
    public static String getSex() {
        SPUtils spUtils = new SPUtils(FILE);
        return (String) spUtils.get(SEX, "");
    }

    /**
     * 保存用户身高
     * @param height
     */
    public static void setHeight(int height) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(HEIGHT, height);
    }

    /**
     * 获取用户身高
     * @return
     */
    public static int getHeight() {
        SPUtils spUtils = new SPUtils(FILE);
        return (int) spUtils.get(HEIGHT, 0);
    }

    /**
     * 保存用户血型
     * @param blood
     */
    public static void setBlood(String blood) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(BLOOD, blood);
    }

    /**
     * 获取用户血型
     * @return
     */
    public static String getBlood() {
        SPUtils spUtils = new SPUtils(FILE);
        return (String) spUtils.get(BLOOD, "");
    }

    /**
     * 保存用户认证状态
     * @param status
     */
    public static void setStatus(int status) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(STATUS, status);
    }

    /**
     * 获取用户认证状态
     * @return
     */
    public static int getStatus() {
        SPUtils spUtils = new SPUtils(FILE);
        return (int) spUtils.get(STATUS, 0);
    }

    /**
     * 保存用户出生日期
     * @param date
     */
    public static void setDate(String date) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(DATE, date);
    }

    /**
     * 获取用户出生日期
     * @return
     */
    public static String getDate() {
        SPUtils spUtils = new SPUtils(FILE);
        return (String) spUtils.get(DATE, "");
    }

    /**
     * 保存用户体重
     * @param weight
     */
    public static void setWeight(int weight) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(WEIGHT, weight);
    }

    /**
     * 获取用户体重
     * @return
     */
    public static int getWeight() {
        SPUtils spUtils = new SPUtils(FILE);
        return (int) spUtils.get(WEIGHT, 0);
    }

    /**
     * 保存用户过敏源
     * @param allergy
     */
    public static void setAllergy(String allergy) {
        SPUtils spUtils = new SPUtils(FILE);
        spUtils.put(ALLERGY, allergy);
    }

    /**
     * 获取用户过敏源
     * @return
     */
    public static String getAllergy() {
        SPUtils spUtils = new SPUtils(FILE);
        return (String) spUtils.get(ALLERGY, "");
    }

}
