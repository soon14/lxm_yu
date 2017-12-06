package com.lxm.ss.shishicai.Utils;

import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * Created by lxm on 2017/5/31.
 */

public class UriUtils {

    private static UriUtils sUriUtils;

    private UriUtils() {
    }

    public static UriUtils getInstance() {
        if (sUriUtils == null) {
            sUriUtils = new UriUtils();
        }

        return sUriUtils;
    }

    public boolean isHtmlUrl(String url) {
        boolean isHtml = false ;
        try {
            Uri uri = Uri.parse(url);
            String scheme = uri.getScheme();
            Zlog.ii("lxm UriUtils: isHtmlUrl" + scheme);
            if ("http".equals(scheme) || "https".equals(scheme)){
                isHtml = true ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return isHtml;
    }
    public boolean isHtmlUrlApk(String url) {

        if (StringUtils.isNull(url)){
            return false;
        }
        return url.endsWith(".apk");
    }




    public static String urlChangeAddParames(String mOldUrl , HashMap<String ,String> mParames) {

        if (TextUtils.isEmpty(mOldUrl) || mParames == null || mParames.size() == 0) return mOldUrl ;
        Map<String, String> map = new HashMap<>();
        StringBuilder mStringBuilder = new StringBuilder() ;

        String sharpValue = null ;

        if (mOldUrl.contains("#")) {
            int sharpIndex = mOldUrl.indexOf("#");
            sharpValue = mOldUrl.substring(sharpIndex);
            mOldUrl = mOldUrl.substring(0, sharpIndex);
        }
        if (mOldUrl.contains("?")) {
            Uri uri = Uri.parse(mOldUrl);
            mStringBuilder.append(mOldUrl.substring(0, mOldUrl.indexOf("?")));
            Set<String> stringSet = uri.getQueryParameterNames();
            if (stringSet != null) {
                for (String key : stringSet) {
                    if (!mParames.containsKey(key)) {
                        map.put(key, uri.getQueryParameter(key));
                    }
                }
            }
        }else {
            mStringBuilder.append(mOldUrl);
        }
        String paramsValue = "";
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                paramsValue += (key + "=" + map.get(key) + "&");
            }
        }

        for (String key : mParames.keySet()) {
            paramsValue += (key + "=" + mParames.get(key) + "&");
        }
        paramsValue = paramsValue.substring(0, paramsValue.length() - 1);

        mStringBuilder.append("?").append(paramsValue);

        if (sharpValue != null) {
            mStringBuilder.append(sharpValue);
        }

        return mStringBuilder.toString();
    }

    /**
     * 对原有url加参数，
     *
     * @param baseUrl 原始url
     * @param params  参数
     */
    public static String assembleUrl(String baseUrl, String params) {
        String newUrl = baseUrl;
            if (StringUtils.isNotBlank(baseUrl) && StringUtils.isNotBlank(params)) {
            if (baseUrl.contains("?")) {
                newUrl = baseUrl + "&" + params;
            } else {
                newUrl = baseUrl + "?" + params;
            }
        }
        return newUrl;
    }
}
