package com.example.edz.myapplication.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.edz.myapplication.R;
import com.example.edz.myapplication.utils.ActivityManager;
import com.example.edz.myapplication.utils.LoadingDialogUtil;
import com.example.edz.myapplication.utils.StatusBarUtil;
import com.example.edz.myapplication.utils.StringUtils;
import com.example.edz.myapplication.widgets.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import org.greenrobot.eventbus.EventBus;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */

public abstract class BaseActivity<P extends BasePresenter> extends SwipeBackActivity implements
        IBaseView {
    /**
     * Presenter
     */
    protected P mPresenter;
    /**
     * 上下文对象
     */
    protected Context mContext;

    /**
     * 提示
     */
    protected Toast mTasot;
    /**
     * 注解框架绑定管理
     */
    private Unbinder unbinder;


    /**
     * 右滑退出
     */
    private SwipeBackLayout mSwipeBackLayout;
    /**
     * 退出app的时间
     */
    private long mExitTime;

    /**
     * 弹窗处理
     */
    private LoadingDialogUtil mLoadingDialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置标题栏
        StatusBarUtil.setColorNoTranslucent(this, Color.TRANSPARENT);
        mContext = this;
        setContentView(getResViewId());
        //注解框架的实例化
        unbinder = ButterKnife.bind(this);
        //获取presenter
        mPresenter = getPresenter();
        //注册EventBus
//        EventBus.getDefault().register(this);
        //添加activity
        ActivityManager.getAppManager().addActivity(this);
        //设置右滑退出
        initSwipeBack();
        mLoadingDialogUtil = new LoadingDialogUtil();
        //初始化数据
        initView(savedInstanceState);
    }


    //*************************************   activity 相关配置/跳转/关闭

    /**
     * 初始化右滑退出
     */
    private void initSwipeBack() {
        // 可以调用该方法，设置是否允许滑动退出
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        // mSwipeBackLayout.setEdgeSize(200);

    }

    /**
     * 关闭右滑退出
     */

    protected void closeSwipeBack() {
        setSwipeBackEnable(false);
    }

    /**
     * 跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 含有Bundle跳转界面回调
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    //****************************************************** 实用方法

    private View mDialogView;

    /**
     * window背景是否变暗
     *
     * @param isDark
     */
    public void setDarkWindow(boolean isDark) {

        if (mDialogView == null) {
            mDialogView = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            mDialogView.setLayoutParams(layoutParams);
            mDialogView.setVisibility(View.VISIBLE);
            mDialogView.setBackgroundColor(getResources().getColor(R.color.black));
            mDialogView.setAlpha(0.6f);
            FrameLayout rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            rootView.addView(mDialogView);
        }
        //添加一个半透明的view 通过显示隐藏的方式来产生背景变暗效果
        if (isDark) {
            mDialogView.setVisibility(View.VISIBLE);
        } else {
            mDialogView.setVisibility(View.GONE);
        }
    }


//    private DynamicBox box;
//
//    protected DynamicBox createDynamicBox(View view) {
//        if (box == null) {
//            box = new DynamicBox(this, view);
//            View customView = getLayoutInflater().inflate(R.layout.include_no_data, null, false);
//            box.addCustomView(customView, "noNewWork");
//        }
//        box.showCustomView("noNewWork");
//        return box;
//    }


//    protected void hideDynamicBox() {
//        if (box != null) {
//            box.hideAll();
//        }
//    }


    //退出app


    protected void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            showToast("再按一次退出DEMO");
            mExitTime = System.currentTimeMillis();
        } else {
            //用户退出处理
            finish();
            System.exit(0);
        }
    }

    /**
     * 关闭刷新
     */
    protected void closeRefresh(SmartRefreshLayout view) {
        view.finishRefresh(300);
        view.finishLoadMore(300);
    }

    /**
     * 收回软键盘
     */

    protected void disMissSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    //提示
    public void showToast(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return;
        }
        if (mTasot == null) {
            mTasot = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        }
        mTasot.setText(msg);
        mTasot.setDuration(Toast.LENGTH_SHORT);
        mTasot.show();
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
        mLoadingDialogUtil.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止加载进度条
     */
    public void stopProgressDialog() {
        mLoadingDialogUtil.cancelDialogForLoading();
    }

    //*************************************************************  子类实现

    //布局文件
    protected abstract int getResViewId();

    //获得presenter
    protected abstract P getPresenter();

    //初始化
    protected abstract void initView(Bundle savedInstanceState);


    //*******************************************  生命周期

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销EventBus
        EventBus.getDefault().unregister(this);
        //解除注解
        if (unbinder != null) {
            unbinder.unbind();
        }
        //取消 view 的绑定
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        ActivityManager.getAppManager().finishActivity(this);

    }

}
