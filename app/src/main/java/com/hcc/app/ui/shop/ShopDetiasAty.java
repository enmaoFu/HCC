package com.hcc.app.ui.shop;

import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;

import com.em.banner.Banner;
import com.em.banner.BannerConfig;
import com.em.banner.Transformer;
import com.em.baseframe.view.listview.ListViewForScrollView;
import com.hcc.app.R;
import com.hcc.app.adapter.ShopDetiasImgAdapter;
import com.hcc.app.adapter.ShopDetiasTextAdapter;
import com.hcc.app.base.BaseAty;
import com.hcc.app.pojo.ShopDetiasImgPojo;
import com.hcc.app.pojo.ShopDetiasTextPojo;
import com.hcc.app.view.BannerImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 * @title  商店详情页面
 * @date   2018/03/23
 * @author enmaoFu
 */
public class ShopDetiasAty extends BaseAty{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.listview_text)
    ListViewForScrollView listviewText;
    @BindView(R.id.scro)
    ScrollView scro;
    @BindView(R.id.listview_img)
    ListViewForScrollView listviewImg;

    private ShopDetiasTextAdapter shopDetiasTextAdapter;
    private List<ShopDetiasTextPojo> shopDetiasTextPojos;

    private ShopDetiasImgAdapter shopDetiasImgAdapter;
    private List<ShopDetiasImgPojo> shopDetiasImgPojos;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_detials;
    }

    @Override
    protected void initData() {

        initToolbar(mToolbar,"详情");

        //设置图片加载集合
        List<String> imageArray = new ArrayList<>();
        imageArray.add("http://img5q.duitang.com/uploads/item/201504/29/20150429185037_BwuRP.jpeg");
        imageArray.add("http://i1.hdslb.com/video/98/9808be3eb2f11c4a7aed287a5aab1b3a.jpg");
        imageArray.add("http://i1.hdslb.com/video/98/9808be3eb2f11c4a7aed287a5aab1b3a.jpg");

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        mBanner.setImages(imageArray);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        shopDetiasTextAdapter = new ShopDetiasTextAdapter(this, setTextData(), R.layout.item_shop_detials_text);
        listviewText.setAdapter(shopDetiasTextAdapter);
        listviewText.setFocusable(false);

        shopDetiasImgAdapter = new ShopDetiasImgAdapter(this, setImgData(), R.layout.item_shop_detials_img);
        listviewImg.setAdapter(shopDetiasImgAdapter);
        listviewImg.setFocusable(false);

        scro.smoothScrollTo(0,20);

    }

    @Override
    protected void requestData() {

    }

    public List<ShopDetiasTextPojo> setTextData(){
        shopDetiasTextPojos = new ArrayList<>();
        ShopDetiasTextPojo shopDetiasTextPojo = null;
        for(int i = 0; i < 10; i++){
            shopDetiasTextPojo = new ShopDetiasTextPojo();
            shopDetiasTextPojos.add(shopDetiasTextPojo);
        }
        return shopDetiasTextPojos;
    }

    public List<ShopDetiasImgPojo> setImgData(){
        shopDetiasImgPojos = new ArrayList<>();
        ShopDetiasImgPojo shopDetiasImgPojo1 = new ShopDetiasImgPojo("https://img.alicdn.com/imgextra/i3/2981840950/TB2yjtza4SYBuNjSspjXXX73VXa_!!2981840950.jpg");
        ShopDetiasImgPojo shopDetiasImgPojo2 = new ShopDetiasImgPojo("https://img.alicdn.com/imgextra/i2/2981840950/TB2CbpAaY1YBuNjSszhXXcUsFXa_!!2981840950.jpg");
        shopDetiasImgPojos.add(shopDetiasImgPojo1);
        shopDetiasImgPojos.add(shopDetiasImgPojo2);
        return shopDetiasImgPojos;
    }

}
