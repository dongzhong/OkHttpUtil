package dongzhong.okhttputil.builder;

import android.support.annotation.NonNull;

import java.io.File;

import dongzhong.okhttputil.request.PostFileRequest;
import dongzhong.okhttputil.request.RequestCall;
import okhttp3.MediaType;

/**
 * Created by dongzhong on 2018/2/8.
 */

public class PostFileRequestBuilder extends OkHttpRequestBuilder<PostFileRequestBuilder> {
    private File file;
    private MediaType mediaType;

    public PostFileRequestBuilder file(@NonNull File file) {
        this.file = file;
        return this;
    }

    public PostFileRequestBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build() {
        return new PostFileRequest(url, tag, params, headers, file, mediaType, id).build();
    }
}
