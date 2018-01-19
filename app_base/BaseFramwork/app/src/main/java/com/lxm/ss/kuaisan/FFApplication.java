package com.lxm.ss.kuaisan;

import android.support.multidex.MultiDexApplication;

import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.ui.main.MainActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import club.fromfactory.okhttp.httputil.Zlog;

/**
 */
public class FFApplication extends MultiDexApplication {

    private static FFApplication instance;
    public HashMap<Integer, Integer> itemPosition = new HashMap<>();
    /*
         *
         * mActivityList:Activities for children of BaseActivity.
         */
    private static List<BaseActivity> mActivityList = new LinkedList<BaseActivity>();

    /** 每次执行限定个数个任务的线程池 */
    private static ExecutorService mFixedThreadExecutor = null;
    private static ExecutorService mCachedThreadPool = null ;


    public Map<String,String> adjusgEventTokenMap = new HashMap<>();

    public static FFApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        mFixedThreadExecutor = Executors.newFixedThreadPool(5);
        mCachedThreadPool = Executors.newCachedThreadPool();

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
