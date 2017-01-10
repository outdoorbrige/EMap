package com.gh.emap.overlay;

import android.content.Context;

import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.OverlayItem;

/**
 * Created by GuHeng on 2017/1/10.
 * 画线覆盖物
 */

public class LineOverlayItem extends OverlayItem {
    private Context mContext;
    private LineObject mLineObject;

    public LineOverlayItem(int latitude, int longitude, String title, String snippet, Context context, int strokeWidth, int strokeColor, boolean dottedLine) {
        super(new GeoPoint(latitude, longitude), title, snippet);

        this.mContext = context;
        this.mLineObject = new LineObject(strokeWidth, strokeColor, dottedLine);
    }

    public void setLineObject(LineObject lineObject) {
        this.mLineObject = lineObject;
    }

    public LineObject getLineObject() {
        return this.mLineObject;
    }
}
