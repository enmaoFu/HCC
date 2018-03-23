package com.hcc.app.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @title  商城接口
 * @date   2018/03/21
 * @author enmaoFu
 */
public interface ShopInterface {

    /**
     * 获取商城列表
     * @return
     */
    @GET("index/store")
    Call<ResponseBody> store();

}
