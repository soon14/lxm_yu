package com.lxm.ss.ssc.web;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lxm.ss.ssc.Utils.Zlog;
import com.lxm.ss.ssc.http.NetUtils;
import com.lxm.ss.ssc.ui.web.WebViewJavaScriptInterface;

/**
 * webview 基础类
 *
 * @author zhoulei
 */
public class FFWebview extends WebView {


    private OnScrollListener mOnScrollListener;

    public FFWebview(Context context) {
        super(context);
        initial(context);
    }

    public FFWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial(context);
    }

    public FFWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initial(context);
    }

    private void initial(Context context) {
        WebSettings settings = getSettings();
        settings.setDefaultTextEncodingName("UTF-8");

        settings.setJavaScriptEnabled(true);//启用js

        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染的优先级
        // 判断是否有网络，有的话，使用LOAD_DEFAULT，无网络时，使用LOAD_CACHE_ELSE_NETWORK
        if (NetUtils.checkNetworkAvailable(context)) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        settings.setJavaScriptCanOpenWindowsAutomatically(true);//js和android交互
        settings.setAllowFileAccess(true); // 允许访问文件

        settings.setUseWideViewPort(true);//设置webview自适应屏幕大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//设置，可能的话使所有列的宽度不超过屏幕宽度
        settings.setLoadWithOverviewMode(true);//设置webview自适应屏幕大小
        settings.setSupportZoom(false);//关闭zoom按钮
        settings.setBuiltInZoomControls(false);//关闭zoom
        settings.setAppCacheMaxSize(1024 * 1024 * 8);
        // WebView是否保存表单数据，默认true，保存数据。
        settings.setSaveFormData(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        settings.setDomStorageEnabled(true);//设置可以使用localStorage
        settings.setAppCacheEnabled(true); //设置H5的缓存打开,默认关闭
        // 启用数据库
        settings.setDatabaseEnabled(true);

        //        Mozilla/5.0 (Linux; Android 6.0.1; SM-G9008V Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.98 Mobile Safari/537.36
        //设置WebView代理字符串，如果String为null或为空，将使用系统默认值
        settings.setUserAgentString(settings.getUserAgentString() + " android_webview android_native_navbar version2");

        if (Zlog.isDebug) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
        }

    }
    public void setSupportMultipleWindows(boolean isSupportMultipleWindows) {
        WebSettings settings = getSettings();
        if (isSupportMultipleWindows) {
            settings.setSupportMultipleWindows(true);
        }
    }

    /**
     * 设置js调用java代码接口
     */
    public void setJsInterface(Activity activity, Handler handler) {
        if (activity != null) {
            WebViewJavaScriptInterface webViewJavaScriptInterface = new WebViewJavaScriptInterface(activity, this, handler);
            addJavascriptInterface(webViewJavaScriptInterface, "JSInterface");
        }
    }

    /**
     * 调用js方法
     *
     * @param jsCode 需要调用的js方法
     */
    public void executeJsCode(String jsCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            evaluateJavascript(jsCode, null);
        } else {
            loadUrl("javascript:" + jsCode);
        }
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(t);
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        mOnScrollListener = listener;
    }

    public interface OnScrollListener {
        void onScroll(int top);
    }

}
