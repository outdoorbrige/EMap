package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.layoutB.CityListLayout;
import com.gh.emap.layoutB.DownloadManagerLayout;
import com.gh.emap.layoutB.HeaderLayout;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class LayoutManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private HeaderLayout mHeaderLayout;
    private CityListLayout mCityListLayout;
    private DownloadManagerLayout mDownloadManagerLayout;

    public LayoutManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mHeaderLayout = new HeaderLayout(mOfflineMapDownloadActivity);
        mHeaderLayout.init();

        mCityListLayout = new CityListLayout(mOfflineMapDownloadActivity);
        mCityListLayout.init();

        mDownloadManagerLayout = new DownloadManagerLayout(mOfflineMapDownloadActivity);
        mDownloadManagerLayout.init();
    }

    public void unInit() {

    }

    public HeaderLayout getHeaderLayout() {
        return mHeaderLayout;
    }

    public CityListLayout getCityListLayout() {
        return mCityListLayout;
    }

    public DownloadManagerLayout getDownloadManagerLayout() {
        return mDownloadManagerLayout;
    }
}
