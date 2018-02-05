package dongzhong.okhttputil;

/**
 * OkHttp管理类-单例
 *
 * Created by dongzhong on 2018/2/5.
 */

public class OkHttpManager {
    private static OkHttpManager instance;

    private OkHttpManager() {

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
}
