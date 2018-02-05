package dongzhong.okhttputil.request;

import android.support.annotation.NonNull;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by dongzhong on 2018/2/5.
 */

public class GetRequest extends OkHttpRequest {
    public GetRequest(@NonNull String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        super(url, tag, params, headers, id);
    }

    @Override
    RequestBody generateRequestBody() {
        return null;
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }
}
