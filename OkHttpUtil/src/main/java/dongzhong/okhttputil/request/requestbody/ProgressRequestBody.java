package dongzhong.okhttputil.request.requestbody;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * 用于构建带进度的请求体
 *
 * Created by dongzhong on 2018/2/8.
 */

public class ProgressRequestBody extends RequestBody {
    protected RequestBody delegate;
    protected ProgressListener listener;

    protected ProgressSink progressSink;

    public ProgressRequestBody(@NonNull RequestBody delegate, ProgressListener listener) {
        this.delegate = delegate;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return delegate.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        progressSink = new ProgressSink(sink);
        BufferedSink bufferedSink = Okio.buffer(progressSink);
        delegate.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    @Override
    public long contentLength() {
        try {
            return delegate.contentLength();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected class ProgressSink extends ForwardingSink {
        private long bytesWritten = 0;

        public ProgressSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);

            bytesWritten += byteCount;
            if (listener != null) {
                listener.onProgress(bytesWritten, contentLength());
            }
        }
    }

    public interface ProgressListener {
        void onProgress(long byteWritten, long contentLength);
    }
}
