package com.gh.emap.fileB;

import com.gh.emap.fileA.TObjectFile;
import com.gh.emap.modelB.OneCityInfo;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class RWCurrentCityFile {
    private static TObjectFile<OneCityInfo> mTObjectFile = new TObjectFile<>();

    public static OneCityInfo read(String file, String[] errorMsg) {
        return mTObjectFile.read(file, errorMsg);
    }

    public static void write(String file, OneCityInfo currentCity, String[] errorMsg) {
        mTObjectFile.write(file, currentCity, errorMsg);
    }
}
