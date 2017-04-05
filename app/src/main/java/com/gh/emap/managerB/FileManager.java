package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.fileB.CacheProvincesCitiesFiles;
import com.gh.emap.modelB.OneCityInfo;
import com.gh.emap.modelB.OneProvinceInfo;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2017/3/27.
 */

public class FileManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private CacheProvincesCitiesFiles mCacheProvincesCitiesFiles;

    public FileManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mCacheProvincesCitiesFiles = new CacheProvincesCitiesFiles(mOfflineMapDownloadActivity.getMyApplication().getMainActivity());
        mCacheProvincesCitiesFiles.init();
    }

    public void unInit() {
        mCacheProvincesCitiesFiles.writeCurrentCityFile(
                mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getListenerManager().getMapListener().getCurrentCity());

        mCacheProvincesCitiesFiles.writeHotCitiesFile(
                mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getListenerManager().getMapListener().getHotCities());

        mCacheProvincesCitiesFiles.writeOtherProvincesCitiesFile(
                mOfflineMapDownloadActivity.getMyApplication().getMainActivity().getMainManager().getListenerManager().getMapListener().getOtherProvincesCities());
    }

    public CacheProvincesCitiesFiles getCacheProvincesCitiesFiles() {
        return mCacheProvincesCitiesFiles;
    }
}
