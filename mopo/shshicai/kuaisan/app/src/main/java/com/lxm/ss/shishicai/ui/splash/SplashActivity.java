package com.lxm.ss.shishicai.ui.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.shishicai.R;
import com.lxm.ss.shishicai.Utils.Zlog;
import com.lxm.ss.shishicai.base.BaseActivity;
import com.lxm.ss.shishicai.constant.Constants;
import com.lxm.ss.shishicai.http.MyOkHttp;
import com.lxm.ss.shishicai.http.OkHttpRequestListener;
import com.lxm.ss.shishicai.model.AppShowData;
import com.lxm.ss.shishicai.ui.main.MainActivity;
import com.lxm.ss.shishicai.ui.web.WebViewActivity;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * 欢迎页，闪屏图片显示1s
 *
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(SplashActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            }
        }
        closeSelf();
    }

    private void closeSelf() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPreferenceUtils.getBooleanValue("is_first_install", true)) {
                    mPreferenceUtils.setBooleanValue("is_first_install", false);
                    AppGuideActivity.launchActivity(SplashActivity.this);
                    finish();
                } else {
                    getMySwitch();
                }
            }
        }, DELAY_TIME);
    }

    private void getMySwitch() {
        MyOkHttp.getInstance().getMySwitchStatus(  new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);
                if (o != null) {
                    String str = (String) o;
                    Zlog.ii("lxm getMySwitch:" + str);
                    str = StringUtils.matchStrString(Constants.MY_SWITCH_REG,str);

                    if (StringUtils.isNull(str)){
                        getStatus();
                    }else {
                        try {
//                            String[] split = str.trim().split("=");
//                            Zlog.ii("lxm getMySwitch:2 " +str + " " );
                            Zlog.ii("lxm getMySwitch:1  " + str);
                            if ( str.equals("1")) {
                                enterMain();
                            }else {
                                getStatus();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            getStatus();
                        }
                    }
                }else {
                    getStatus();
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                getStatus();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...

                }
            }
        }
    }

    private void getStatus() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
