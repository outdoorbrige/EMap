package com.gh.emap.manager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.gh.emap.R;

import com.gh.emap.overlay.LineOverlayItems;
import com.gh.emap.overlay.PointOverlayItems;

/**
 * Created by GuHeng on 2016/12/15.
 * 用户覆盖物管理类
 */

public class MyUserOverlaysManager {
    private Context mContext;
    private Drawable mPointOverlayItemMarker;
    private PointOverlayItems mPointOverlayItems;
    private LineOverlayItems mLineOverlayItems;

    public MyUserOverlaysManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mPointOverlayItemMarker = ContextCompat.getDrawable(this.mContext, R.mipmap.added_icon);
        this.mPointOverlayItems = new PointOverlayItems(this.mContext, this.mPointOverlayItemMarker);
        this.mPointOverlayItems.init();

        this.mLineOverlayItems = new LineOverlayItems(this.mContext);
        this.mLineOverlayItems.init();
    }

    public PointOverlayItems getPointOverlayItems() {
        return this.mPointOverlayItems;
    }

    public LineOverlayItems getLineOverlayItems() {
        return this.mLineOverlayItems;
    }
}
