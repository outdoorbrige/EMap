package com.gh.emap.fileA;

import com.gh.emap.overlayA.LineObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/9.
 * 覆盖物 线 信息文件
 */

public class RWLineFile {
    private static TObjectFile<LineObject> mTObjectFile = new TObjectFile<>();

    public static LineObject read(String file, String[] errorMsg) {
        return mTObjectFile.read(file, errorMsg);
    }

    public static ArrayList<LineObject> read(ArrayList<File> files, String[] errorMsg) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<LineObject> items = new ArrayList<LineObject>();

        for(int i = 0; i < files.size(); i ++) {
            LineObject object = read(files.get(i).getPath(), errorMsg);
            if(object != null) {
                items.add(object);
            }
        }

        return items;
    }

    public static void write(String file, LineObject object, String[] errorMsg) {
        mTObjectFile.write(file, object, errorMsg);
    }
}
