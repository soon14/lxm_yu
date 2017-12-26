package com.lxm.ss.kuaisan.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.lxm.ss.kuaisan.ui.more.CommenProblemsActivity;
import com.lxm.ss.kuaisan.ui.setting.SettingActivity;
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

public class LottertHomeFragment extends BaseFragment {


    private CustomTitleLinearlayout mCtlTitle ;
    private View propagandaLink ;

    private AutoRoll arl_arl ;

    private LinearLayout mLyWebView ;
    private FFWebview mWebView ;
//
//    private String mCurrentUrl = "http://112.74.102.204:86/m/zst.html";
//    String javascript = "javascript:function hideOther() {"
//            + "if(null!= document.getElementsByClassName('nav')) {document.getElementsByClassName('nav')[0].style.display = 'none';}" +
//            "}";
    private String mCurrentUrl = "http://5.9188.com/yuce/";
    String javascript = "javascript:function hideOther() {"
            + "if(null!= document.getElementsByClassName('tzHeader')) {document.getElementsByClassName('tzHeader')[0].style.display = 'none';}" +
            "}";
    String javascript1 = "javascript:function hideOther() {"
            + "if(null!= document.getElementsByClassName('fixed kjFloat')) {document.getElementsByClassName('fixed kjFloat')[0].style.display = 'none';}" +
            "}";
    String javascript2 = "javascript:function hideOther() {"
            + "if(null!= document.getElementsByClassName('ops-btn')) {document.getElementsByClassName('ops-btn')[0].style.display = 'none';}" +
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
        View vv = inflater.inflate(R.layout.fragment_home_three, container, false);
        initView(vv);
        return vv;
    }

    private void initView(View vv) {
        mCtlTitle = (CustomTitleLinearlayout) vv.findViewById(R.id.ctl_title);
        // 轮播图
        propagandaLink = vv.findViewById(R.id.fl_wjsqpropaganda_link);
        arl_arl = (AutoRoll) vv.findViewById(R.id.fragment_auto_roll_pic);
        setImageParams(arl_arl);

        vv.findViewById(R.id.ly01).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly02).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly03).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly04).setOnClickListener(mOnClickListener);

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
        mCtlTitle.setLeftTextVisible(false);
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {

            @Override
            public void clickRight() {
                SettingActivity.launchActivity(getActivity());
            }
        });


        initializeViewPage();



    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.ly01:
//                    DetailContentActivity.launchActivity(getActivity(),getResources().getString(R.string.kuaosan_rules));
//                    enterIntoActivity("http://caipiao.163.com/help/12/1108/15/8FQ3IUNH00754IHE.html");//快三
                    enterIntoActivity("http://caipiao.163.com/help/14/0702/11/A056UQ5K00754KN4.html");//北京赛车
//                    enterIntoActivity("http://caipiao.163.com/help/10/0811/14/6DQGMQC400754IIO.html");
//                    IntoActivity.launchActivity(getActivity(),"http://caipiao.163.com/help/12/1108/15/8FQ3IUNH00754IHE.html");

//                    MoreStyleActivity.launchActivity(getActivity());
                    break;
                case R.id.ly02:
                    TrailerListActivity.launchActivity(getActivity());
                    break;
                case R.id.ly03:

                    CommenProblemsActivity.launchActivity(getActivity());

//                    BettingTypeListActivity.launchActivity(getActivity());
//                    BettingAnalysisListActivity.launchActivity(getActivity());
//                    NewLotteryActivity.launchActivity(getActivity());
                    break;
                case R.id.ly04:
                    InformationListActivity.launchActivity(getActivity());
//                    ToastUtils.show(getActivity(),"敬请期待");
                    break;
                case R.id.fragment_auto_roll_pic:
                    int position = arl_arl.getCurrentIndex();

                    String url = "http://m3.rrzcp8.com/activity/group/viewPage.html?activityId=tgnbx";
                    if (position ==  0) {
                        url = "" ;
//                        enterLocalWebView("http://m3.rrzcp8.com/activity/group/viewPage.html?activityId=tgnbx","","http://img.rrzcp8.cn/rrzcp/product/images/duobao/activity/1510900370200_1.jpg");
                    }else if (position ==1 ){
                        url = "http://m3.ttacp8.com/activity/group/viewPage.html?activityId=tgn13" ;
//                        http://m3.ttacp8.com/activity/group/viewPage.html?activityId=tgn13
//                        enterLocalWebView("http://fa.163.com/optg/activity/model/hhtDrawNewActivity/page/index?from=tgncpapppc","","https://pimg1.126.net/silver/product/fams/banner/819f29ca-d4fa-4859-a4f8-9b29fae9be42.jpg");
                    }else if (position == 2){
                        url = "http://www.qipaigame1.com/zjh_download.html?from=tgnzjhcp5" ;
//                        http://www.qipaigame1.com/zjh_download.html?from=tgnzjhcp5
//                        enterLocalWebView("http://m3.rrzcp8.com/activity/group/viewPage.html?activityId=tgnbx","","http://img.rrzcp8.cn/rrzcp/product/images/duobao/activity/1510900370200_1.jpg");
                    }
//                    try {
//                        startActivity(new Intent(Intent.ACTION_VIEW,
//                                Uri.parse(url)));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    break;

                default:
                    break;
            }
        }
    };


    // 轮播图额初始化
    private void initializeViewPage() {

        List<Drawable> imgUrls = new ArrayList<>();

        imgUrls.add(getResources().getDrawable(R.mipmap.ads_3));
//        imgUrls.add(getResources().getDrawable(R.mipmap.ads_4));
        imgUrls.add(getResources().getDrawable(R.mipmap.ads_1));
        arl_arl.initData(imgUrls);
        arl_arl.setOnClickListener(mOnClickListener);
    }


    // 轮播图片 1242*525
    private void setImageParams(View view) {

        int screenWidth = ScreenUtils.getScreenWidth(getActivity());
        int width = screenWidth;
        int height = screenWidth * 525 / 1242;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
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

            view.loadUrl(javascript1);
            view.loadUrl("javascript:hideOther();");
            view.loadUrl(javascript2);
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
