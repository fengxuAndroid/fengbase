package com.example.edz.myapplication.home;

import com.example.edz.myapplication.base.ApiService;
import com.example.edz.myapplication.base.BaseModel;
import com.example.edz.myapplication.base.RetrofitFactory;
import com.example.edz.myapplication.home.bean.HomePageBean;
import com.example.edz.myapplication.utils.networkutil.RxSchedulerHepler;


import io.reactivex.Flowable;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public class HomePageModel extends BaseModel {
    /**
     * 登录
     * @param username 登录名
     * @param password 密码
     */

    public Flowable<HomePageBean> getHomePageData(String token,String username,String password) {

        return  RetrofitFactory.getRetrofit().create(ApiService.class).getHomePage(token,username,password).compose(RxSchedulerHepler.handleMyResult());

    }

}
