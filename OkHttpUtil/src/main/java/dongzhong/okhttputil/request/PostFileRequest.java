package dongzhong.okhttputil.request;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.Map;

import dongzhong.okhttputil.callback.Callback;
import dongzhong.okhttputil.request.requestbody.ProgressRequestBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by dongzhong on 2018/2/8.
 */

public class PostFileRequest extends OkHttpRequest {
    private static MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");

    private File file;
    private MediaType mediaType;

    public PostFileRequest(@NonNull String url, Object tag, Map<String, String> params, Map<String, String> headers, @NonNull File file, MediaType mediaType, int id) {
        super(url, tag, params, headers, id);
        this.file = file;
        this.mediaType = mediaType;
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_STREAM;
        }
    }

    @Override
    RequestBody generateRequestBody() {
        return RequestBody.create(mediaType, file);
    }

    @Override
    RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        if (callback == null) {
            return requestBody;
        }
        ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, new ProgressRequestBody.ProgressListener() {
            @Override
            public void onProgress(long byteWritten, long contentLength) {
                float progress = byteWritten * 1.0f / contentLength;
                callback.onProgress(progress, contentLength, id);
            }
        });
        return progressRequestBody;
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
