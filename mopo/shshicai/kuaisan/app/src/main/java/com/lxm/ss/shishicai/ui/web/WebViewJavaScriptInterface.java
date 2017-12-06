package com.lxm.ss.shishicai.ui.web;

import android.app.Activity;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;


/**
 * Created by lxm ss on 2016/11/10.
 * 处理js调用的类
 */

public class WebViewJavaScriptInterface {



    /*
            * This method can be called from Android. @JavascriptInterface
            * required after SDK version 17.
            */
    private Activity activity;
    private Handler mHandler = null;
    private WebView myWebView = null;

    public WebViewJavaScriptInterface(Activity activiy, WebView myWebView, Handler mHandler) {
        this.activity = activiy;
        this.myWebView = myWebView;
        this.mHandler = mHandler;
    }

    @JavascriptInterface
    /**
     *
     */
    public void urlToAll() {

    }

}
