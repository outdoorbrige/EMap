package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.layoutB.CityListCurrentCityLayout;
import com.gh.emap.layoutB.CityListHotCityLayout;
import com.gh.emap.layoutB.CityListLayout;
import com.gh.emap.layoutB.CityListOtherProvincesCitiesLayout;
import com.gh.emap.layoutB.DownloadManagerLayout;
import com.gh.emap.layoutB.HeaderLayout;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class LayoutManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private HeaderLayout mHeaderLayout;
    private CityListCurrentCityLayout mCityListCurrentCityLayout;
    private CityListHotCityLayout mCityListHotCityLayout;
    private CityListOtherProvincesCitiesLayout mCityListOtherProvincesCitiesLayout;
    private CityListLayout mCityListLayout;
    private DownloadManagerLayout mDownloadManagerLayout;

    public LayoutManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mHeaderLayout = new HeaderLayout(mOfflineMapDownloadActivity);
        mHeaderLayout.init();

        mCityListCurrentCityLayout = new CityListCurrentCityLayout(mOfflineMapDownloadActivity);
        mCityListCurrentCityLayout.init();

        mCityListHotCityLayout = new CityListHotCityLayout(mOfflineMapDownloadActivity);
        mCityListHotCityLayout.init();

        mCityListOtherProvincesCitiesLayout = new CityListOtherProvincesCitiesLayout(mOfflineMapDownloadActivity);
        mCityListOtherProvincesCitiesLayout.init();

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

    public CityListCurrentCityLayout getCityListCurrentCityLayout() {
        return mCityListCurrentCityLayout;
    }

    public CityListHotCityLayout getCityListHotCityLayout() {
        return mCityListHotCityLayout;
    }

    public CityListOtherProvincesCitiesLayout getCityListOtherProvincesCitiesLayout() {
        return mCityListOtherProvincesCitiesLayout;
    }

    public CityListLayout getCityListLayout() {
        return mCityListLayout;
    }

    public DownloadManagerLayout getDownloadManagerLayout() {
        return mDownloadManagerLayout;
    }
}
