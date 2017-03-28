package com.gh.emap.fileB;

import android.content.Context;
import android.os.Environment;

import com.gh.emap.R;
import com.gh.emap.fileA.TObjectFile;
import com.gh.emap.managerA.MapManager;
import com.gh.emap.modelB.OneCityInfo;
import com.gh.emap.modelB.OneProvinceInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class OtherProvincesCitiesFile {
    private Context mContext;
    private ArrayList<OneProvinceInfo> mOtherProvincesCities = new ArrayList<>();
    private TObjectFile<OneProvinceInfo> mTObjectFile = new TObjectFile<>();

    public OtherProvincesCitiesFile(Context context) {
        mContext = context;
    }

    public void init() {

    }

    public ArrayList<OneProvinceInfo> getOtherProvincesCities() {
        return mOtherProvincesCities;
    }

    private String getFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return MapManager.getCachePath(mContext) + File.separator +
                    "OtherProvincesCities.config";
        } else {
            return null;
        }
    }

    public void read(String[] errorMsg) {
        mOtherProvincesCities = mTObjectFile.readList(getFile(), errorMsg);
    }

    public void write(ArrayList<OneProvinceInfo> otherProvincesCities, String[] errorMsg) {
        mTObjectFile.writeList(getFile(), otherProvincesCities, errorMsg);
    }
}
