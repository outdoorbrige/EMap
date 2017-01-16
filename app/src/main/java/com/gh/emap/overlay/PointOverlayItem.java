package com.gh.emap.overlay;

import com.gh.emap.MainActivity;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.OverlayItem;

/**
 * Created by GuHeng on 2016/11/14.
 * 画点覆盖物
 */
public class PointOverlayItem extends OverlayItem {
    private MainActivity mMainActivity;
    private PointObject mPointObject;

    public PointOverlayItem(MainActivity mainActivity, String title, String snippet, int latitude, int longitude) {
        super(new GeoPoint(latitude, longitude), title, snippet);

        mMainActivity = mainActivity;

        mPointObject = new PointObject();
        mPointObject.setTitle(title);
        mPointObject.setSnippet(snippet);
        mPointObject.setLatitude(latitude);
        mPointObject.setLongitude(longitude);
    }

    public PointObject getPointObject() {
        return mPointObject;
    }

    public void setPointObject(PointObject pointObject) {
        mPointObject = pointObject;
    }
}
