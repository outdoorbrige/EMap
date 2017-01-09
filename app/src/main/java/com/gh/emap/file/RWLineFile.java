package com.gh.emap.file;

import com.gh.emap.overlay.LineObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/9.
 * 覆盖物 线 信息文件
 */

public class RWLineFile {
    private static TObjectFile<LineObject> mTObjectFile = new TObjectFile<>();

    public static LineObject read(String file) {
        return mTObjectFile.read(file);
    }

    public static ArrayList<LineObject> read(ArrayList<File> files) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<LineObject> items = new ArrayList<LineObject>();

        for(int i = 0; i < files.size(); i ++) {
            LineObject object = read(files.get(i).getPath());
            if(object != null) {
                items.add(object);
            }
        }

        return items;
    }

    public static boolean write(String file, LineObject object) {
        return mTObjectFile.write(file, object);
    }
}
