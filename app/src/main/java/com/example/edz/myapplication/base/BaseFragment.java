package com.example.edz.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.edz.myapplication.utils.LoadingDialogUtil;
import com.example.edz.myapplication.utils.StringUtils;
import com.example.edz.myapplication.widgets.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基础类
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    //得到类名
    protected final String TAG = this.getClass().getName();
    protected P mPresenter;
    /**
     * 根视图
     */
    protected View mRootView;
    /**
     * 全APP 进程
     */
    protected App mApp;
    /**
     * 上下文对象
     */
    protected Context mContext;
    /**
     * 吐司
     */
    protected Toast mToast;
    /**
     * 注解
     */
    private Unbinder unbinder;
    /**
     * 弹窗
     */
    private LoadingDialogUtil mLoadingDialogUtil;

    /**
     * fragment不可见
     */
    private boolean isViewVisiable = false;

    /**
     * 是否准备
     */
    private boolean isPrepared = false;

    /**
     * 是否加载数据
     */
    private boolean isDataAdd = false;

    //********************************************* 生命周期

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attach(context);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        attach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getViewResId(), container, false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        unbinder = ButterKnife.bind(this, mRootView);
        isPrepared = true;
        if (isViewVisiable && !isDataAdd) {
            load();
        }
        return mRootView;
    }

    /**
     * 初始化数据
     */
    private void load() {
        mPresenter = getPresenter();
        mLoadingDialogUtil = new LoadingDialogUtil();
        initView();
        isDataAdd = true;

    }

    /**
     * 只在可见时加载数据
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isViewVisiable = isVisibleToUser;
        if (isViewVisiable && isPrepared && !isDataAdd) {
            load();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind(); //注解框架解绑
        }
        //取消 view 的绑定
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        isPrepared = false;
        isViewVisiable = false;
        isDataAdd = false;
    }

    private void attach(Context context) {
        mContext = context;
        mApp = (App) context.getApplicationContext();

    }


    //********************************************* 实用方法

    /**
     * window背景是否变暗
     *
     * @param isDark
     */
    public void setDarkWindow(boolean isDark) {
        //尝试 动态添加一个半透明的view 通过显示隐藏的方式来产生背景变暗效果
        if (isDark) {
            ((BaseActivity) getActivity()).setDarkWindow(true);
        } else {
            ((BaseActivity) getActivity()).setDarkWindow(false);
        }
    }

//    DynamicBox box;
//    protected DynamicBox createDynamicBox(View view){
//        if (box==null) {
//            box = new DynamicBox(getActivity(), view);
//            View customView = getLayoutInflater().inflate(R.layout.include_no_data, null, false);
//            box.addCustomView(customView, "noNewWork");
//        }
//        box.showCustomView("noNewWork");
//        return box;
//    }


//    protected void hideBox() {
//        if (box != null) {
//            box.hideAll();
//        }
//    }


    public void showToast(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void showToastLong(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        }
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    /**
     * 开启加载进度条
     */
    public void startProgressDialog() {
        startProgressDialog(Constant.TOAST_LODING);
    }

    /**
     * 开启加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        mLoadingDialogUtil.showDialogForLoading(getActivity(), msg, true);
    }

    /**
     * 停止加载进度条
     */
    public void stopProgressDialog() {
        mLoadingDialogUtil.cancelDialogForLoading();
    }

    /**
     * 关闭刷新
     */
    protected void closeRefresh(SmartRefreshLayout view) {
        view.finishRefresh(300);
        view.finishLoadMore(300);
    }


    //********************************************* 子类实现

    //布局ID
    protected abstract int getViewResId();


    protected abstract P getPresenter();

    //初始化
    protected abstract void initView();



}