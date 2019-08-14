package com.example.edz.myapplication.utils.networkutil;

import com.example.edz.myapplication.R;
import com.example.edz.myapplication.base.App;
import com.example.edz.myapplication.utils.NetWorkUtils;
import com.example.edz.myapplication.utils.StringUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 * @funtion 进行异常信息的处理
 */
public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private String mErrorMsg;


    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startLoading();
    }

    @Override
    public void onError(Throwable e) {
        //错误信息的处理
//        LogUtil.e("网络请求异常走了这里", e + "");
        // 网络不可用
        if (!NetWorkUtils.isNetConnected(App.getAppContext())) {
            onFail(App.getAppContext().getString(R.string.no_net));
        } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
//            onFail("请求超时,请稍后再试");
            onFail("");
        }
        // 服务器
        else if (e instanceof ServerException) {
            onFail(App.getAppContext().getString(R.string.service_error));
        } else if (e instanceof HttpException) {
            onFail("服务器异常，请稍后再试");

        } else if (!StringUtils.isEmpty(e.getMessage()) && e.getMessage().contains("错误你")) {
            String msg = e.getMessage().replace("错误你", "");
            if (StringUtils.isEmpty(msg)) {
                onFail("接口访问错误");
            } else {
                onFail(msg);
            }
        }
        // 其它
        else {
            onFail(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }


    protected abstract void onFail(String errorMsg);

    protected abstract void onSuccess(T response);

    protected abstract void startLoading();


}
