package com.lxm.ss.kuaisan.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lxm.ss.kuaisan.BuildConfig;
import com.lxm.ss.kuaisan.FFApplication;

/**
 * Created by lxm on 2016/11/9.
 */

public class NetUtils {

    public static final int SERVER_RETURN_NULL = 100;
    public static final int SERVER_RETURN_ERROR = 101;
    public static final int SERVER_RETURN_OK = 102;

    public static final int NET_CONNECT_TYPE_NONE = -1;
    public static final int NET_CONNECT_TYPE_WIFI = 1;
    public static final int NET_CONNECT_TYPE_MOBILE = 2;

     public static String APP_MAIN_URL = BuildConfig.SERVER_PATH;


//    static {
//        APP_MAIN_URL = PreferenceUtils.getInstance(FFApplication.getInstance().getApplicationContext())
//                .getStringValue(PreferenceUtils.CHANGE_HOST, APP_MAIN_URL);
//    }
	public static final String BLANK_PAGE = "about:blank";
    /**
     * 检查网络
     *
     * @param context
     * @return 返回是否有网
     */
    public static boolean checkNetworkAvailable(Context context) {
        if(context == null){
            context = FFApplication.getInstance().getApplicationContext();
        }

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                int type = mNetworkInfo.getType();
                switch (type) {
                    case ConnectivityManager.TYPE_WIFI:
                        return NET_CONNECT_TYPE_WIFI;
                    case ConnectivityManager.TYPE_MOBILE:
                        return NET_CONNECT_TYPE_MOBILE;
                    default:
                        break;
                }
            }
        }
        return NET_CONNECT_TYPE_NONE;
    }

}
