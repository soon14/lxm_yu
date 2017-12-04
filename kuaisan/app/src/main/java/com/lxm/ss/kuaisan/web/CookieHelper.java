package com.lxm.ss.kuaisan.web;

import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;

import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.http.NetUtils;

/**
 * 管理cookie工具类
 * @author zhoulei  2017/6/5.
 */
public class CookieHelper {

    //本地的cookie


    public static void setCookie(String cookie){
        setCookie(NetUtils.APP_MAIN_URL, cookie);
    }

    public static void setDefaultCookie() {


    }

    public static void setCookie(String url, String cookie){
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, cookie);
    }

    public static String getCookieString() {
        String cookie = "";
        try {
            cookie =  CookieManager.getInstance().getCookie(NetUtils.APP_MAIN_URL) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;
    }
    public static String getCookieString(String host) {
        String cookie = "";
        try {
            cookie =  CookieManager.getInstance().getCookie(host) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie;
    }

    public static void clearCookie(){
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies(null);
        } else {
            cookieManager.removeAllCookie();
        }
    }


    public static String getUid(String cookie) {
        String uid = "0";
        Zlog.ii("cookie!!!"+cookie);
        if (!(null == cookie)) {
            String[] arr = cookie.split(";");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].contains("uid")) {
                    String[] uidArr = arr[i].split("\"");
                    Log.d("mycookie", uidArr[uidArr.length - 1]);
                    return cookie.replace(uidArr[uidArr.length - 1], "0");
                }
            }
        }

        return uid;
    }

    /***
     * 获取Cookie里的uid
     * @param cookie
     * @return
     */
    public static String getUidNew(String cookie) {
        Zlog.ii("lxm ss getUidNew:" + cookie);
        String uid = "0";
        if (!(null == cookie)) {
            String[] arr = cookie.split(";");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].contains("uid")) {
                    String[] uidArr = arr[i].split("\"");
//                    return cookie.replace(uidArr[uidArr.length-1],"0");
                    return uidArr[uidArr.length - 1];
                }
            }
        }
        return uid;
    }

    public static String getCountryCode(String cookie) {
        String country_code = "0";
        if (!(null == cookie)) {
            String[] arr = cookie.split(";");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].contains("country_code")) {
                    String[] uidArr = arr[i].split("=");
//                    return cookie.replace(uidArr[uidArr.length-1],"0");
                    return uidArr[uidArr.length - 1];
                }
            }
        }
        return country_code;
    }

    public static String getCountryName(String cookie) {
        String country_name = "0";
        if (!(null == cookie)) {
            String[] arr = cookie.split(";");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].contains("country_name")) {
                    String[] uidArr = arr[i].split("=");
//                    return cookie.replace(uidArr[uidArr.length-1],"0");
                    return uidArr[uidArr.length - 1];
                }
            }
        }
        return country_name;
    }
}
