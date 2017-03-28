package com.gh.emap.fileA;

import com.gh.emap.overlayA.PointObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/12/14.
 * 覆盖物 点 信息文件
 */

public class RWPointFile {
    private static TObjectFile<PointObject> mTObjectFile = new TObjectFile<>();

    public static PointObject read(String file, String[] errorMsg) {
        return mTObjectFile.read(file, errorMsg);
    }

    public static ArrayList<PointObject> read(ArrayList<File> files, String[] errorMsg) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<PointObject> items = new ArrayList<PointObject>();

        for(int i = 0; i < files.size(); i ++) {
            PointObject object = read(files.get(i).getPath(), errorMsg);
            if(object != null) {
                items.add(object);
            }
        }

        return items;
    }

    public static void write(String file, PointObject object, String[] errorMsg) {
        mTObjectFile.write(file, object, errorMsg);
    }
}
