package club.fromfactory.okhttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import club.fromfactory.okhttp.build.GetBuilder;
import club.fromfactory.okhttp.build.PostStringBuilder;
import club.fromfactory.okhttp.callback.OkCallback;
import club.fromfactory.okhttp.callback.StringCallback;
import club.fromfactory.okhttp.httputil.Platform;
import club.fromfactory.okhttp.request.RequestCall;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by lxm on 2017/6/20.
 */

public class OkHttpUtil {

    public static final long DEFAULT_MILLISECONDS = 10000; //默认超时
    private static OkHttpUtil mOkHttpUtil;

    private OkHttpClient mOkHttpClient;

    private Platform mPlatform;

    public OkHttpUtil() {
        initialize();
    }

    public static OkHttpUtil initClient() {

        synchronized (OkHttpUtil.class) {
            if (mOkHttpUtil == null) {
                mOkHttpUtil = new OkHttpUtil();
            }
        }
        return mOkHttpUtil;
    }


    public static OkHttpUtil getInstance()
    {
        return initClient();
    }

    private void initialize() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        }
        mPlatform = Platform.get();
    }

    public OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        }

        return mOkHttpClient;
    }

    public void httpGet(String url, Map<String, String> header, Map<String, String> params,String mCookie, StringCallback stringCallback) {
        if (header == null) {
            header = new HashMap<>();
        }
        if (params == null) {
            params = new HashMap<>();
        }
        try {
            new GetBuilder()
                    .url(url)
                    .tag(url)
                    .headers(header)
                    .setCookie(mCookie)
                    .params(params)
                    .build()
                    .readTimeOut(5000)
                    .writeTimeOut(5000)
                    .connTimeOut(5000)
                    .execute(stringCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void httpPost(String url, Map<String, String> header, String data,String mCookie, StringCallback stringCallback) {
        if (header == null) {
            header = new HashMap<>();
        }
        try {
            new PostStringBuilder()
                    .url(url).tag(url)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .headers(header).content(data)
                    .setCookie(mCookie)
                    .build()
                    .readTimeOut(5000)
                    .writeTimeOut(5000)
                    .connTimeOut(5000)
                    .execute(stringCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void execute(final RequestCall requestCall, OkCallback callback) {
        if (callback == null)
            callback = OkCallback.CALLBACK_DEFAULT;
        final OkCallback finalCallback = callback;

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(call, e, finalCallback);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalCallback);
                        return;
                    }

                    if (!response.isSuccessful()) {
                        sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback);
                        return;
                    }

                    Object o = finalCallback.parseNetworkResponse(response);
                    sendSuccessResultCallback(o, finalCallback);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalCallback);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final OkCallback callback) {
        if (callback == null) return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final OkCallback callback) {
        if (callback == null) return;
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
            }
        });
    }

    public void cancelTag(Object tag)
    {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }


}
