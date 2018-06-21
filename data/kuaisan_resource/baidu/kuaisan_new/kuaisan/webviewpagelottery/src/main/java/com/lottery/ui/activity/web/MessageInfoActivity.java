package com.lottery.ui.activity.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.lottery.R;
import com.lottery.base.BaseActivity;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/11/30 17:32
 * @description:
 */
public class MessageInfoActivity extends BaseActivity{


//    @BindView(R.id.activity_all_web_view)
//    WebView webview;
    WebView webview;
    private String URL;

    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_all);
        progressShow();
        URL = getIntent().getStringExtra("url");
        if (null != getIntent().getStringExtra("title")) {
            title = getIntent().getStringExtra("title");
        }
        initToolbar(title, this, true);
        initView();
    }

    protected void initView() {
        findViewById(R.id.action_bar_left_iv).setOnClickListener(mOnClickListener);
        webview = (WebView)findViewById(R.id.activity_all_web_view);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        webview.loadUrl(URL);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        //滚动条
        webview.setHorizontalScrollBarEnabled(false);
        webview.setVerticalScrollBarEnabled(false);

        webview.setWebViewClient(client);
        webview.setWebChromeClient(chromeClient);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    }

    private WebViewClient client = new WebViewClient() {
        // 防止加载网页时调起系统浏览器
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressShow();
            view.loadUrl(url);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressCancel();
        }
    };

    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.action_bar_left_iv) {
                if (webview.canGoBack()) {
                    webview.goBack();
                }

            } else {
            }
        }
    };

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webview.stopLoading();
        webview.clearHistory();
        webview.destroy();
    }
}