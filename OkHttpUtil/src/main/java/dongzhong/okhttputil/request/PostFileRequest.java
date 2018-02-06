package dongzhong.okhttputil.request;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.Map;

import dongzhong.okhttputil.callback.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by dongzhong on 2018/2/6.
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
        RequestBody requestBody = RequestBody.create(mediaType, file);
        return requestBody;
    }

    @Override
    RequestBody wrapRequestBody(RequestBody requestBody, Callback callback) {
        if (callback == null) {
            return requestBody;
        }

        return null;
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return null;
    }
}
