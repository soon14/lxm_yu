package com.lxm.ss.shishicai.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;


import com.lxm.ss.shishicai.FFApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by lxm on 2016/11/9.
 */

public class Utils {

    /**
     * 重启app
     * @param mActivity
     */
    public static void restartApp(Activity mActivity) {
        Intent i = mActivity.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(mActivity.getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(i);
    }

    public static final String ACTIVITY_ALIAS_1 = "";
    public static final String ACTIVITY_ALIAS_2 = "";

    public static void changeIcon(Activity mActivity, int count) {
        ComponentName mDefault = mActivity.getComponentName();
        Zlog.ii("lxm changeIcon:" + count + "  " + mDefault.getClassName());
        if (count % 2 == 0) {
            changeIcon11(mActivity);
        } else if (count % 2 == 1) {
            changeIconDefault(mActivity);
        }

        PackageManager mPm = FFApplication.getInstance().getPackageManager();
//Intent 重启 Launcher 应用
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = mPm.queryIntentActivities(intent, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                ActivityManager am = (ActivityManager) mActivity.getSystemService(mActivity.ACTIVITY_SERVICE);
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }
    }

    private static void changeIconDefault(Activity mActivity) {
        ComponentName mDouble11 = new ComponentName(
                mActivity,
                "club.fromfactory.TestAlias1");
        ComponentName mDouble12 = new ComponentName(
                mActivity,
                "club.fromfactory.TestAlias2");

        disableComponent(mDouble12);
        enableComponent(mDouble11);
    }

    private static void changeIcon11(Activity mActivity) {
        ComponentName mDouble11 = new ComponentName(
                mActivity,
                "club.fromfactory.TestAlias1");
        ComponentName mDouble12 = new ComponentName(
                mActivity,
                "club.fromfactory.TestAlias2");

        disableComponent(mDouble11);
        enableComponent(mDouble12);
    }

    // 显示新的图标
    private static void enableComponent(ComponentName componentName) {
        PackageManager mPm = FFApplication.getInstance().getPackageManager();
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

    }

    /**
     * 去除旧的图标
     * @param componentName
     */
    private static void disableComponent(ComponentName componentName) {
        PackageManager mPm = FFApplication.getInstance().getPackageManager();
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

    }

    /*
   * 获取当前程序的版本号
   */
    public static int getApplicationVersionCode(Context context) {
        if (context == null) {
            context = FFApplication.getInstance().getApplicationContext();
        }

        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return Integer.MAX_VALUE;
    }

//    public static void getGoogleAdsId(final Context mContext) {
//        new Thread(new Runnable() {
//            public void run() {
//                String adId = null;
//                try {
//                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext);
//                    final boolean isLAT = adInfo.isLimitAdTrackingEnabled();
//                    adId = adInfo.getId();
//
//                    Zlog.ii("lxm ss getGoogleAdsId:" + adId + "  " + isLAT);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Zlog.ii("lxm ss getGoogleAdsId:" + e.getMessage());
//                }
//                CookieHelper.setCookie("google_ads_id=" + adId);
//            }
//        }).start();
//    }

    //

    /**
     * 请求商品类别标签，sendemptymessage 3
     *
     * @return
     */
    public static String getAndroidId() {
        return Settings.Secure.getString(FFApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceId() {
        TelephonyManager telephonyManager;
        telephonyManager =
                (TelephonyManager) FFApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(FFApplication.getInstance(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        return telephonyManager.getDeviceId();
    }


    public static String getApplicationVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = FFApplication.getInstance().getPackageName();

            //Retriving package info
            packageInfo = FFApplication.getInstance().getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Zlog.ii("lxm ss  Package Name = " + context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

//             String key1 = new String(Base64.encodeBytes(md.digest()));
                Zlog.ii("lxm ss Key Hash: " + key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Zlog.ii("Name not found " + e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Zlog.ii("lxm ss No such an algorithm " + e.toString());
        } catch (Exception e) {
            Zlog.ii("lxm ss Exception " + e.toString());
        }
        return key;
    }
}
