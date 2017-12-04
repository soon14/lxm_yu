package com.lxm.ss.kuaisan;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.ui.main.MainActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import club.fromfactory.okhttp.OkHttpUtil;
import club.fromfactory.okhttp.cookie.CookieJarImpl;
import club.fromfactory.okhttp.cookie.store.MemoryCookieStore;
import club.fromfactory.okhttp.https.HttpsUtils;
import club.fromfactory.okhttp.httputil.LoggerInterceptor;
import club.fromfactory.okhttp.httputil.Zlog;
import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 */
public class FFApplication extends Application {

    private static FFApplication instance;
    /*
         *
         * mActivityList:Activities for children of BaseActivity.
         */
    private static List<BaseActivity> mActivityList = new LinkedList<BaseActivity>();

    /** 每次执行限定个数个任务的线程池 */
    private static ExecutorService mFixedThreadExecutor = null;
    private static ExecutorService mCachedThreadPool = null ;

    public static FFApplication getInstance() {
        return instance;
    }


    public Map<String,String> mapLotterInfor = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        mFixedThreadExecutor = Executors.newFixedThreadPool(5);
        mCachedThreadPool = Executors.newCachedThreadPool();

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        initOkHttp();
        initFresco();
        initData();
    }

    private void initData() {
        mapLotterInfor.put("football_9","胜负彩/任选九");
        mapLotterInfor.put("qxc","七星彩");
        mapLotterInfor.put("hbkuai3","湖北快三");
        mapLotterInfor.put("football_sfc","");
        mapLotterInfor.put("ssq","双色球");
        mapLotterInfor.put("gdd11","粤11选5");
        mapLotterInfor.put("dlt","大乐透");
        mapLotterInfor.put("nmgkuai3","易快三");
        mapLotterInfor.put("zjd11","易乐11选5");
        mapLotterInfor.put("x3d","3D");
        mapLotterInfor.put("pl5","排列5");
        mapLotterInfor.put("qlc","七星彩");
        mapLotterInfor.put("kuai3","快三");
        mapLotterInfor.put("pl3","排列3");
        mapLotterInfor.put("oldkuai3","老快三");
        mapLotterInfor.put("ssc","重庆时时彩");
        mapLotterInfor.put("klpk","快乐扑克");
    }


    private void initOkHttp(){

        //okhtp
//        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor())
                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtil.initClient();

    }

    private void initFresco() {

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }

    public static ExecutorService getFixThreadExecutor() {
        return mFixedThreadExecutor ;
    }
    public static ExecutorService getCacheThreadExecutor() {
        return mCachedThreadPool ;
    }

    // 开放Volley的HTTP请求队列接口
//    public static RequestQueue getRequestQueue() {
//        return volleyQueue;
//    }

    public static void addActivity(BaseActivity activity) {
        mActivityList.add(activity);
    }

    public static void removeActivity(BaseActivity activity) {
        mActivityList.remove(activity);
    }

    public static void finishAllAndExit() {
        try {
            for (BaseActivity activity : mActivityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } finally {

        }
    }

    /*
     * Finish all children of BaseActivity in mActivityList,
     * and exit form app.
     */
    public boolean isHasMainActivity() {
        int a = 0;
        try {
            for (BaseActivity activity : mActivityList) {
                if (MainActivity.class.getName().equals(activity.getClass().getName())) {
                    a++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Zlog.ii("lxm  isHasMainActivity:" + a);

        return a > 0;

    }
}
