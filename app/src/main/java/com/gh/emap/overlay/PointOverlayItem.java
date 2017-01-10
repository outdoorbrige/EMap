package com.gh.emap.overlay;

import android.content.Context;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.OverlayItem;

/**
 * Created by GuHeng on 2016/11/14.
 * 画点覆盖物
 */
public class PointOverlayItem extends OverlayItem {
    private Context mContext;
    private PointObject mPointObject;

    public PointOverlayItem(int latitude, int longitude, String title, String snippet, Context context) {
        super(new GeoPoint(latitude, longitude), title, snippet);

        this.mContext = context;

        mPointObject = new PointObject(title, snippet, -1, "", "", latitude, longitude);
    }

    public PointObject getPointObject() {
        return mPointObject;
    }

    public void setPointObject(PointObject pointObject) {
        this.mPointObject = pointObject;
    }
}
