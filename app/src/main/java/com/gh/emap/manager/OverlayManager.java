package com.gh.emap.manager;

import android.content.Context;

import com.gh.emap.overlay.LineOverlay;
import com.gh.emap.overlay.PointOverlay;

/**
 * Created by GuHeng on 2016/12/22.
 * 覆盖物管理
 */

public class OverlayManager {
    private Context mContext;
    private PointOverlay mPointOverlay;
    private LineOverlay mLineOverlay;

    public OverlayManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        mPointOverlay = new PointOverlay(this.mContext);
        mLineOverlay = new LineOverlay(this.mContext);
    }

    public PointOverlay getPointOverlay() {
        return mPointOverlay;
    }

    public LineOverlay getLineOverlay() {
        return mLineOverlay;
    }
}
