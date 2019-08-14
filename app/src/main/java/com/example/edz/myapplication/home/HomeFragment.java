package com.example.edz.myapplication.home;

import android.util.Log;

import com.example.edz.myapplication.R;
import com.example.edz.myapplication.base.BaseFragment;
import com.example.edz.myapplication.home.bean.HomePageBean;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 * @ Description: 首页
 */
public class HomeFragment extends BaseFragment<HomePagePresenter> implements HomePageView {


    @Override
    protected int getViewResId() {
        return R.layout.frag_main_home;
    }

    @Override
    protected void initView() {

        //登录接口
        mPresenter.getHomePageData("1111111111111", "test001", "123456");

    }

    @Override
    protected HomePagePresenter getPresenter() {
        return new HomePagePresenter(this);
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void getHomePageSuc(HomePageBean homeFrageBean) {

        Log.e("111111111111", homeFrageBean.getUsername());

    }

    @Override
    public void getHomePageFail(String msg) {
        Log.e("1111111112222222", msg);
    }
}
