package dongzhong.okhttputil;

import java.util.concurrent.TimeUnit;

import dongzhong.okhttputil.interceptor.LogInterceptor;
import okhttp3.OkHttpClient;

/**
 * <p>OkHttp管理类-单例</p>
 * <p>用于管理http请求</p>
 *
 * Created by dongzhong on 2018/2/5.
 */

public class OkHttpManager {
    public static final long CONNECT_TIMOUT = 60 * 1000L;
    public static final long READ_TIMEOUT = 60 * 1000L;
    public static final long WRITE_TIMEOUT = 60 * 1000L;

    private static OkHttpManager instance;

    private OkHttpClient okHttpClient;

    private OkHttpManager() {
        initOkHttpClient(null);
    }

    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                if (instance == null) {
                    synchronized (OkHttpManager.class) {
                        instance = new OkHttpManager();
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
}
