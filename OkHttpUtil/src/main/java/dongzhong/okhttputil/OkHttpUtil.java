package dongzhong.okhttputil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dongzhong.okhttputil.builder.GetRequestBuilder;
import dongzhong.okhttputil.builder.PostFileRequestBuilder;
import dongzhong.okhttputil.builder.PostFormRequestBuilder;
import dongzhong.okhttputil.builder.PostStringBuilder;
import dongzhong.okhttputil.callback.Callback;
import dongzhong.okhttputil.interceptor.LogInterceptor;
import dongzhong.okhttputil.request.RequestCall;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * <p>OkHttp管理类-单例</p>
 * <p>用于管理http请求</p>
 *
 * Created by dongzhong on 2018/2/5.
 */

public class OkHttpUtil {
    public static final long CONNECT_TIMOUT = 60 * 1000L;
    public static final long READ_TIMEOUT = 60 * 1000L;
    public static final long WRITE_TIMEOUT = 60 * 1000L;

    private static OkHttpUtil instance;

    private OkHttpClient okHttpClient;

    private OkHttpUtil() {
        initOkHttpClient(null);
    }

    public static OkHttpUtil getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtil.class) {
                if (instance == null) {
                    synchronized (OkHttpUtil.class) {
                        instance = new OkHttpUtil();
                    }
                }
            }
        }
        return instance;
    }

    public void initOkHttpClient(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            this.okHttpClient = okHttpClient;
        }
        else {
            this.okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                    .addInterceptor(new LogInterceptor())
                    .build();
        }
    }

    /**
     * 获取OkHttpClient
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            initOkHttpClient(null);
        }
        return okHttpClient;
    }

    public GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    public PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public PostFormRequestBuilder postForm() {
        return new PostFormRequestBuilder();
    }

    public PostFileRequestBuilder postFile() {
        return new PostFileRequestBuilder();
    }

    public void execute(final RequestCall requestCall, final Callback callback) {
        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(call, response);
            }
        });
    }
}
