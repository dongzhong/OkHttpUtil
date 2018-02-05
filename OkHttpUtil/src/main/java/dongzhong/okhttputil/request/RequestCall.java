package dongzhong.okhttputil.request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dongzhong.okhttputil.OkHttpUtil;
import dongzhong.okhttputil.callback.Callback;
import dongzhong.okhttputil.interceptor.LogInterceptor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dongzhong on 2018/2/5.
 */

public class RequestCall {
    private OkHttpRequest okHttpRequest;
    private Request request;
    private Call call;

    private long connectTimeOut;
    private long readTimeOut;
    private long writeTimeOut;

    public RequestCall(OkHttpRequest okHttpRequest) {
        this.okHttpRequest = okHttpRequest;
    }

    public RequestCall connectTimeOut(long connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
        return this;
    }

    public RequestCall readTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public RequestCall writeTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    private Request generateRequest() {
        return okHttpRequest.generateRequest();
    }

    public Call getCall() {
        return this.call;
    }

    private Call buildCall() {
        this.request = generateRequest();
        OkHttpClient client = OkHttpUtil.getInstance().getOkHttpClient();
        if (connectTimeOut > 0 || readTimeOut > 0 || writeTimeOut > 0) {
            connectTimeOut = connectTimeOut > 0 ? connectTimeOut : OkHttpUtil.CONNECT_TIMOUT;
            readTimeOut = readTimeOut > 0 ? readTimeOut : OkHttpUtil.READ_TIMEOUT;
            writeTimeOut = writeTimeOut > 0 ? writeTimeOut : OkHttpUtil.WRITE_TIMEOUT;
            client = client.newBuilder()
                    .connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS)
                    .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                    .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                    .addInterceptor(new LogInterceptor())
                    .build();
        }
        Call call = client.newCall(request);
        return call;
    }

    /**
     * 执行异步请求
     *
     * @param callback
     */
    public void execute(Callback callback) {
        this.call = buildCall();
        OkHttpUtil.getInstance().execute(this, callback);
    }

    /**
     * 执行同步请求
     *
     * @return
     * @throws IOException
     */
    public Response execute() throws IOException{
        buildCall();
        Response response = call.execute();
        return response;
    }
}
