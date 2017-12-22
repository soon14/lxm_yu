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
    public void getHtml(final String url ,final OkHttpRequestListener okHttpRequestListener) {
        Zlog.ii("lxm httpost:getHtml:" + url );
        FFApplication.getFixThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                doPostHttpGet(RequestTypeConstant.REQUEST_MY_SWITCH, url ,
                        RequestTypeConstant.RETURN_JSON_MESSAGE,null,null,null,okHttpRequestListener);
            }
        });
    }

    private final String GET_LOTTERY_DATA= "http://api.caipiao.163.com/award_home.html?mobileType=android&ver=4.30&channel=qq_tab1&apiVer=1.1&apiLevel=27";

    public void getLotterData(final TypeReference mTypeReference,final OkHttpRequestListener okHttpRequestListener) {

        Zlog.ii("lxm httpost:getLotterData:" + GET_LOTTERY_DATA);
        FFApplication.getFixThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                doPostHttpGet(RequestTypeConstant.REQUEST_MY_SWITCH, GET_LOTTERY_DATA ,
                        RequestTypeConstant.RETURN_INITJSON_DATA,null,null,null,okHttpRequestListener);
            }
        });
    }

    private final String GET_LOTTERY_DETAIL= "http://api.caipiao.163.com/queryAwardByCond.html?mobileType=android&ver=4.30&channel=qq_tab1&apiVer=1.1&apiLevel=27";
    public void getLotteryDetail(final String gameEn ,final String period,final OkHttpRequestListener okHttpRequestListener) {

        Zlog.ii("lxm httpost:getLotteryDetail:" + GET_LOTTERY_DETAIL);
        http://api.caipiao.163.com/queryAwardByCond.html?mobileType=android&ver=4.30&channel=qq_tab1&apiVer=1.1&apiLevel=27&count=20&period=2017141&gameEn=ssq
//        currentPeriod=2017140&gameEn=ssq
        FFApplication.getFixThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
//                //设置参数，访问url获取json
//                Map<String, Object> params = new HashMap<String, Object>();
//                params.put("count", 20);
//                params.put("period", Long.valueOf(period) +1);
//                params.put("gameEn", gameEn);

                doPostHttpGet(RequestTypeConstant.REQUEST_MY_SWITCH, GET_LOTTERY_DETAIL + "&count=20" + "&period=" + Long.valueOf(period) +1
                        +"&gameEn=" +gameEn,
                        RequestTypeConstant.RETURN_JSON_MESSAGE,null,null,null,okHttpRequestListener);
            }
        });
    }
}
