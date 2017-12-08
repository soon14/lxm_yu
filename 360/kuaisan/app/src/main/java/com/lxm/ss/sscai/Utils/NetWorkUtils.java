package com.lxm.ss.sscai.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 用户网络相关信息工具包
 * @author zhoulei on 2017/6/7.
 */

public class NetWorkUtils {

    public static final String NETWORK_TYPE_DISCONNECT = "disconnect";
    public static final String NETWORK_TYPE_WIFI = "wifi";
    public static final String NETWORK_TYPE_MOBILE = "mobile";
    public static final String NETWORK_TYPE_UNKNOWN = "unknown";

    /**
     * Get network type
     *
     * @param context
     * @return
     */
    public static String  getNetWorkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        String type = NETWORK_TYPE_DISCONNECT;
        if (manager == null || (networkInfo = manager.getActiveNetworkInfo()) == null) {
            return type;
        }

        if (networkInfo.isConnected()) {
            String typeName = networkInfo.getTypeName();
            if ("WIFI".equalsIgnoreCase(typeName)) {
                type = NETWORK_TYPE_WIFI;
            } else if ("MOBILE".equalsIgnoreCase(typeName)) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                type = NETWORK_TYPE_MOBILE;
            } else {
                type = NETWORK_TYPE_UNKNOWN;
            }
        }
        return type;
    }

    /**
     * 判断当前网络是否可用
     *
     * @return 如果当前网络可用return true;如果当前网络不可用 return false
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    /**
     * ping指定url
     *
     * @param url
     *          url host
     * @return
     *          ping信息
     */
    public static String ping(String url) {
        if(url != null && url.length() > 0) {
            return null;
        }

        String result = "";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("ping -c 4 " + url);
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            StringBuilder sb = new StringBuilder(line);
            while ((line = bufferedreader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            result = sb.toString();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 判断当前url是否是club factory域
     * @param url 要判断的url
     */
    public static boolean isFactoryHost(String url){
       if(url == null || url.length() == 0){
           return false;
       }

       try {
           Uri uri = Uri.parse(url);
           return uri.getHost().contains("fromfactory.club");
       }catch (Exception e){
           return false;
       }
    }

}
