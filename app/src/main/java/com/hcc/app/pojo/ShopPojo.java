package com.hcc.app.pojo;

/**
 * @title  商店recyclerview的item实体类
 * @date   2018/02/26
 * @author enmaoFu
 */
public class ShopPojo {

    private String img;

    private String title;

    private String number;

    private String price;

    private String usedPrice;

    public ShopPojo(String img, String title, String number, String price, String usedPrice) {
        this.img = img;
        this.title = title;
        this.number = number;
        this.price = price;
        this.usedPrice = usedPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsedPrice() {
        return usedPrice;
    }

    public void setUsedPrice(String usedPrice) {
        this.usedPrice = usedPrice;
    }
}
