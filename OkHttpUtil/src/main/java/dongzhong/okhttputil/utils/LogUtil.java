package dongzhong.okhttputil.utils;

import android.util.Log;

/**
 * Created by dongzhong on 2018/2/5.
 */

public class LogUtil {
    private static final String TAG_PREFIX = "OkHttpUtil-";

    public static void v(String tag, String logContent) {
        Log.v(TAG_PREFIX + tag, logContent);
    }

    public static void d(String tag, String logContent) {
        Log.d(TAG_PREFIX + tag, logContent);
    }

    public static void i(String tag, String logContent) {
        Log.i(TAG_PREFIX + tag, logContent);
    }

    public static void w(String tag, String logContent) {
        Log.w(TAG_PREFIX + tag, logContent);
    }

    public static void e(String tag, String logContent) {
        Log.e(TAG_PREFIX + tag, logContent);
    }
}
