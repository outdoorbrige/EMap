package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.fileB.CurrentCityFile;
import com.gh.emap.fileB.HotCitiesFile;
import com.gh.emap.fileB.OtherProvincesCitiesFile;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class FileManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private CurrentCityFile mCurrentCityFile;
    private HotCitiesFile mHotCitiesFile;
    private OtherProvincesCitiesFile mOtherProvincesCitiesFile;

    public FileManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mCurrentCityFile = new CurrentCityFile(mOfflineMapDownloadActivity);
        mCurrentCityFile.init();

        mHotCitiesFile = new HotCitiesFile(mOfflineMapDownloadActivity);
        mHotCitiesFile.init();

        mOtherProvincesCitiesFile = new OtherProvincesCitiesFile(mOfflineMapDownloadActivity);
        mOtherProvincesCitiesFile.init();
    }

    public void unInit() {

    }

    public CurrentCityFile getCurrentCityFile() {
        return mCurrentCityFile;
    }

    public HotCitiesFile getHotCitiesFile() {
        return mHotCitiesFile;
    }

    public OtherProvincesCitiesFile getOtherProvincesCitiesFile() {
        return mOtherProvincesCitiesFile;
    }
}
