package dongzhong.okhttputil.builder;

import android.net.Uri;

import java.util.LinkedHashMap;
import java.util.Map;

import dongzhong.okhttputil.request.GetRequest;
import dongzhong.okhttputil.request.RequestCall;

/**
 * Created by dongzhong on 2018/2/5.
 */

public class GetRequestBuilder extends OkHttpRequestBuilder<GetRequestBuilder> implements HasParamsable {
    @Override
    public GetRequestBuilder addParam(String key, String val) {
        if (key == null || key.isEmpty()) {
            return this;
        }
        if (this.params == null) {
            this.params = new LinkedHashMap<>();
        }
        this.params.put(key, val);
        return this;
    }

    @Override
    public GetRequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    protected void appendParams() {
        if (params != null && !params.isEmpty()) {
            Uri.Builder builder = Uri.parse(url).buildUpon();
            for (String key : params.keySet()) {
                builder.appendQueryParameter(key, params.get(key));
            }
            this.url = builder.build().toString();
        }
    }

    @Override
    RequestCall build() {
        appendParams();
        return new GetRequest(url, tag, params, headers, id).build();
    }
}
