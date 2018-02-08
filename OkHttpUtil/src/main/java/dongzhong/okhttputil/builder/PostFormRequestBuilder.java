package dongzhong.okhttputil.builder;

import java.util.LinkedHashMap;
import java.util.Map;

import dongzhong.okhttputil.request.PostFormRequest;
import dongzhong.okhttputil.request.RequestCall;

/**
 * Created by dongzhong on 2018/2/8.
 */

public class PostFormRequestBuilder extends OkHttpRequestBuilder<PostFormRequestBuilder> implements HasParamsable {
    @Override
    public RequestCall build() {
        return new PostFormRequest(url, tag, params, headers, id).build();
    }

    @Override
    public PostFormRequestBuilder addParam(String key, String val) {
        if (key == null || key.isEmpty()) {
            return this;
        }
        if (params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostFormRequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }
}
