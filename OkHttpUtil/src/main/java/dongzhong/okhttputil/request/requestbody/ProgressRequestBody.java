package dongzhong.okhttputil.request.requestbody;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by dongzhong on 2018/2/6.
 */

public class ProgressRequestBody extends RequestBody {
    private RequestBody requestBody;
    private Listener listener;

    public ProgressRequestBody(@NonNull RequestBody requestBody, Listener listener) {
        this.requestBody = requestBody;
        this.listener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        requestBody.writeTo(sink);
    }

    @Override
    public long contentLength() throws IOException {
        try {
            return requestBody.contentLength();
        }
        catch (Exception e) {

        }
        return -1;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static interface Listener {
        void onProgress(int progress);
    }
}
