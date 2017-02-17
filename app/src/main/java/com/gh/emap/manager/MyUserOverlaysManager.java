package com.gh.emap.manager;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

import com.gh.emap.overlay.LineOverlayItems;
import com.gh.emap.overlay.PlaneOverlayItems;
import com.gh.emap.overlay.PointOverlayItems;

/**
 * Created by GuHeng on 2016/12/15.
 * 用户覆盖物管理类
 */

public class MyUserOverlaysManager {
    private MainActivity mMainActivity;
    private Drawable mPointOverlayItemMarker;
    private PointOverlayItems mPointOverlayItems;
    private LineOverlayItems mLineOverlayItems;
    private PlaneOverlayItems mPlaneOverlayItems;

    public MyUserOverlaysManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mPointOverlayItemMarker = ContextCompat.getDrawable(mMainActivity, R.mipmap.point_bg);
        mPointOverlayItems = new PointOverlayItems(mMainActivity, mPointOverlayItemMarker);
        mPointOverlayItems.init();

        mLineOverlayItems = new LineOverlayItems(mMainActivity);
        mLineOverlayItems.init();

        mPlaneOverlayItems = new PlaneOverlayItems(mMainActivity);
        mPlaneOverlayItems.init();
    }

    public PointOverlayItems getPointOverlayItems() {
        return mPointOverlayItems;
    }

    public LineOverlayItems getLineOverlayItems() {
        return mLineOverlayItems;
    }

    public PlaneOverlayItems getPlaneOverlayItems() {
        return mPlaneOverlayItems;
    }
}
