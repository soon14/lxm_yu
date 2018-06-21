package com.lxm.ss.kuaisan.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.TrafficStats;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Process;
import android.webkit.MimeTypeMap;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lxm.ss.kuaisan.FFApplication;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.PreferenceUtils;
import com.lxm.ss.kuaisan.Utils.StorageUtils;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 定制webviewclien基类
 */
public class FFWebViewClient extends WebViewClient {

    private static final int LOAD_TIME = 7000;  //加载超过7s，提示网络速度有问题。

    private static List<String> sFileList;

    private long mBeginTime;  //访问url的开始时间
    private long mTrafficStats;
    private boolean mShowNetSpeedToast;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return shouldOverrideUrlLoading(view, request.getUrl().toString());
        } else {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mBeginTime = System.currentTimeMillis();
        mTrafficStats = TrafficStats.getUidRxBytes(Process.myUid());
        mShowNetSpeedToast = true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mShowNetSpeedToast = false;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        try {
            getFileList(view.getContext());
            WebResourceResponse resourceResponse = getCacheResource(view.getContext(), url);
            if (resourceResponse != null) {
                return resourceResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.shouldInterceptRequest(view, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (request != null && request.getUrl() != null) {
                try {
                    getFileList(view.getContext());
                    WebResourceResponse resourceResponse = getCacheResource(view.getContext(), request.getUrl().toString());
                    if (resourceResponse != null) {
                        return resourceResponse;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        uploadErrorInfo(view.getContext(), failingUrl, errorCode);
        mShowNetSpeedToast = false;
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                uploadErrorInfo(view.getContext(), request.getUrl().toString(), error.getErrorCode());
                Zlog.ii("lxm onReceivedHttpError:" +error.getErrorCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mShowNetSpeedToast = false;
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            uploadErrorInfo(view.getContext(), request.getUrl().toString(), errorResponse.getStatusCode());
            try {
                Zlog.ii("lxm onReceivedHttpError:" +errorResponse.getStatusCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mShowNetSpeedToast = false;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        if (error != null) {
            uploadErrorInfo(view.getContext(), error.getUrl(), ERROR_FAILED_SSL_HANDSHAKE);
        }
        mShowNetSpeedToast = false;
    }

    private void getFileList(Context context) {
//
        if (sFileList == null || sFileList.size() == 0) {
            sFileList = new ArrayList<>();
            File fileDir = new File(StorageUtils.getStorageWebResoucePath(context) + "/style/");
            if (fileDir.exists() && fileDir.isDirectory()) {
                File[] files = fileDir.listFiles();
                for (File file : files) {
                    sFileList.add(file.getName());
                }
            }
//            if (!PreferenceUtils.getInstance(context).getBooleanValue(PreferenceUtils.ENABLE_WEB_CACHE, false)) {
//            sFileList = null;
//            return;
//        }
        }
    }

    private WebResourceResponse getCacheResource(Context context, String url) {
        WebResourceResponse resourceResponse = null;
        if (url != null && url.trim().length() > 0) {
            try {
                String[] path = Uri.parse(url).getPath().split("/");
                String file = path[path.length - 1];
                if (sFileList != null && sFileList.size() > 0) {
                    for (String name : sFileList) {
                        if (name.equals(file)) {
                            File resource = new File(StorageUtils.getStorageWebResoucePath(context) + "/style/" + name);
                            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(url);
                            String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
                            Zlog.ii("lxm getCacheResource:" +fileExtension +"  "+ mimeType);
                            resourceResponse = new WebResourceResponse(mimeType, "UTF-8", new FileInputStream(resource));
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resourceResponse;
    }

    private void uploadErrorInfo(Context context, String url, int errorCode) {
        if (mBeginTime > 0) {
            int type = 1;
            if (errorCode == ERROR_TIMEOUT) {
                type = 2;
            }
            long traffic = TrafficStats.getUidRxBytes(Process.myUid());
            mBeginTime = 0;
        }
    }

    /**
     * 页面加载成功，发送统计信息
     */
    public void sendStatInfo(Context context, String url) {
        if (mBeginTime > 0) {
            long traffic = TrafficStats.getUidRxBytes(Process.myUid());
            mBeginTime = 0;
        }
    }

    /**
     * 提示用户网络慢
     */
    public void showNetToast(Context context) {
        if (mShowNetSpeedToast && mBeginTime > 0 && System.currentTimeMillis() - mBeginTime > LOAD_TIME) {
            if(context == null){
                context = FFApplication.getInstance().getApplicationContext();
            }
            ToastUtils.show(context, context.getResources().getString(R.string.internet_slow));
            mShowNetSpeedToast = false;
        }
    }

}
