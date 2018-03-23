package com.hcc.app.http;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @title  个人中心接口
 * @date   2018/03/22
 * @author enmaoFu
 */
public interface MeInterface {

    /**
     * 修改个人信息通用接口
     * @param name
     * @param value
     * @return
     */
    @POST("user/quickEdit")
    @FormUrlEncoded
    Call<ResponseBody> quickEdit(@Field("name") String name, @Field("value") String value);

    /**
     * 上传头像
     * @param avator
     * @return
     */
    @Multipart
    @POST("user/editAvator")
    Call<ResponseBody> editAvator(@Part MultipartBody.Part avator);

    /**
     * 用户认证
     * @param username
     * @param card_id
     * @return
     */
    @POST("user/userAuth")
    @FormUrlEncoded
    Call<ResponseBody> userAuth( @Field("username") String username, @Field("card_id") String card_id);

    /**
     * 查询用户认证
     * @return
     */
    @GET("user/getcard")
    Call<ResponseBody> getcard();

}
