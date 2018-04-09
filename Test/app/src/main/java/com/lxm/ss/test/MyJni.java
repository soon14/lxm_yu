package com.lxm.ss.test;

/**
 * Created by lxm on 2017/8/28.
 */

public class MyJni {

    static {
        System.loadLibrary("JniTest");
    }
    public static native String sayHello();
}
