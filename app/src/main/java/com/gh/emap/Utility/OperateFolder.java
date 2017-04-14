package com.gh.emap.utility;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/12/15.
 * 操作文件夹
 */

public class OperateFolder {

    // 查找指定目录下指定后缀的所有文件
    public static void TraverseFindFlies(String path, String fileSuffix, ArrayList<File> files) {
        File dir = new File(path);
        if(!dir.exists()) {
            return; // 路径不存在
        }

        File[] subFiles = dir.listFiles();
        if(subFiles == null || subFiles.length == 0) {
            return; // 空文件夹
        }

        for(int i = 0; i < subFiles.length; i ++) {
            if(subFiles[i].isFile()) {
                if(subFiles[i].getName().toLowerCase().endsWith(fileSuffix.toLowerCase())) {
                    files.add(subFiles[i]);
                }
            } else {
                TraverseFindFlies(subFiles[i].getAbsolutePath(), fileSuffix, files);
            }
        }
    }
}
