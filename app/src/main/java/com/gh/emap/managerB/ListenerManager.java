package com.gh.emap.managerB;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.listenerB.OfflineMapDownloadTabOneListener;

/**
 * Created by GuHeng on 2017/3/15.
 */

public class ListenerManager {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    OfflineMapDownloadTabOneListener mOfflineMapDownloadTabOneListener;

    public ListenerManager(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        mOfflineMapDownloadTabOneListener = new OfflineMapDownloadTabOneListener(mOfflineMapDownloadActivity);
    }

    public void unInit() {

    }

    public OfflineMapDownloadTabOneListener getOfflineMapDownloadTabOneListener() {
        return mOfflineMapDownloadTabOneListener;
    }
}
