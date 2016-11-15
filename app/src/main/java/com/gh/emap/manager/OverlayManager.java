package com.gh.emap.manager;

import android.content.Context;

import com.gh.emap.overlay.PointOverlay;

/**
 * Created by GuHeng on 2016/11/15.
 * 覆盖物管理
 */
public class OverlayManager {
    private Context mContext;
    private PointOverlay mPointOverlay;

    public OverlayManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mPointOverlay = new PointOverlay(this.mContext);
    }

    public PointOverlay getPointOverlay() {
        return this.mPointOverlay;
    }}
