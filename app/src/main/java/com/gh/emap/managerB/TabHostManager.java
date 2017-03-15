package com.gh.emap.managerB;

import android.widget.TabHost;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class TabHostManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    private TabHost mTabHost;

    private final String TAB_ONE_TAG = "TAB_ONE";
    private final String TAB_ONE_LABEL = "城市列表";
    private final String TAB_TWO_TAG = "TAB_TWO";
    private final String TAB_TWO_LABEL = "下载管理";

    public TabHostManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mTabHost = mOfflineMapDownloadActivity.getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec(TAB_ONE_TAG).setIndicator(TAB_ONE_LABEL).setContent(R.id.offline_map_download_tab_one_city_list));
        mTabHost.addTab(mTabHost.newTabSpec(TAB_TWO_TAG).setIndicator(TAB_TWO_LABEL).setContent(R.id.offline_map_download_tab_two_download_manager));
    }

    public void unInit() {

    }
}
