package market.huake.com.testok;

import android.os.Environment;

import java.io.File;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/13 12:39
 * @描述	      
 */
public class Utils {

    public static String getDir(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsoluteFile());
        sb.append(File.separator);
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /** 创建文件夹 */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }


}
