package com.example.edz.myapplication.base;

import com.example.edz.myapplication.home.bean.HomePageBean;
import com.example.edz.myapplication.home.bean.ResponseData;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author 冯旭 2019-07-19
 * @ function 所有接口
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public interface ApiService {

    String BASE_URL = "https://192.168.2.195:7875/interface/store/";

    //限时购头部分类
//    @FormUrlEncoded
//    @POST()
//    Flowable<ResponseData<HomePageBean>> getHomePage(@Field("params") String pack_no);

//    Flowable<ResponseData<HomePageBean>> getHomePage(@Body RequestParams parama);
//    Flowable<ResponseData<HomePageBean>> getHomePage(@Body() Map pack_no);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("login")
    Flowable<ResponseData<HomePageBean>> getHomePage(@Header("token") String token, @Field("username") String username, @Field("password") String password);


}
