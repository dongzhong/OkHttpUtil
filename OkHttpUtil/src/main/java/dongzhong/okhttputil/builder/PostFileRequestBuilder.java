package dongzhong.okhttputil.builder;

import android.support.annotation.NonNull;

import java.io.File;

import dongzhong.okhttputil.request.PostFileRequest;
import dongzhong.okhttputil.request.RequestCall;
import okhttp3.MediaType;

/**
 * Created by dongzhong on 2018/2/8.
 */

public class PostFileRequestBuilder extends OkHttpRequestBuilder<PostFormRequestBuilder> {
    private File file;
    private MediaType mediaType;

    public PostFileRequestBuilder file(@NonNull File file) {
        this.file = file;
        return this;
    }

    @Override
    RequestCall build() {
        return new PostFileRequest(url, tag, params, headers, file, mediaType, id).build();
    }
}
