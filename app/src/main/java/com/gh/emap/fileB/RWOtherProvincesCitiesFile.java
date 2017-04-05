package com.gh.emap.fileB;

import com.gh.emap.fileA.TObjectFile;
import com.gh.emap.modelB.OneProvinceInfo;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class RWOtherProvincesCitiesFile {
    private static TObjectFile<OneProvinceInfo> mTObjectFile = new TObjectFile<>();

    public static ArrayList<OneProvinceInfo> read(String file, String[] errorMsg) {
        return mTObjectFile.readList(file, errorMsg);
    }

    public static void write(String file, ArrayList<OneProvinceInfo> otherProvincesCities, String[] errorMsg) {
        mTObjectFile.writeList(file, otherProvincesCities, errorMsg);
    }
}
