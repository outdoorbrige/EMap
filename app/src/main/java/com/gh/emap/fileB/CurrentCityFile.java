package com.gh.emap.fileB;

import android.content.Context;
import android.os.Environment;

import com.gh.emap.R;
import com.gh.emap.fileA.TObjectFile;
import com.gh.emap.managerA.MapManager;
import com.gh.emap.modelB.OneCityInfo;
import com.tianditu.android.maps.TGeoAddress;

import java.io.File;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class CurrentCityFile {
    private Context mContext;
    private OneCityInfo mOneCityInfo;
    private TObjectFile<OneCityInfo> mTObjectFile = new TObjectFile<>();

    public CurrentCityFile(Context context) {
        mContext = context;
    }

    public void init() {

    }

    public OneCityInfo getCurrentCity() {
        return mOneCityInfo;
    }

    private String getFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return MapManager.getCachePath(mContext) + File.separator +
                    "CurrentCity.config";
        } else {
            return null;
        }
    }

    public void read(String[] errorMsg) {
        mOneCityInfo = mTObjectFile.read(getFile(), errorMsg);
    }

    public void write(OneCityInfo currentCity, String[] errorMsg) {
        mTObjectFile.write(getFile(), currentCity, errorMsg);
    }
}
