package com.lxm.ss.kuaisan.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.Utils.UriUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.web.FFWebChromeClient;
import com.lxm.ss.kuaisan.web.FFWebViewClient;
import com.lxm.ss.kuaisan.web.FFWebview;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

public class DetailWebViewActivity extends BaseActivity {

    private CustomTitleLinearlayout mCtlTitle;
    private LinearLayout mLyWebview;
    private FFWebview mWebView ;

    private String mCurrentUrl;

    /**
     * 启动详情页面
     *
     * @param url 需要打开页面的url。
     */
    public static void launchActivity(Context context, String url) {
        if (url == null || url.length() == 0) {
            return;
        }
        Intent intent = new Intent(context, DetailWebViewActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_web_view);
        // WebView要加载的URL,保存下来是为了断网出错时,刷新时加载原来的页面.
        Intent intent = getIntent();
        mCurrentUrl = intent.getStringExtra(Constants.INTENT_URL);
        initView();

        initData();
    }

    private void initView() {

        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);


        mLyWebview = (LinearLayout) findViewById(R.id.splash_webview_ly_webview);

        mWebView = new FFWebview(DetailWebViewActivity.this);
        mLyWebview.addView(mWebView);
        mWebView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        initWebView();
    }
    private void initWebView() {
        mWebView.setJsInterface(this, null);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebViewClient(webViewClient);
        // 设置可现实js的alert弹窗
        mWebView.setWebChromeClient(webChromeClient);

        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        refreshWebview();
    }

    private void initData() {

        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                goback();
            }
        });
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                default:
                    break;
            }
        }
    };

    private FFWebViewClient webViewClient = new FFWebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Zlog.ii("lxm ss webview shouldOverrideUrlLoading :" + url + "  ");
            mCurrentUrl  = url ;
            if (UriUtils.getInstance().isHtmlUrl(url)){
                return false ;
            }else {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.show(DetailWebViewActivity.this,"请先安装应用");
                }
                return true;
            }
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            int i = url.indexOf(".jpg");
            Zlog.ii("lxm ss webview onLoadResource:" + i);
            if (url.indexOf(".jpg") > 0) {
            }
        }

        @Override
        public void onPageStarted(WebView view, final String url, Bitmap favicon) {
            Zlog.ii("lxm ss webview onPageStarted:" + url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCod, String description, String failingUrl) {
            super.onReceivedError(view, errorCod, description, failingUrl);
            Zlog.ii("lxm ss webview onReceivedError:");
//            String data = "";
//            view.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
//            view.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            view.loadUrl(NetUtils.NETWORK_ERROR_PAGE_URL);
            ToastUtils.show(DetailWebViewActivity.this, "error code =" + errorCod);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (request != null && request.getUrl().toString().equals(mCurrentUrl)) {
                    ToastUtils.show(DetailWebViewActivity.this, "error code =" + errorResponse.getStatusCode());
                }
            }
        }
    };

    private WebChromeClient webChromeClient = new FFWebChromeClient() {
        public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Zlog.ii("lxm ss webview onProgressChanged 1:" + newProgress);
            if (newProgress >= 75) {
                Zlog.ii("lxm ss webview onProgressChanged 2:" + newProgress + view.getUrl());
                mLyWebview.setVisibility(View.VISIBLE);
                webViewClient.sendStatInfo(getApplicationContext(), view.getUrl());
            } else {
                webViewClient.showNetToast(getApplicationContext());
            }
        }
    };

    private void refreshWebview() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.stopLoading();
                mWebView.loadUrl(mCurrentUrl);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 当keyCode等于退出事件值时
            goback();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void goback() {

        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
        }else {
            finish();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Zlog.ii("lxm ss detailpageactivity onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Zlog.ii("lxm ss detailpageactivity onStop");
    }

    @Override
    public void finish() {
        super.finish();

        try {
            mLyWebview.removeAllViews();
            if (mWebView != null) {
                mWebView.destroy();
                mWebView = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Zlog.ii("lxm ss detailpageactivity onDestroy");
    }
}
