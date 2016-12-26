package com.gh.emap.overlay;

import android.content.Context;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.OverlayItem;

import java.io.Serializable;

/**
 * Created by GuHeng on 2016/11/14.
 * 画点覆盖物
 */
public class PointOverlayItem extends OverlayItem implements Serializable {
    private Context mContext;
    private PointObject mPointObject;

    public PointOverlayItem(int latitude, int longitude, String title, String snippet, Context context) {
        super(new GeoPoint(latitude, longitude), title, snippet);

        this.mContext = context;

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
        this.mPointObject = pointObject;
    }
}
