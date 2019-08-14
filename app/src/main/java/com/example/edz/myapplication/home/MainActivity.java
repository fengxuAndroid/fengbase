package com.example.edz.myapplication.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.chaychan.library.BottomBarLayout;
import com.example.edz.myapplication.R;
import com.example.edz.myapplication.base.BaseActivity;
import com.example.edz.myapplication.boring.PupilBoringFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    /**
     * ViewPager
     */
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    /**
     * 底部tab栏
     */
    @BindView(R.id.bbl_main)
    BottomBarLayout bblMain;

    /**
     * 主页五个模块
     */
    private List<Fragment> mFragments;

    @Override
    protected int getResViewId() {
        return R.layout.act_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //关闭右滑退出
        closeSwipeBack();
        //初始化ViewPager
        initViewPager();
    }

    /**
     * 初始化五个主页模块
     */
    private void initViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new PupilBoringFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());


        /*
         * ViewPager适配器
         */
        MainPagerAdapter mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        vpMain.setAdapter(mMainPagerAdapter);
        //绑定适配器
        bblMain.setViewPager(vpMain);
        //默认选中首页
        bblMain.setCurrentItem(2);
    }


    /**
     * 监听返回按钮
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void showLoading(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void stopLoading() {

    }


    private class MainPagerAdapter extends FragmentPagerAdapter {

        MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }


}