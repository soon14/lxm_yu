package com.lxm.ss.ssc.Utils;

import android.util.Log;

import com.lxm.ss.ssc.BuildConfig;


public class Zlog {
//    public static boolean isDebug = true;
    public static boolean isDebug = BuildConfig.IS_DEBUG;
    private static final String TAG = "club_factory";

    //
    public static void i(String msg) {
        if (isDebug && msg != null)
            Log.i(TAG, "club_factory:" + msg);
    }

    public static void d(String msg) {
        if (isDebug && msg != null)
            Log.d(TAG, "club_factory:" + msg);
    }

    public static void e(String msg) {
        if (isDebug && msg != null)
            Log.e(TAG, "club_factory:" + msg);
    }

    public static void v(String msg) {
        if (isDebug && msg != null)
            Log.v(TAG, msg);
    }

    //
    //*
    public static void i(Class<?> _class, String msg) {
        if (isDebug && msg != null)
            Log.i(_class.getName(), "club_factory:" + msg);
    }

    //*/
    public static void d(Class<?> _class, String msg) {
        if (isDebug && msg != null)
            Log.i(_class.getName(), "club_factory:" + msg);
    }

    public static void e(Class<?> _class, String msg) {
        if (isDebug && msg != null)
            Log.i(_class.getName(), "club_factory:" + msg);
    }

    public static void v(Class<?> _class, String msg) {
        if (isDebug && msg != null)
            Log.i(_class.getName(), "club_factory:" + msg);
    }

    //
    public static void i(String tag, String msg) {
        if (isDebug && msg != null)
            Log.i(tag, "club_factory:" + msg);
    }

    public static void ii(String msg) {
        if (isDebug && msg != null)
            Log.i(TAG, "club_factory:" + msg);
    }

    public static void iPaytm(String msg) {
        if (isDebug && msg != null)
            Log.i(TAG, "club_factory:iPaytm " + msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug && msg != null)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug && msg != null)
            Log.e(tag, "club_factory:" + msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug && msg != null)
            Log.v(tag, "club_factory:" + msg);
    }
}
