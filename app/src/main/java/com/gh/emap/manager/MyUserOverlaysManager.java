package com.gh.emap.manager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.gh.emap.R;

import com.gh.emap.overlay.PointOverlayItem;
import com.gh.emap.overlay.PointOverlayItems;

import java.util.HashMap;

/**
 * Created by GuHeng on 2016/12/15.
 * 用户覆盖物管理类
 */

public class MyUserOverlaysManager {
    private Context mContext;
    private Drawable mMarker;
    private PointOverlayItems mPointOverlayItems;

    public MyUserOverlaysManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mMarker = ContextCompat.getDrawable(this.mContext, R.mipmap.added_icon);
        this.mPointOverlayItems = new PointOverlayItems(this.mMarker, this.mContext);
        this.mPointOverlayItems.init();
    }

    public PointOverlayItems getPointOverlayItems() {
        return this.mPointOverlayItems;
    }
}
