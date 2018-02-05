package dongzhong.okhttputil.interceptor;

import java.io.IOException;

import dongzhong.okhttputil.utils.LogUtil;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dongzhong on 2018/2/5.
 */

public class LogInterceptor implements Interceptor {
    private final String TAG = LogInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        logForResponse(response);
        return response;
    }

    private void logForRequest(Request request) {
        try {
            StringBuilder logStringBuilder = new StringBuilder();
            logStringBuilder.append("Request: \n");
            String url = request.url().toString();
            String method = request.method();
            logStringBuilder.append("url: ").append(url).append(", ");
            logStringBuilder.append("method: ").append(method).append("\n");
            Headers headers = request.headers();
            if (headers != null && headers.size() > 0) {
                logStringBuilder.append("headers: \n").append(headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    logStringBuilder.append("requestBody-mediaType: ").append(mediaType.toString());
                }
            }
            LogUtil.d(TAG, logStringBuilder.toString());
        }
        catch (Exception e) {

        }
    }

    private void logForResponse(Response response) {
        try {
            StringBuilder logStringBuilder = new StringBuilder();
            logStringBuilder.append("Response:\n");
            int code = response.code();
            String url = response.request().url().toString();
            logStringBuilder.append("code: ").append(code).append(", ");
            logStringBuilder.append("url: ").append(url).append("\n");
            Headers headers = response.headers();
            if (headers != null && headers.size() > 0) {
                logStringBuilder.append("headers: \n").append(headers.toString());
            }
            LogUtil.d(TAG, logStringBuilder.toString());
        }
        catch (Exception e) {

        }
    }
}
