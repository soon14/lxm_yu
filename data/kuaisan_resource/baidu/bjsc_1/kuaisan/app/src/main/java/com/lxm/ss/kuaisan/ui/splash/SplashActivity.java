package com.lxm.ss.kuaisan.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.lottery.bean.MyBean;
import com.lottery.ui.OfficalMainActivity;
import com.lottery.ui.activity.OfficalNetActivity;
import com.lottery.ui.activity.TabMainActivity;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.model.AppShowData;
import com.lxm.ss.kuaisan.ui.main.MainActivity;
import com.lxm.ss.kuaisan.ui.web.WebViewActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import club.fromfactory.baselibrary.Test02Activity;
import club.fromfactory.baselibrary.utils.StringUtils;
import club.fromfactory.okhttp.OkHttpUtil;
import club.fromfactory.okhttp.build.GetBuilder;
import club.fromfactory.okhttp.build.PostFormBuilder;
import club.fromfactory.okhttp.build.PostStringBuilder;
import club.fromfactory.okhttp.callback.OkCallback;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

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
//                if (mPreferenceUtils.getBooleanValue("is_first_install", true)) {
//                    mPreferenceUtils.setBooleanValue("is_first_install", false);
//                    AppGuideActivity.launchActivity(SplashActivity.this);
//                    finish();
//                } else {
                    getMySwitch();
//                }
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
//        getWebViewUrl();
        enterMainActivity();
    }

    private void enterMainActivity() {
                MainActivity.launchActivity(SplashActivity.this);
//        Intent intent = new Intent(SplashActivity.this, TabMainActivity.class);
//        startActivity(intent);
        finish();
    }

    private String showUrl;
    private Boolean show = false;

    private void getWebViewUrl () {

        //
        String url = "http://1114600.com:8080/appgl/appShow/getByAppId?appId=yj20171221002";

        MyOkHttp.getInstance().getHtml(url, new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);
                Zlog.ii("lxm getWebViewUrl3:onSucceed " + o +"  ");
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                Zlog.ii("lxm getWebViewUrl3:onError " + message +"  ");
            }
        });

//        if (!show) {
//            return;
//        }

        String postJsonString = getPostJsonString();

        Zlog.ii("lxm getWebViewUrl: " + postJsonString +"  ");
        Zlog.ii("lxm getWebViewUrl: " + url +"  ");
        new GetBuilder()
                .url(url)
                .tag(url)
                .build()
                .readTimeOut(5000)
                .writeTimeOut(5000)
                .connTimeOut(5000)
                .execute(new OkCallback() {
                    @Override
                    public Object parseNetworkResponse(Response response) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Zlog.ii("lxm getWebViewUrl1:onError " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Object response) {
                        Zlog.ii("lxm getWebViewUrl1:onError " + response);
                    }
                });


        OkHttpUtils.get().url(postJsonString).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Zlog.ii("lxm getWebViewUrl2:onError " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {

                Zlog.ii("lxm getWebViewUrl2:onResponse " + response);
            }
        });

//        MyOkHttp.getInstance().postHtml(getPostJsonString(),new OkHttpRequestListener() {
//            @Override
//            public void onSucceed(Object o) {
//                super.onSucceed(o);
//                String data = (String) o;
//                MyBean myBean = null;
//                Intent intent = new Intent();
//                switch (URL) {
//                    case "1":
//                        try {
//                            JSONObject jsonObject = new JSONObject(data);
//                            JSONObject data3 = jsonObject.optJSONObject("data");
//                            if (data3 != null) {
//                                if (data3.getString("show_url") != null) {
//                                    if (data3.getString("show_url").equals("1")) {
//                                        show = true;
//                                        showUrl = data3.getString("url");
//                                        intent.setClass(SplashActivity.this, OfficalNetActivity.class);
//                                    }
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case "2":
//                        myBean = new Gson().fromJson(data, MyBean.class);
//                        if ("1".equals(myBean.getIsshowwap())) {
//                            show = true;
//                            showUrl = myBean.getWapurl();
//                            intent.setClass(SplashActivity.this, OfficalNetActivity.class);
//                        }
//                        break;
//                    case "3":
//                        JSONObject jsonObject = null;
//                        String code = null;
//                        String netdata = null;
//                        try {
//                            jsonObject = new JSONObject(data);
//                            code = jsonObject.getString("rt_code");
//                            netdata = jsonObject.getString("data");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        if ("200".equals(code)) {
//                            String B = new String(Base64.decode(netdata.getBytes(), Base64.DEFAULT));
//                            myBean = new Gson().fromJson(B, MyBean.class);
//                        }
//                        if (null != myBean && "1".equals(myBean.getShow_url())) {
//                            show = true;
//                            showUrl = myBean.getUrl();
//                            intent.setClass(SplashActivity.this, OfficalNetActivity.class);
//                        }
//                        break;
//                    case "4":
//                        myBean = new Gson().fromJson(data, MyBean.class);
//                        if ("1".equals(myBean.getStatus())) {
//                            show = true;
//                            showUrl = myBean.getUrl();
//                            intent.setClass(SplashActivity.this, OfficalMainActivity.class);
//                        }
//                        break;
//                }
//
//                if (show) {
//                    intent.putExtra("url", showUrl);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    intent.setClass(SplashActivity.this, TabMainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//            }
//
//            @Override
//            public void onFailed(int code, String body, String message) {
//                super.onFailed(code, body, message);
//            }
//        });
    }

    private String URL = "1";

    private String heijinID = "20178857";
    private String sttingID = "2017112838";
    private String hunanID =null;
    private String lihkID = "yj20171120002";

    public String getPostJsonString() {
        String str = null;
        switch (URL) {
            case "1":
                str = "http://www.08cpapp.com/Lottery_server/check_and_get_url.php?type=android&appid=" + heijinID;
                break;
            case "2":
                str = "http://www.27305.com/frontApi/getAboutUs?appid=" + sttingID;
                break;
            case "3":
                str = "http://vipapp.01appkkk.com/Lottery_server/get_init_data.php?type=android&appid=" + hunanID;
                break;
            case "4":
                str = "http://1114600.com:8080/appgl/appShow/getByAppId?appId=" + lihkID;
                break;
        }
        return str;
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
