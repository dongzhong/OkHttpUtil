package dongzhong.okhttputil.request;

import android.support.annotation.NonNull;

import java.util.Map;

import dongzhong.okhttputil.callback.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by dongzhong on 2018/2/8.
 */

public class PostFormRequest extends OkHttpRequest {
    public PostFormRequest(@NonNull String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        super(url, tag, params, headers, id);
    }

    @Override
    RequestBody generateRequestBody() {
        if (params == null || params.size() <= 0) {
            return new FormBody.Builder().build();
        }
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (String key : params.keySet()) {
            bodyBuilder.add(key, params.get(key));
        }
        return bodyBuilder.build();
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
