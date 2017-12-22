package com.lxm.ss.kuaisan.http;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.kuaisan.FFApplication;
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


    private final String STATUE_SWITCH = "http://1114600.com:8080/appgl/appShow/getByAppId?appId=";

    public void getAppShowStatus(final String appid ,final TypeReference mTypeReference,final OkHttpRequestListener okHttpRequestListener) {
        Zlog.ii("lxm httpost:getAppShowStatus:" + STATUE_SWITCH + "  " +appid);
        FFApplication.getFixThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                doPostHttpGet(RequestTypeConstant.REQUEST_STATUE_SWITCH, STATUE_SWITCH + appid,
                        RequestTypeConstant.RETURN_INITJSON_DATA,null,null,mTypeReference,okHttpRequestListener);
            }
        });
    }
    private final String STATUE_MY_SWITCH = "http://blog.csdn.net/u013101747/article/details/78616842";

    public void getMySwitchStatus(final OkHttpRequestListener okHttpRequestListener) {
        Zlog.ii("lxm httpost:getMySwitchStatus:" + STATUE_MY_SWITCH );
        FFApplication.getFixThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                doPostHttpGet(RequestTypeConstant.REQUEST_MY_SWITCH, STATUE_MY_SWITCH ,
                        RequestTypeConstant.RETURN_JSON_MESSAGE,null,null,null,okHttpRequestListener);
            }
        });
    }


    public void getHtmlString(final String url,final OkHttpRequestListener okHttpRequestListener) {
        Zlog.ii("lxm httpost:getHtmlString:" + url );
        FFApplication.getFixThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                doPostHttpGet(RequestTypeConstant.REQUEST_GET_HTML_STRING, url ,
                        RequestTypeConstant.RETURN_JSON_MESSAGE,null,null,null,okHttpRequestListener);
            }
        });
    }
}
