package com.gh.emap.file;

import com.gh.emap.overlay.PlaneObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/17.
 */

public class RWPlaneFile {
    private static TObjectFile<PlaneObject> mTObjectFile = new TObjectFile<>();

    public static PlaneObject read(String file) {
        return mTObjectFile.read(file);
    }

    public static ArrayList<PlaneObject> read(ArrayList<File> files) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<PlaneObject> items = new ArrayList<PlaneObject>();

        for(int i = 0; i < files.size(); i ++) {
            PlaneObject object = read(files.get(i).getPath());
            if(object != null) {
                items.add(object);
            }
        }

        return items;
    }

    public static boolean write(String file, PlaneObject object) {
        return mTObjectFile.write(file, object);
    }
}
