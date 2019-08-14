package com.example.edz.myapplication.base;

import android.os.Environment;
import android.util.Log;

import com.example.edz.myapplication.utils.LogUtil;
import com.example.edz.myapplication.utils.NetWorkUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 * 网络请求工具类
 */
public class Api {
    //单例
    private static Api instance = null;
    private Retrofit retrofit;
    public ApiService service;
    /**
     * 缓存路径
     */
    private String CACHE_ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/Android/huayuan/";
    /**
     * 缓存路径
     */
    private String HTTP_CACHE_PATH = CACHE_ROOT_PATH + "cache/";


    // 构造方法私有
    private Api() {
        //设置Gson日期的转换格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder().baseUrl(ApiService.BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                //设置Json 转换器
                .addConverterFactory(GsonConverterFactory.create(gson))
                //RxJava 适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient()).build();

        service = retrofit.create(ApiService.class);
    }

    /**
     * @return APi
     */
    public static Api getInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) {
                    instance = new Api();
                }
            }
        }
        return instance;
    }


    /**
     * 拦截器
     */
    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetConnected(App.getAppContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                LogUtil.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(App.getAppContext())) {
                // 有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200").removeHeader("Pragma")
                        .build();
            }
        }
    }

    /**
     * 配置网络
     *
     * @return okHttpClient
     */
    private OkHttpClient getOkHttpClient() {

        // 增加头部信息
        Interceptor headerInterceptor = chain -> {
            Request build = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
            return chain.proceed(build);
        };
        //打印 网络请求日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
            //打印retrofit日志
            Log.i("RetrofitLog", "retrofitBack = " + message);
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓冲器，缓存
        File cacheFile = new File(HTTP_CACHE_PATH);
        //Cache就是指缓存SRAM。 SRAM叫静态内存,“静态”指的是当我们将一笔数据写入SRAM后,除非重新写入新数据或关闭电源,否则写入的数据保持不变
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 100Mb


        OkHttpClient.Builder build = new OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)//设置了超时时间
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingInterceptor)//打印retrofit日志
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .retryOnConnectionFailure(true);//出现错误则重新连接

        return build.build();

    }

}