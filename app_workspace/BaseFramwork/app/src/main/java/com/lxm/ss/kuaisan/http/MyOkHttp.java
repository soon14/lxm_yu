package com.lxm.ss.kuaisan.http;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.web.CookieHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import club.fromfactory.okhttp.OkHttpUtil;

/**
 * Created by lxm on 2017/06/21
 */

public class MyOkHttp {

    private static MyOkHttp httpPost;

    public static MyOkHttp getInstance() {

        if (httpPost == null) {
            httpPost = new MyOkHttp();
        }
        return httpPost;
    }

    /*
     * 取消请求
     */
    public void clearTags(String tag) {
        OkHttpUtil.getInstance().cancelTag(tag);
    }

    /**
     * 网络请求httppost
     * @param mRequestType
     * @param url
     * @param header
     * @param params
     * @param returnType
     * @param mTypeReference
     * @param okHttpRequestListener
     * @param <T>
     */
    public synchronized <T> void doPostHttpPost(int mRequestType, String url,int returnType, Map<String, String> header,
                                                 Map<String, Object> params,
                                                  TypeReference<T> mTypeReference,OkHttpRequestListener okHttpRequestListener) {// TODO
        if (params == null) {
            params = new HashMap<>();
        }
        Zlog.ii("lxm httpost MyOkHttp doPostHttpPost:" + url);
        JSONObject jsonObject = new JSONObject(params);
        String mCookie = CookieHelper.getCookieString();
        OkHttpListenerInterface okHttpListenerInterface = new OkHttpListenerInterface(mRequestType,returnType,mTypeReference,url,System.currentTimeMillis(),okHttpRequestListener);
        OkHttpUtil.getInstance().httpPost(url,header,jsonObject.toString(),mCookie,okHttpListenerInterface.mStringCallback);
    }

    /**
     * 网络请求httpget
     * @param mRequestType 接口requesttype
     * @param url
     * @param header
     * @param params
     * @param returnType  是否进一步解析RETURN_JSON_MESSAGE RETURN_INITJSON_DATA
     * @param mTypeReference
     * @param okHttpRequestListener
     * @param <T>
     */
    public synchronized <T> void doPostHttpGet(int mRequestType, String url, int returnType,  Map<String, String> header,
                                                Map<String, String> params,
                                               TypeReference<T> mTypeReference,OkHttpRequestListener okHttpRequestListener) {// TODO
        Zlog.ii("lxm httpost MyOkHttp doPostHttpGet:" + url);
        String mCookie = CookieHelper.getCookieString();
        OkHttpListenerInterface okHttpListenerInterface = new OkHttpListenerInterface(mRequestType,returnType,mTypeReference,url,System.currentTimeMillis(),okHttpRequestListener);
        OkHttpUtil.getInstance().httpGet(url,header,params,mCookie,okHttpListenerInterface.mStringCallback);
    }
}
