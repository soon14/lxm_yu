package com.lxm.ss.kuaisan;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lxm on 2017/11/23.
 */

public class FFApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
}
