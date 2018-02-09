package dongzhong.okhttputiltest.util;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by dongzhong on 2018/2/9.
 */

public class FileUtil {
    public static final String filePath = "OkHttpUtil";
    public static final String fileName = "uploadFile.txt";

    public static void createNewFile() {
        String externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();

        File okHttpUtilFilePath = new File(externalStorageDirectory, filePath);
        if (!okHttpUtilFilePath.exists()) {
            okHttpUtilFilePath.mkdir();
        }
        uploadFile = new File(okHttpUtilFilePath, fileName);
        if (!uploadFile.exists()) {
            try {
                uploadFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(uploadFile);
                int fileLength = 1000;
                while (fileLength > 0) {
                    fileOutputStream.write("A".getBytes());
                    fileLength--;
                }
            }
            catch (Exception e) {

            }
        }
    }

    public static File uploadFile;
    static {
        String externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();

        File okHttpUtilFilePath = new File(externalStorageDirectory, filePath);
        if (!okHttpUtilFilePath.exists()) {
            okHttpUtilFilePath.mkdir();
        }
        uploadFile = new File(okHttpUtilFilePath, fileName);
        if (!uploadFile.exists()) {
            try {
                uploadFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(uploadFile);
                int fileLength = 1000;
                while (fileLength > 0) {
                    fileOutputStream.write("A".getBytes());
                    fileLength--;
                }
            }
            catch (Exception e) {

            }
        }
    }
}
