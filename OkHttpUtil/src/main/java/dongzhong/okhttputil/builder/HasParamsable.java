package dongzhong.okhttputil.builder;

import java.util.Map;

/**
 * Created by dongzhong on 2018/2/5.
 */

public interface HasParamsable {
    OkHttpRequestBuilder addParam(String key, String val);
    OkHttpRequestBuilder params(Map<String, String> params);
}
