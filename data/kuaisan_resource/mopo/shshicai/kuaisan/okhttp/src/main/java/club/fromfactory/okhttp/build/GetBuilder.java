package club.fromfactory.okhttp.build;

import android.net.Uri;

import club.fromfactory.okhttp.request.GetRequest;
import club.fromfactory.okhttp.request.RequestCall;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 */
public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> implements HasParamsable
{
    @Override
    public RequestCall build()
    {
        if (mParams != null)
        {
            url = appendParams(url, mParams);
        }

        return new GetRequest(url, tag, mParams, mHeaders).build();
    }

    protected String appendParams(String url, Map<String, String> params)
    {
        if (url == null || params == null || params.isEmpty())
        {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }


    @Override
    public GetBuilder params(Map<String, String> mParams)
    {
        this.mParams = mParams;
        return this;
    }

    @Override
    public GetBuilder addParams(String key, String val)
    {
        if (this.mParams == null)
        {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
        return this;
    }


}
