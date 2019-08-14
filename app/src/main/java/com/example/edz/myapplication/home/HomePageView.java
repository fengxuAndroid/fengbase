package com.example.edz.myapplication.home;

import com.example.edz.myapplication.base.IBaseView;
import com.example.edz.myapplication.home.bean.HomePageBean;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public interface HomePageView extends IBaseView {

    /**
     * 首页接口成功
     */
    void getHomePageSuc(HomePageBean homeFrageBean);

    /**
     * 首页接口失败
     */
    void getHomePageFail(String msg);
}
