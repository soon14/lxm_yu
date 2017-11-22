package com.lxm.ss.kuaisan.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.model.AppShowData;
import com.lxm.ss.kuaisan.ui.main.MainActivity;
import com.lxm.ss.kuaisan.ui.web.WebViewActivity;

/**
 * 欢迎页，闪屏图片显示1s
 *
 * @author zhoulei on 2017/5/26.
 */
public class SplashActivity extends BaseActivity {

    private static final int DELAY_TIME = 1000;  //闪屏页存在的时间
    private static final int PERMISSION_APPLY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
        setContentView(R.layout.activity_splash);
        closeSelf();
    }

    private void closeSelf() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPreferenceUtils.getBooleanValue("is_first_install", true)) {
//                    mPreferenceUtils.setBooleanValue("is_first_install", false);
                    AppGuideActivity.launchActivity(SplashActivity.this);
                } else {
                    getStatus();
                }
                finish();
            }
        }, DELAY_TIME);
    }


    private void getStatus() {
        ToastUtils.show(SplashActivity.this,"sdsds");

        TypeReference typeReference = new TypeReference<AppShowData>(){};
        MyOkHttp.getInstance().getAppShowStatus(Constants.APPSHOW_ADID, typeReference, new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);

                if (o != null) {
                    AppShowData appShowData = (AppShowData) o;
                    Zlog.ii("lxm getStatus:" + appShowData);
                    if (AppShowData.STATUS_JUMPT_WEB.equals(appShowData.getStatus())){
                        enterWebPage(appShowData.getUrl());
                    }else {
                        enterMain();
                    }
                }else {
                    enterMain();
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                enterMain();
            }
        });
    }

    private void enterMain() {
        MainActivity.launchActivity(SplashActivity.this);
        finish();
    }
    private void enterWebPage(String url) {
        WebViewActivity.launchActivity(SplashActivity.this,url);
        finish();
    }
}
