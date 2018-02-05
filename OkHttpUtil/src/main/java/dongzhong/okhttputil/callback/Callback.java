package dongzhong.okhttputil.callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 请求回调基类
 *
 * Created by dongzhong on 2018/2/5.
 */

public abstract class Callback {
    public abstract void onFailure(Call call, IOException e);

    public abstract void onResponse(Call call, Response response);
}
