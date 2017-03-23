package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.listenerB.CityListListener;
import com.gh.emap.listenerB.HeaderListener;
import com.gh.emap.listenerB.OtherCityExpandableListGroupItemListener;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class ListenerManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    HeaderListener mHeaderListener;
    CityListListener mCityListListener;
    OtherCityExpandableListGroupItemListener mOtherCityExpandableListGroupItemListener;

    public ListenerManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mHeaderListener = new HeaderListener(mOfflineMapDownloadActivity);
        mCityListListener = new CityListListener(mOfflineMapDownloadActivity);
        mOtherCityExpandableListGroupItemListener = new OtherCityExpandableListGroupItemListener(mOfflineMapDownloadActivity);
    }

    public void unInit() {

    }

    public HeaderListener getHeaderListener() {
        return mHeaderListener;
    }

    public CityListListener getCityListListener() {
        return mCityListListener;
    }

    public OtherCityExpandableListGroupItemListener getOtherCityExpandableListGroupItemListener() {
        return mOtherCityExpandableListGroupItemListener;
    }
}
