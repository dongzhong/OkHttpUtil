package dongzhong.okhttputil.builder;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

import dongzhong.okhttputil.request.RequestCall;

/**
 * <p>请求Builder基类</p>
 *
 * Created by dongzhong on 2018/2/5.
 */

public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder> {
    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected int id;

    public T url(@NonNull String url) {
        this.url = url;
        return (T) this;
    }

    public T tag(Object tag) {
        this.tag = tag;
        return (T) this;
    }

    public T headers(Map<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T id(int id) {
        this.id = id;
        return (T) this;
    }

    public T addHeader(String key, String val) {
        if (key == null || key.isEmpty()) {
            return (T) this;
        }
        if (this.headers == null) {
            this.headers = new LinkedHashMap<>();
        }
        this.headers.put(key, val);
        return (T) this;
    }

    /**
     * 构建RequestCall
     *
     * @return
     */
    abstract RequestCall build();
}
