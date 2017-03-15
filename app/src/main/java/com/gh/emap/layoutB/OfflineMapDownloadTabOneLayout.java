package com.gh.emap.layoutB;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class OfflineMapDownloadTabOneLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private View mLayout; // 布局
    private TextView mCurrentCityView; // 当前城市
    private ExpandableListView mCurrentCityList; // 当前城市列表
    private TextView mHotCityView; // 热门城市
    private ExpandableListView mHotCityList; // 热门城市列表
    private TextView mOtherCityView; // 其他城市
    private ExpandableListView mOtherCityList; // 其他城市列表

    public OfflineMapDownloadTabOneLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mLayout = mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_tab_one_city_list);
        mCurrentCityView = (TextView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_tab_one_current_city_view);
        mCurrentCityList = (ExpandableListView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_tab_one_current_city_list);
        mHotCityView = (TextView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_tab_one_hot_city_view);
        mHotCityList = (ExpandableListView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_tab_one_hot_city_list);
        mOtherCityView = (TextView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_tab_one_other_city_view);
        mOtherCityList = (ExpandableListView)mOfflineMapDownloadActivity.findViewById(R.id.offline_map_download_tab_one_other_city_list);
    }
}
