package cn.mandroid.wechatrobot.utils;

import android.util.Log;

import cn.mandroid.wechatrobot.BuildConfig;

/**
 * Created by wrBug on 2017/1/1.
 */

public class MLog {
    private static final String TAG = "MLog";

    public static void i(Object o) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, String.valueOf(o));
        }
    }

    public static void w(Object o) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, String.valueOf(o));
        }
    }

    public static void e(Object o) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, String.valueOf(o));
        }
    }

    public static void e(Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public static void d(Object o) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, String.valueOf(o));
        }
    }
}
