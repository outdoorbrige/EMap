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

    public PointOverlayItem(Context context, String title, String snippet, int latitude, int longitude) {
        super(new GeoPoint(latitude, longitude), title, snippet);

        this.mContext = context;
        mPointObject = new PointObject();
    }

    public PointObject getPointObject() {
        return mPointObject;
    }

    public void setPointObject(PointObject pointObject) {
        this.mPointObject = pointObject;
    }
}
