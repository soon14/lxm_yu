package com.lxm.ss.test.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import com.lxm.ss.test.MainActivity;
import com.lxm.ss.test.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lxm on 2017/9/13.
 */

public class LauncherBadgeHelper {

    /**
     * Set badge count

     * 针对 Samsung / xiaomi / sony 手机有效
     *
     * @param context The context of the application package.
     * @param count Badge count to be set
     */
    public static void setBadgeCount(Context context, int count) {

        String launcherClassName = getLauncherClassName(context);

        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);


        return;


//        if (count <= 0) {
//            count = 0;
//        } else {
//            count = Math.max(0, Math.min(count, 99));
//        }
//        Zlog.ii("lxm setBadgeCount:" + Build.MANUFACTURER);
//
//        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//            sendToXiaoMi(context, count);
//        } else if (Build.MANUFACTURER.equalsIgnoreCase("sony")) {
//            sendToSony(context, count);
//        } else if (Build.MANUFACTURER.toLowerCase().contains("samsung")) {
//            sendToSamsumg(context, count);
//        } else if (Build.MANUFACTURER.equalsIgnoreCase("huawei")){
//            setHuaweiBadge(context, count);
//        }else if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")){
//            changeOPPOBadge(context, count);
//        }

//        else if (Build.MANUFACTURER.equalsIgnoreCase("ZUK")){
//            changeZUKBadge(context, count);
//        }else if (isQQLanucher()){
//            setQQLauncherBadges(context, count);
//        }
    }

    /**
     * 向小米手机发送未读消息数广播
     *
     * @param count
     */
    private static void sendToXiaoMi(Context context, int count) {
        try {
            Class miuiNotificationClass = Class.forName("android.app.MiuiNotification");
            Object miuiNotification = miuiNotificationClass.newInstance();
            Field field = miuiNotification.getClass().getDeclaredField("messageCount");
            field.setAccessible(true);
            field.set(miuiNotification, String.valueOf(count == 0 ? "" : count)); // 设置信息数-->这种发送必须是miui 6才行
        } catch (Exception e) {
            Zlog.e(e.toString());
            // miui 6之前的版本
            Intent localIntent = new Intent(
                    "android.intent.action.APPLICATION_MESSAGE_UPDATE");
            localIntent.putExtra(
                    "android.intent.extra.update_application_component_name",
                    context.getPackageName() + "/" + getLauncherClassName(context));
            localIntent.putExtra(
                    "android.intent.extra.update_application_message_text", String.valueOf(count == 0 ? "" : count));
            context.sendBroadcast(localIntent);
        }
    }

    /**
     * 向小米手机发送未读消息数广播miui6以后
     *
     * @param count
     */
    private static void sendToXiaoMi2(Context context, int count) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context).setContentTitle("title").setContentText("text").setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        try {
            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, count);
            mNotificationManager.notify(10, notification);
        } catch (Exception e) {
            e.printStackTrace();
            Zlog.e(e.toString());
            // miui 6之前的版本
            Intent localIntent = new Intent(
                    "android.intent.action.APPLICATION_MESSAGE_UPDATE");
            localIntent.putExtra(
                    "android.intent.extra.update_application_component_name",
                    context.getPackageName() + "/" + getLauncherClassName(context));
            localIntent.putExtra(
                    "android.intent.extra.update_application_message_text", String.valueOf(count == 0 ? "" : count));
            context.sendBroadcast(localIntent);
        }
    }


    /**
     * 向索尼手机发送未读消息数广播

     * 据说：需添加权限：<uses-permission android:name="com.sonyericsson.home.permission.BROADCAST_BADGE"> [未验证]
     *
     * @param count
     */
    private static void sendToSony(Context context, int count) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }

        boolean isShow = true;
        if (count == 0) {
            isShow = false;
        }
        Intent localIntent = new Intent();
        localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow);//是否显示
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", launcherClassName);//启动页
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(count));//数字
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());//包名
        context.sendBroadcast(localIntent);
    }

    /**
     * 向三星手机发送未读消息数广播
     *
     * @param count
     */
    private static void sendToSamsumg(Context context, int count) {
        String launcherClassName = getLauncherClassName(context);

        Zlog.ii("lxm sendToSamsumg:" + launcherClassName);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }


    public static void changeOPPOBadge(Context paramContext, int paramInt)
    {
        int i = 99;
        for (int j = 0; ; j = paramInt)
        {
            if (j > i);
            while (true)
                try
                {
                    Bundle localBundle = new Bundle();
                    localBundle.putInt("app_badge_count", i);
                    paramContext.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, localBundle);
                    i = j;
                    return;
                }
                catch (Exception localException)
                {
                    Zlog.d("BadgeUtilImpl  "+2, "OPPOBadge badge get a  crash" + localException.getMessage());
                    return;
                }
            }
    }

    public static void setHuaweiBadge(Context paramContext, int paramInt)
    {

        if (paramInt <= 99)
            paramInt = 99;
        while (true)
        {
            try
            {
                Zlog.d("BadgeUtilImpl "+ 2, "huawiBadge mcount=" + paramInt);
                String str = getLauncherClassName(paramContext);
                if (str != null)
                {

                    Bundle localBundle = new Bundle();
                    localBundle.putString("package", paramContext.getPackageName());
                    localBundle.putString("class", str);
                    localBundle.putInt("badgenumber", paramInt);
                    paramContext.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, localBundle);
                    return;
                }else {
                    return;

                }
            }
            catch (Throwable localThrowable)
            {
                return;
            }
        }
    }

    public static void setVivo(Context paramContext, int paramInt) {
        Intent localIntent1 = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
        localIntent1.putExtra("packageName", paramContext.getPackageName());
        localIntent1.putExtra("className", MainActivity.class.getName());
        localIntent1.putExtra("notificationNum", paramInt);
        paramContext.sendBroadcast(localIntent1);
    }

    /**
     * 重置、清除Badge未读显示数

     *
     * @param context
     */
    public static void resetBadgeCount(Context context) {
        setBadgeCount(context, 0);
    }

    /**
     * Retrieve launcher activity name of the application from the context
     *
     * @param context The context of the application package.
     * @return launcher activity name of this application. From the
     * "android:name" attribute.
     */
    private static String getLauncherClassName(Context context) {
        PackageManager packageManager = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        // To limit the components this Intent will resolve to, by setting an
        // explicit package name.
        intent.setPackage(context.getPackageName());
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        // All Application must have 1 Activity at least.
        // Launcher activity must be found!
        ResolveInfo info = packageManager
                .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);

        // get a ResolveInfo containing ACTION_MAIN, CATEGORY_LAUNCHER
        // if there is no Activity which has filtered by CATEGORY_DEFAULT
        if (info == null) {
            info = packageManager.resolveActivity(intent, 0);
        }

        return info.activityInfo.name;
    }
}

