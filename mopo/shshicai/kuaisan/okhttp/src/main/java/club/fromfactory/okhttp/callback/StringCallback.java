package club.fromfactory.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

public abstract class StringCallback extends OkCallback<String>
{
    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        return response.body().string();
    }
}
