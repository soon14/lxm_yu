package com.lxm.ss.kuaisan.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.lxm.ss.kuaisan.BuildConfig;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseFragment;
import com.lxm.ss.kuaisan.ui.main.DetailWebViewActivity;
import com.lxm.ss.kuaisan.ui.setting.SettingActivity;
import com.lxm.ss.kuaisan.web.FFWebChromeClient;
import com.lxm.ss.kuaisan.web.FFWebViewClient;
import com.lxm.ss.kuaisan.web.FFWebview;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxm on 2017/11/22.
 */

public class RecommendedBallHomeFragment extends BaseFragment {


    private CustomTitleLinearlayout mCtlTitle ;

    private LinearLayout mLyWebView ;
    private FFWebview mWebView ;
//
//    private String mCurrentUrl = "http://112.74.102.204:86/m/zst.html";
//    String javascript = "javascript:function hideOther() {"
//            + "if(null!= document.getElementsByClassName('nav')) {document.getElementsByClassName('nav')[0].style.display = 'none';}" +
//            "}";
//    private String mTitle = "赛事资料" ;
//    private String mCurrentUrl = "http://m.jc258.cn/europe";
    private String mTitle = "专家推荐" ;
    private String mCurrentUrl = "http://m.jc258.cn/data/recommendlist";  //

    String jsbase = "javascript:hideOther();" ;
//    String javascript = "javascript:function hideOther() {"
//            + "if(null!= document.getElementById('gobuy')) {document.getElementById('gobuy')[0].style.display = 'none';}" +
//            "}";
    String javascript = "javascript:function hideOther() {"
            + "if(null!= document.getElementsByClassName('navsb botnav')) {document.getElementsByClassName('navsb botnav')[0].style.display = 'none';}" +
            "}";
    String javascript1 = "javascript:function hideOther() {"
            + "if(null!= document.getElementsByClassName('bt_menu')) {document.getElementsByClassName('bt_menu')[0].style.display = 'none';}" +
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
        View vv = inflater.inflate(R.layout.fragment_home_lotter, container, false);
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
        mCtlTitle.setRightVisible(true);
        mCtlTitle.setTitleCenter(mTitle);
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

            List<String> stringList = new ArrayList<>();
            stringList.add(javascript);
            stringList.add(jsbase);
            stringList.add(javascript1);
            stringList.add(jsbase);
            DetailWebViewActivity.launchActivity(getActivity(),url,stringList);
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
            mLyWebView.setVisibility(View.GONE);
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
