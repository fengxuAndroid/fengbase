package com.example.edz.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * presenter基础类
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public abstract class BasePresenter<M extends BaseModel, V extends IBaseView> {

    /**
     * 管理Rx订阅
     */
    protected CompositeDisposable mDisposable;
    protected M mModel;
    protected V mView;
    protected Context mContext;

    //初始化 view 和 model
    protected void initPresenter(V view) {
        this.mView = view;
        this.mModel = createModel();
        initContext(view);

    }

    /**
     * 解除订阅
     */
    protected void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable.dispose();
        }

    }

    /**
     * 添加订阅
     */
    protected void addSubscribe(Disposable subscription) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(subscription);
    }


    /**
     * 获得上下文对象
     */
    private void initContext(V view) {
        if (view instanceof Activity) {
            //Activity
            mContext = (Activity) view;
        } else {
            mContext = ((Fragment) view).getActivity();
        }

    }

    public void onDestroy() {
        if (mView != null) {
            mView = null;
        }
        //解除订阅
        unSubscribe();
    }


    protected abstract M createModel();

}
