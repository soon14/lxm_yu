package com.lxm.ss.kuaisan.http;

/**
 * Created by lxm on 2017/6/21.
 */

public abstract class OkHttpRequestListener {

   public void onSucceed(Object o){
    };

    public void onNoLoginStatus(String message){
    };
    public void onFailed(int code ,String body , String message){};//新增，兼容以前的 2017/11/1

}
