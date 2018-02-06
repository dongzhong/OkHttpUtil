package dongzhong.okhttputil.request;

import android.support.annotation.NonNull;

import java.util.Map;

import dongzhong.okhttputil.callback.Callback;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 请求基类
 *
 * Created by dongzhong on 2018/2/5.
 */

public abstract class OkHttpRequest {
    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected int id;

    protected Request.Builder builder = new Request.Builder();

    public OkHttpRequest(@NonNull String url,
                         Object tag,
                         Map<String, String> params,
                         Map<String, String> headers,
                         int id) {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        this.id = id;

        initBuilder();
    }

    private void initBuilder() {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    private void appendHeaders() {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        Headers.Builder headersBuilder = new Headers.Builder();
        for (String key : headers.keySet()) {
            headersBuilder.add(key, headers.get(key));
        }
        builder.headers(headersBuilder.build());
    }

    public int getId() {
        return this.id;
    }

    /**
     * 生成Request
     *
     * @return
     */
    public Request generateRequest(Callback callback) {
        RequestBody requestBody = generateRequestBody();
        RequestBody wrapedRequestBody = wrapRequestBody(requestBody, callback);
        Request request = buildRequest(wrapedRequestBody);
        return request;
    }

    abstract RequestBody generateRequestBody();

    abstract RequestBody wrapRequestBody(RequestBody requestBody, Callback callback);

    abstract Request buildRequest(RequestBody requestBody);

    /**
     * 构建RequestCall
     *
     * @return
     */
    public RequestCall build() {
        return new RequestCall(this);
    }
}
