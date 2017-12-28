package com.lxm.ss.kuaisan.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.lottery.adapter.FragmentAdapter;
import com.lottery.ui.fragment.SsczstFragment;
import com.lxm.ss.kuaisan.BuildConfig;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.Utils.UriUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseFragment;
import com.lxm.ss.kuaisan.ui.betting.BettingTypeListActivity;
import com.lxm.ss.kuaisan.ui.main.DetailWebViewActivity;
import com.lxm.ss.kuaisan.ui.setting.SettingActivity;
import com.lxm.ss.kuaisan.ui.style.MoreStyleActivity;
import com.lxm.ss.kuaisan.ui.trailer_infor.information.InformationListActivity;
import com.lxm.ss.kuaisan.ui.trailer_infor.pre.TrailerListActivity;
import com.lxm.ss.kuaisan.web.FFWebChromeClient;
import com.lxm.ss.kuaisan.web.FFWebViewClient;
import com.lxm.ss.kuaisan.web.FFWebview;
import com.lxm.ss.kuaisan.widget.AutoRoll;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

import club.fromfactory.baselibrary.utils.ScreenUtils;

/**
 * Created by lxm on 2017/11/22.
 */

public class HomeFragmentFour extends BaseFragment {


    private CustomTitleLinearlayout mCtlTitle ;
    private AutoRoll arl_arl ;

    private LinearLayout mLyWebView ;
    private FFWebview mWebView ;

    private String mCurrentUrl = "http://112.74.102.204:86/m/yu.html";
    String javascript = "javascript:function hideOther() {"
            + "if(null!= document.getElementsByClassName('nav')) {document.getElementsByClassName('nav')[0].style.display = 'none';}" +
            "}";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            refreshWebview();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshWebview();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vv = inflater.inflate(R.layout.fragment_home_four, container, false);
        initView(vv);
        return vv;
    }

    private void initView(View vv) {
        mCtlTitle = (CustomTitleLinearlayout) vv.findViewById(R.id.ctl_title);


        mLyWebView = (LinearLayout) vv.findViewById(R.id.fragment_home_two_ly_webview);
        mWebView = new FFWebview(getActivity());
        mLyWebView.addView(mWebView);
        mWebView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        initWebView();
    }
    private void initWebView() {

        mWebView.setJsInterface(getActivity(), null);
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

        mCtlTitle.settingRight();
        mCtlTitle.setTitleCenter(BuildConfig.MAIN_TITLE);
        mCtlTitle.setRightVisible(false);
        mCtlTitle.setLeftTextVisible(false);
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {

            @Override
            public void clickRight() {
                SettingActivity.launchActivity(getActivity());
            }
        });

    }



    private FFWebViewClient webViewClient = new FFWebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Zlog.ii("lxm ss webview shouldOverrideUrlLoading :" + url + "  ");
            return false ;
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

            showBaseProgressDialog();
        }

        @Override
        public void onReceivedError(WebView view, int errorCod, String description, String failingUrl) {
            super.onReceivedError(view, errorCod, description, failingUrl);
            Zlog.ii("lxm ss webview onReceivedError:");
//            String data = "";
//            view.loadUrl("javascript:document.body.innerHTML=\"" + data + "\"");
//            view.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            view.loadUrl(NetUtils.NETWORK_ERROR_PAGE_URL);
            ToastUtils.show(getActivity(), "error code =" + errorCod);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (request != null && request.getUrl().toString().equals(mCurrentUrl)) {
                    ToastUtils.show(getActivity(), "error code =" + errorResponse.getStatusCode());
                }
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.loadUrl(javascript);
            view.loadUrl("javascript:hideOther();");
            mLyWebView.setVisibility(View.VISIBLE);

            hideBaseProgressDialog();
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
                webViewClient.sendStatInfo(getActivity(), view.getUrl());
            } else {
                webViewClient.showNetToast(getActivity());
            }
        }
    };

    private void refreshWebview() {
        mWebView.stopLoading();
        mWebView.loadUrl(mCurrentUrl);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
