package dongzhong.okhttputil.request;

import android.support.annotation.NonNull;

import java.util.Map;

import dongzhong.okhttputil.callback.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by dongzhong on 2018/2/5.
 */

public class PostStringRequest extends OkHttpRequest {
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private String content;
    private MediaType mediaType;

    public PostStringRequest(@NonNull String url, Object tag, Map<String, String> params, Map<String, String> headers, @NonNull String content, MediaType mediaType, int id) {
        super(url, tag, params, headers, id);
        this.content = content;
        this.mediaType = mediaType;

        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }
    }

    @Override
    RequestBody generateRequestBody() {
        return RequestBody.create(mediaType, content);
    }

    @Override
    RequestBody wrapRequestBody(RequestBody requestBody, Callback callback) {
        return requestBody;
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
