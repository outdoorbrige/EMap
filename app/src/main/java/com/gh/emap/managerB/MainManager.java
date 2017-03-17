package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class MainManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private ListenerManager mListenerManager;
    private LayoutManager mLayoutManager;

    public MainManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mListenerManager = new ListenerManager(mOfflineMapDownloadActivity);
        mListenerManager.init();

        mLayoutManager = new LayoutManager(mOfflineMapDownloadActivity);
        mLayoutManager.init();
    }

    public void unInit() {
        mListenerManager.unInit();
        mLayoutManager.unInit();
    }

    public ListenerManager getListenerManager() {
        return mListenerManager;
    }

    public LayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}
