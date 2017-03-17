package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.listenerB.CityListListener;
import com.gh.emap.listenerB.HeaderListener;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class ListenerManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    HeaderListener mHeaderListener;
    CityListListener mCityListListener;

    public ListenerManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mHeaderListener = new HeaderListener(mOfflineMapDownloadActivity);
        mCityListListener = new CityListListener(mOfflineMapDownloadActivity);
    }

    public void unInit() {

    }

    public HeaderListener getHeaderListener() {
        return mHeaderListener;
    }

    public CityListListener getCityListListener() {
        return mCityListListener;
    }
}
