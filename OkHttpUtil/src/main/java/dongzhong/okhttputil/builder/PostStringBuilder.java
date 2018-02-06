package dongzhong.okhttputil.builder;

import dongzhong.okhttputil.request.PostStringRequest;
import dongzhong.okhttputil.request.RequestCall;
import okhttp3.MediaType;

/**
 * Created by dongzhong on 2018/2/6.
 */

public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder> {
    protected MediaType mediaType;
    protected String content;

    public PostStringBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public PostStringBuilder content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostStringRequest(url, tag, params, headers, content, mediaType, id).build();
    }
}
