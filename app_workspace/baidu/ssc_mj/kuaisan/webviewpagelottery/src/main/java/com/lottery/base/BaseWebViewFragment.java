package com.lottery.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lottery.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: LiuJinrui
 * @email: liujinrui@qdcftx.com
 * @time: 2017/12/10 15:18
 * @description:
 */
public class BaseWebViewFragment extends BaseFragment {
    private View rootView = null;

//
//    @BindView(R.id.fragment_nationwide_web_view)
//    WebView webView;
    WebView webView;

    private String javascript;

    private String url;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        System.out.println("lxm BaseWebViewFragment onHiddenChanged:" +hidden );
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        System.out.println("lxm BaseWebViewFragment setUserVisibleHint:" +isVisibleToUser );
        if (isVisibleToUser) {
            refreshWebView();
        }
    }

    boolean is_load ;

    private void refreshWebView() {
        if (webView != null) {
            progressShow();
            webView.loadUrl(url);
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        url = getArguments().getString("fragment_url");
        javascript=getArguments().getString("javascript");
        javascript=getArguments().getString("javascript");
        is_load=getArguments().getBoolean("is_load");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_nationwide, container, false);
//        ButterKnife.bind(this, rootView);
//        progressShow();
        BaseFragmentInitView();
        return rootView;
    }

    public void BaseFragmentInitView() {
        webView = (WebView) rootView.findViewById(R.id.fragment_nationwide_web_view);
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染的优先级
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true); //设置H5的缓存打开,默认关闭

        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        webView.setWebViewClient(client);
        webView.setWebChromeClient(chromeClient);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        if (is_load) {
            refreshWebView();
        }
    }

    private WebViewClient client = new WebViewClient() {
        // 防止加载网页时调起系统浏览器
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.loadUrl(javascript);
            view.loadUrl("javascript:hideOther();");
            getWebView().setVisibility(View.VISIBLE);
            progressCancel();

        }
    };


    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
        }
    };

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }
}


