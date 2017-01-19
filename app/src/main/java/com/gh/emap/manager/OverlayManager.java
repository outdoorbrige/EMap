package com.gh.emap.manager;

import com.gh.emap.MainActivity;
import com.gh.emap.overlay.LineOverlay;
import com.gh.emap.overlay.PlaneOverlay;
import com.gh.emap.overlay.PointOverlay;

/**
 * Created by GuHeng on 2016/12/22.
 * 覆盖物管理
 */

public class OverlayManager {
    private MainActivity mMainActivity;
    private PointOverlay mPointOverlay;
    private LineOverlay mLineOverlay;
    private PlaneOverlay mPlaneOverlay;

    public OverlayManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mPointOverlay = new PointOverlay(mMainActivity);
        mLineOverlay = new LineOverlay(mMainActivity);
        mPlaneOverlay = new PlaneOverlay(mMainActivity);
    }

    public PointOverlay getPointOverlay() {
        return mPointOverlay;
    }

    public LineOverlay getLineOverlay() {
        return mLineOverlay;
    }

    public PlaneOverlay getPlaneOverlay() {
        return mPlaneOverlay;
    }
}
