package com.gh.emap.fileB;

import android.content.Context;
import android.os.Environment;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.fileA.TObjectFile;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.managerA.MapManager;
import com.gh.emap.modelB.OneCityInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class HotCitiesFile {
    private Context mContext;
    private ArrayList<OneCityInfo> mHotCities = new ArrayList<>();
    private TObjectFile<OneCityInfo> mTObjectFile = new TObjectFile<>();

    public HotCitiesFile(Context context) {
        mContext = context;
    }

    public void init() {

    }

    public ArrayList<OneCityInfo> getHotCities() {
        return mHotCities;
    }

    private String getFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return MapManager.getCachePath(mContext) + File.separator +
                    "HotCities.config";
        } else {
            return null;
        }
    }

    public void read(String[] errorMsg) {
        mHotCities = mTObjectFile.readList(getFile(), errorMsg);
    }

    public void write(ArrayList<OneCityInfo> hotCities, String[] errorMsg) {
        mTObjectFile.writeList(getFile(), hotCities, errorMsg);
    }
}
