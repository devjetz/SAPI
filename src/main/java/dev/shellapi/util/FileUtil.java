package dev.shellapi.util;

import java.io.File;

public class FileUtil {

    public static void ensureDir(String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
}
