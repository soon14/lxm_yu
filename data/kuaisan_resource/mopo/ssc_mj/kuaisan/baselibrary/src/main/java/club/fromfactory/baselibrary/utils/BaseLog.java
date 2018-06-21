package club.fromfactory.baselibrary.utils;

import android.util.Log;

public class BaseLog {
    public static boolean isDebug = true;
    private static final String TAG = "lxm";

    //
    public static void i(String msg) {
        if (isDebug && msg != null)
            Log.i(TAG, "lxm:" + msg);
    }

    public static void d(String msg) {
        if (isDebug && msg != null)
            Log.d(TAG, "lxm:" + msg);
    }

    public static void e(String msg) {
        if (isDebug && msg != null)
            Log.e(TAG, "lxm:" + msg);
    }

    public static void v(String msg) {
        if (isDebug && msg != null)
            Log.v(TAG, msg);
    }

    //
    //*
    public static void i(Class<?> _class, String msg) {
        if (isDebug && msg != null)
            Log.i(_class.getName(), "lxm:" + msg);
    }

    //*/
    public static void d(Class<?> _class, String msg) {
        if (isDebug && msg != null)
            Log.i(_class.getName(), "lxm:" + msg);
    }

    public static void e(Class<?> _class, String msg) {
        if (isDebug && msg != null)
            Log.i(_class.getName(), "lxm:" + msg);
    }

    public static void v(Class<?> _class, String msg) {
        if (isDebug && msg != null)
            Log.i(_class.getName(), "lxm:" + msg);
    }

    //
    public static void i(String tag, String msg) {
        if (isDebug && msg != null)
            Log.i(tag, "lxm:" + msg);
    }

    public static void ii(String msg) {
        if (isDebug && msg != null)
            Log.i(TAG, "lxm:" + msg);
    }

    public static void iPaytm(String msg) {
        if (isDebug && msg != null)
            Log.i(TAG, "lxm:iPaytm " + msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug && msg != null)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug && msg != null)
            Log.e(tag, "lxm:" + msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug && msg != null)
            Log.v(tag, "lxm:" + msg);
    }
}
