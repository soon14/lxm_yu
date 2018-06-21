package club.fromfactory.okhttp.build;

import club.fromfactory.okhttp.request.PostStringRequest;
import club.fromfactory.okhttp.request.RequestCall;
import okhttp3.MediaType;

public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder>
{
    private String content;
    private MediaType mediaType;


    public PostStringBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostStringRequest(url, tag, mParams, mHeaders, content, mediaType).build();
    }


}
