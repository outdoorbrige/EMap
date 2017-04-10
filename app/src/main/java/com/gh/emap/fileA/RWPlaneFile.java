package com.gh.emap.fileA;

import com.gh.emap.Utility.TObjectFile;
import com.gh.emap.overlayA.PlaneObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/1/17.
 */

public class RWPlaneFile {
    private static TObjectFile<PlaneObject> mTObjectFile = new TObjectFile<>();

    public static PlaneObject read(String file, String[] errorMsg) {
        return mTObjectFile.read(file, errorMsg);
    }

    public static ArrayList<PlaneObject> read(ArrayList<File> files, String[] errorMsg) {
        if(files == null || files.size() == 0) {
            return null;
        }

        ArrayList<PlaneObject> items = new ArrayList<PlaneObject>();

        for(int i = 0; i < files.size(); i ++) {
            PlaneObject object = read(files.get(i).getPath(), errorMsg);
            if(object != null) {
                items.add(object);
            }
        }

        return items;
    }

    public static void write(String file, PlaneObject object, String[] errorMsg) {
        mTObjectFile.write(file, object, errorMsg);
    }
}
