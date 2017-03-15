package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.layoutB.OfflineMapDownloadTabOneLayout;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class LayoutManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private OfflineMapDownloadTabOneLayout mOfflineMapDownloadTabOneLayout;

    public LayoutManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mOfflineMapDownloadTabOneLayout = new OfflineMapDownloadTabOneLayout(mOfflineMapDownloadActivity);
        mOfflineMapDownloadTabOneLayout.init();
    }

    public void unInit() {

    }

    public OfflineMapDownloadTabOneLayout getOfflineMapDownloadTabOneLayout() {
        return mOfflineMapDownloadTabOneLayout;
    }
}
