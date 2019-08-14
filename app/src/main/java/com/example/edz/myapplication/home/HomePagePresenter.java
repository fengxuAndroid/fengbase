package com.example.edz.myapplication.home;

import com.example.edz.myapplication.base.BasePresenter;
import com.example.edz.myapplication.home.bean.HomePageBean;
import com.example.edz.myapplication.utils.networkutil.CommonSubscriber;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public class HomePagePresenter extends BasePresenter<HomePageModel, HomePageView> {


    public HomePagePresenter(HomePageView view) {
        initPresenter(view);
    }


    /**
     * 登录
     */
    public void getHomePageData(String token, String username, String password) {

        addSubscribe(mModel.getHomePageData(token, username, password)
                .subscribeWith(new CommonSubscriber<HomePageBean>() {

                    @Override
                    protected void startLoading() {
                        mView.showLoading("正在获取数据，请稍后...");
                    }

                    @Override
                    protected void onSuccess(HomePageBean response) {
                        mView.stopLoading();
                        mView.getHomePageSuc(response);
                    }

                    @Override
                    protected void onFail(String errorMsg) {
                        mView.getHomePageFail(errorMsg);
                        mView.stopLoading();
                    }
                }));

    }


    @Override
    protected HomePageModel createModel() {
        return new HomePageModel();
    }
}
