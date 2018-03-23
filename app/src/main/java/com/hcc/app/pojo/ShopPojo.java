package com.hcc.app.pojo;

/**
 * @title  商店recyclerview的item实体类
 * @date   2018/02/26
 * @author enmaoFu
 */
public class ShopPojo {

    private int id;

    private String title;

    private String pid;

    private int money;

    private int nowmoney;

    private int pnum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNowmoney() {
        return nowmoney;
    }

    public void setNowmoney(int nowmoney) {
        this.nowmoney = nowmoney;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }
}
