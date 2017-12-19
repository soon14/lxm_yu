package com.lxm.ss.kuaisan.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.kuaisan.FFApplication;
import com.lxm.ss.kuaisan.Utils.FastjsonUtil;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.http.model.HttpResult;
import club.fromfactory.okhttp.callback.StringCallback;
import okhttp3.Call;

/**
 * Created by lxm on 2016/11/9.
 */

public class OkHttpListenerInterface<T> {

    private String failedPrompt = "Server connection failure";
    private int mRequestType;// 请求类型
    private int returnType; // 是否需要进一步解析
    private TypeReference mTypeReference;// 返回数据类型
    private String url;// 请求地址
    private long begin ;

    private OkHttpRequestListener mOkHttpRequestListener ;

    public OkHttpListenerInterface(int mRequestType, int returnType,
                                   TypeReference mTypeReference, String url, long begin,OkHttpRequestListener mOkHttpRequestListener) {

        OkHttpListenerInterface.this.mRequestType = mRequestType;
        OkHttpListenerInterface.this.returnType = returnType;
        OkHttpListenerInterface.this.mTypeReference = mTypeReference;
        OkHttpListenerInterface.this.url = url;
        OkHttpListenerInterface.this.begin = begin;
        OkHttpListenerInterface.this.mOkHttpRequestListener = mOkHttpRequestListener;
    }

    private OkHttpListenerInterface() {
        super();
    }

    public StringCallback mStringCallback = new StringCallback() {
        @Override
        public void onError(final Call call, final Exception e) {
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                Zlog.ii("lxm ss okhttp Thread:Main Thread");
            } else {
                Zlog.ii("lxm ss okhttp Thread:Not Main Thread");
            }
            FFApplication.getCacheThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    Zlog.ii("lxm okhttp onErrorResponse:"+ mRequestType+  e.getMessage() +" " );
                    //处理保存数据
                    saveLocalData(false,null);
                    onFailed(call.hashCode(),"",e.getMessage());
                }
            });

        }

        @Override
        public void onResponse(final String response) {
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                Zlog.ii("lxm ss okhttp Thread:Main Thread");
            } else {
                Zlog.ii("lxm ss okhttp Thread:Not Main Thread");
            }
            FFApplication.getCacheThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    if (response != null) {
                        Zlog.ii("lxm okhttp onResponse:"+ mRequestType + "  "+  response);
                        initJson(response);
                    }else {
                        Zlog.ii("lxm okhttp onResponse:null" + mRequestType);
                        onFailed(400,"",failedPrompt);
                    }
                }
            });

        }
    };

    /**
     * 解析
     *
     */
    private synchronized void initJson(String response) {
        Zlog.ii("lxm httppost initJson 1:" + response);

        Object obj = null;
        if (TextUtils.isEmpty(response)) {
            Zlog.ii("lxm httppost initJson 4:" + response);
            onFailed(400,"",failedPrompt);
        } else {
            Zlog.ii("lxm httppost  initJson 2:" + response);
            switch (returnType) {
                case RequestTypeConstant.RETURN_INITJSON_DATA: {
                    Zlog.ii("lxm httppost  initJson 3:" + response);
                    obj = parseJsonMessage(response);
                    if (obj != null) {
                        Zlog.ii("lxm httppost  initJson 4:" + obj);
                        onSucceed(obj);
                    } else {
                        onFailed(400,"",failedPrompt);
                    }
                }
                break;
                case RequestTypeConstant.RETURN_JSON_MESSAGE: {
                    Zlog.ii("lxm httppost  initJson 5:" + response);
                        if (response != null) {
                            onSucceed(response);
                        } else {
                            onFailed(400,"",failedPrompt);
                        }
                }
                break;
                case RequestTypeConstant.RETURN_JSON_MESSAGE_NEW:{
                    TypeReference mTypeReference = new TypeReference<HttpResult>(){};
                    HttpResult mHttpResult = (HttpResult) FastjsonUtil.getInstance().parseJson(response,mTypeReference);
                    if (mHttpResult != null) {
                        if (RequestTypeConstant.SERVER_RETURN_OK == mHttpResult.getCode()) {
                            onSucceed(mHttpResult.getBody());
                        }else if (RequestTypeConstant.SERVER_RETURN_NOT_LOGIN == mHttpResult.getCode()){
                            onNotLogin(mHttpResult.getMessage());
                        }
                        else {
                            onFailed(mHttpResult.getCode(),mHttpResult.getBody(),mHttpResult.getMessage());
                        }
                    }else {
                        onFailed(400,"",failedPrompt);
                    }
                }
                    break;
                case RequestTypeConstant.RETURN_INITJSON_DATA_NEW: {
                    TypeReference mTypeReference = new TypeReference<HttpResult>(){};
                    HttpResult mHttpResult = (HttpResult) FastjsonUtil.getInstance().parseJson(response,mTypeReference);
                    if (mHttpResult != null) {

                        if (RequestTypeConstant.SERVER_RETURN_OK == mHttpResult.getCode()) {
                            obj = parseJsonMessage(mHttpResult.getBody());
                            Zlog.ii("lxm httppost  initJson 9:" + mHttpResult.getBody());
                            if (obj != null) {
                                Zlog.ii("lxm httppost  initJson 4:" + obj);
                                onSucceed(obj);
                            } else {
                                onFailed(400,"",failedPrompt);
                            }
                        }else if (RequestTypeConstant.SERVER_RETURN_NOT_LOGIN == mHttpResult.getCode()){
                            onNotLogin(mHttpResult.getMessage());
                        }else {
                            onFailed(mHttpResult.getCode(),mHttpResult.getBody(),mHttpResult.getMessage());
                        }
                    }else {
                        onFailed(400,"",failedPrompt);
                    }
                }
                    break;

                default:
                    break;
            }

        }
    }

    /**
     * 解析返回json的message
     *
     * @return
     */
    private synchronized T parseJsonMessage(String message) {
        Zlog.ii("lxm okhttp parseJsonMessage 1:"+ message);
        if (message == null) {
            return null;
        }
        T o = null;
        try {

            if (mOkHttpRequestListener == null) {

            }else {
                o = (T) FastjsonUtil.getInstance().parseJson(message,mTypeReference);
                Zlog.ii("lxm okhttp parseJsonMessage 2:"+ o.toString());
            }
            //处理保存数据
            saveLocalData(true,message);

        } catch (Exception e) {
            e.printStackTrace();
            Zlog.i("lxm okhttp parseJsonMessage Exception:" + mRequestType + e.getMessage());
        }
        return o;
    }

    private void onNotLogin( final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mOkHttpRequestListener != null) {
                    mOkHttpRequestListener.onNoLoginStatus(message);
                }
            }
        });
    }
    private void onFailed(final int code ,final String body , final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mOkHttpRequestListener != null) {
                    mOkHttpRequestListener.onFailed(code,body,message);
                }
            }
        });
    }

    private void onSucceed(final Object o) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mOkHttpRequestListener != null) {
                    mOkHttpRequestListener.onSucceed(o);
                }
            }
        });
    }


    private void saveLocalData(boolean isSucceed, String message) {
        //处理保存数据
        switch (mRequestType) {
            default:
                break;
        }
    }
}
