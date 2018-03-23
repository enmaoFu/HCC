package com.hcc.app.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @title  用户登录注册接口
 * @date   2018/03/21
 * @author enmaoFu
 */
public interface UserInterface {

    /**
     * 登录
     * @param author
     * @param action
     * @param token
     * @param username
     * @param pwd
     * @param code
     * @return
     */
    @POST("login/login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("author") String author, @Field("action") String action, @Field("token") String token, @Field("username") String username,
                             @Field("pwd") String pwd, @Field("code") int code);

    /**
     * 注册
     * @param author
     * @param action
     * @param token
     * @param username
     * @param code
     * @param pwd
     * @param invite_code
     * @return
     */
    @POST("login/register")
    @FormUrlEncoded
    Call<ResponseBody> register(@Field("author") String author, @Field("action") String action, @Field("token") String token, @Field("username") String username,
                             @Field("code") int code, @Field("pwd") String pwd, @Field("invite_code") String invite_code);

    /**
     * 注册获取验证码
     * @param author
     * @param action
     * @param token
     * @param phone
     * @return
     */
    @POST("login/recode")
    @FormUrlEncoded
    Call<ResponseBody> recode(@Field("author") String author, @Field("action") String action, @Field("token") String token, @Field("phone") String phone);

    /**
     * 登录获取验证码
     * @param author
     * @param action
     * @param token
     * @param phone
     * @return
     */
    @POST("login/setcode")
    @FormUrlEncoded
    Call<ResponseBody> setcode(@Field("author") String author, @Field("action") String action, @Field("token") String token, @Field("phone") String phone);

}
