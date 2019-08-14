package com.example.edz.myapplication.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.edz.myapplication.R;
import com.example.edz.myapplication.utils.LogUtil;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;


/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
@SuppressLint("Registered")
public class App extends Application {
    public static Context AppContext;
    private static  App mContext;


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.notice_line, android.R.color.white);
            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            return new BezierCircleHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        mContext = this;
        //测试环境允许显示日志
        LogUtil.isShowing = false;

        //bugly 参数3  调试开关 /测试时true 发布时false
//        Bugly.init(getApplicationContext(), "3013f43ad7", false);
        //极光推送
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
        //内存泄漏检测
//        LeakCanary.install(this);

    }


    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }



}