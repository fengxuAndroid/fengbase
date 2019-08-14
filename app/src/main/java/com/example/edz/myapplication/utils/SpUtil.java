package com.example.edz.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

public class SpUtil {
    private WeakReference<Context> context = null;

    private static SharedPreferences prefs = null;

    private static SpUtil instance = null;

    public static SpUtil init(Context context) {
        if (instance == null || prefs == null) {
            synchronized (SpUtil.class) {
                if (instance == null || prefs == null) {
                    instance = new SpUtil(context);
                }
            }
        }

        return instance;
    }

    private SpUtil(Context context) {
        this.context = new WeakReference<>(context);
        prefs = this.context.get().getSharedPreferences("huayuan.sp", Context.MODE_PRIVATE);
    }

    public void commit(String key, String value) {
        prefs.edit().putString(key, value).commit();
    }

    public void commit(String key, int value) {
        prefs.edit().putInt(key, value).commit();
    }

    public void commit(String key, boolean value) {
        prefs.edit().putBoolean(key, value).commit();
    }

    public String readString(String key) {
        return prefs.getString(key, "");
    }

    public Integer readInt(String key) {
        return prefs.getInt(key, 0);
    }

    public Boolean readBoolean(String key, boolean def) {
        return prefs.getBoolean(key, def);
    }

    /**
     * <p/>
     * 注意该方法会清除所有保存过的SharedPreferences
     * <li>登出时调用
     */
    public void clearAll() {
        prefs.edit().clear().apply();
    }

}
