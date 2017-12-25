package club.fromfactory.okhttp.build;

import android.text.TextUtils;

import club.fromfactory.okhttp.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder>
{
    protected String url;
    protected Object tag;
    protected Map<String, String> mHeaders;
    protected Map<String, String> mParams;
    protected int id;

    public T id(int id)
    {
        this.id = id;
        return (T) this;
    }

    public T url(String url)
    {
        this.url = url;
        return (T) this;
    }


    public T tag(Object tag)
    {
        this.tag = tag;
        return (T) this;
    }

    public T headers(Map<String, String> mHeaders)
    {
        this.mHeaders = mHeaders;
        return (T) this;
    }

    public T addHeader(String key, String val)
    {
        if (this.mHeaders == null)
        {
            mHeaders = new LinkedHashMap<>();
        }
        mHeaders.put(key, val);
        return (T) this;
    }

    public T setCookie(String cookies){
        mHeaders.put("Accept", "application/json, text/javascript, */*; q=0.01");
//        mHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        mHeaders.put("Content-Type", "application/json");
        if (!TextUtils.isEmpty(cookies)) {
            mHeaders.put("Cookie", cookies);
        }
        return (T) this;
    }

    public abstract RequestCall build();
}
