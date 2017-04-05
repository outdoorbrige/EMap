package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.listenerB.CityListCurrentCityListener;
import com.gh.emap.listenerB.CityListHotCityListener;
import com.gh.emap.listenerB.CityListListener;
import com.gh.emap.listenerB.HeaderListener;
import com.gh.emap.listenerB.OfflineMapDownloadTypeSelectListener;
import com.gh.emap.listenerB.OtherCityExpandableListChildItemListener;
import com.gh.emap.listenerB.OtherCityExpandableListGroupItemListener;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class ListenerManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private HeaderListener mHeaderListener;
    private CityListListener mCityListListener;
    private OtherCityExpandableListGroupItemListener mOtherCityExpandableListGroupItemListener;
    private OtherCityExpandableListChildItemListener mOtherCityExpandableListChildItemListener;
    private CityListCurrentCityListener mCityListCurrentCityListener;
    private CityListHotCityListener mCityListHotCityListener;
    private OfflineMapDownloadTypeSelectListener mOfflineMapDownloadTypeSelectListener;

    public ListenerManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mHeaderListener = new HeaderListener(mOfflineMapDownloadActivity);
        mCityListListener = new CityListListener(mOfflineMapDownloadActivity);
        mOtherCityExpandableListGroupItemListener = new OtherCityExpandableListGroupItemListener(mOfflineMapDownloadActivity);
        mOtherCityExpandableListChildItemListener = new OtherCityExpandableListChildItemListener(mOfflineMapDownloadActivity);
        mCityListCurrentCityListener = new CityListCurrentCityListener(mOfflineMapDownloadActivity);
        mCityListHotCityListener = new CityListHotCityListener(mOfflineMapDownloadActivity);
        mOfflineMapDownloadTypeSelectListener = new OfflineMapDownloadTypeSelectListener(mOfflineMapDownloadActivity);
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

    public OtherCityExpandableListChildItemListener getOtherCityExpandableListChildItemListener() {
        return mOtherCityExpandableListChildItemListener;
    }

    public CityListCurrentCityListener getCityListCurrentCityListener() {
        return mCityListCurrentCityListener;
    }

    public CityListHotCityListener getCityListHotCityListener() {
        return mCityListHotCityListener;
    }

    public OfflineMapDownloadTypeSelectListener getOfflineMapDownloadTypeSelectListener() {
        return mOfflineMapDownloadTypeSelectListener;
    }
}
