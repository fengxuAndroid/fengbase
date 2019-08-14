package com.example.edz.myapplication.utils.networkutil;


import android.util.Log;

import com.example.edz.myapplication.home.bean.ResponseData;


import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 * @function 线程转换辅助类
 */
public class RxSchedulerHepler {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    /**
//     * 统一返回结果处理
//     *
//     * @param <T>
//     * @return
//     */
//    public static <T> FlowableTransformer<ResponseData<T>, T> handleMyResult() {   //compose判断结果
//        return httpResponseFlowable -> httpResponseFlowable.flatMap((Function<ResponseData<T>, Flowable<T>>) response -> {
//
//
//            Log.e("111111111111",response.toString());
////            if ("1".equals(response.getStatus().getCode())||"0".equals(response.getStatus().getCode())) {
//                return createData(response.getData());
////            } else {
////                return Flowable.error(new Exception(
////                         ( response.getStatus().getMsg())
////                ));
////            }
//        }).subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<ResponseData<T>, T> handleMyResult() {   //compose判断结果
        return httpResponseFlowable -> httpResponseFlowable.flatMap((Function<ResponseData<T>, Flowable<T>>) response -> {
            if ("0".equals(response.getCode())) {
                return createData(response.getData());
            } else {
                return Flowable.error(new Exception(
                        response.getMsg()
                ));
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 存储数据
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }, BackpressureStrategy.BUFFER);
    }


}
