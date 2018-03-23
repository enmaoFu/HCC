package com.em.baseframe.config;

import com.em.baseframe.util.SPUtils;

/**
 * @title  获取设置用户登录状态
 * @date   2017/06/17
 * @author enmaoFu
 */
public abstract class UserInfoManger {

	/**
	 * 登陆状态
	 */
	public static final String FILE = "userConfig";

	/**
	 * openid（token）
	 */
	private static final String OPEN_ID = "openid";

	/**
	 * 获得登陆状态
	 */
	public static boolean isLogin() {
		SPUtils spUtils = new SPUtils("userConfig");
		return (Boolean) spUtils.get("isLogin", false);
	}

	/**
	 * 设置登陆状态
	 */
	public static void setIsLogin( boolean b) {
		SPUtils spUtils = new SPUtils("userConfig");
		spUtils.put("isLogin", b);
	}

	/**
	 * 保存openid（token）
	 * @param openid
	 */
	public static void setOpenid(String openid) {
		SPUtils spUtils = new SPUtils(FILE);
		spUtils.put(OPEN_ID, openid);
	}

	/**
	 * 获取openid（token）
	 * @return
	 */
	public static String getOpenid() {
		SPUtils spUtils = new SPUtils(FILE);
		return (String) spUtils.get(OPEN_ID, "");
	}

}
