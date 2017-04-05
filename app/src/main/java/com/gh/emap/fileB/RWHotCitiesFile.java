package com.gh.emap.fileB;

import com.gh.emap.fileA.TObjectFile;
import com.gh.emap.modelB.OneCityInfo;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class RWHotCitiesFile {
    private static TObjectFile<OneCityInfo> mTObjectFile = new TObjectFile<>();

    public static ArrayList<OneCityInfo> read(String file, String[] errorMsg) {
        return mTObjectFile.readList(file, errorMsg);
    }

    public static void write(String file, ArrayList<OneCityInfo> hotCities, String[] errorMsg) {
        mTObjectFile.writeList(file, hotCities, errorMsg);
    }
}
